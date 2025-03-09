USE [master]
GO

/*******************************************************************************
   Drop database if it exists
********************************************************************************/
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N' LaptopWarranty')
BEGIN
	ALTER DATABASE  LaptopWarranty SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE  LaptopWarranty SET ONLINE;
	DROP DATABASE  LaptopWarranty;
END

GO

CREATE DATABASE  LaptopWarranty
GO

USE  LaptopWarranty
GO

CREATE TABLE Role (
    RoleId INT IDENTITY(1,1) PRIMARY KEY,
    RoleName VARCHAR(255) NOT NULL
);

CREATE TABLE Staff (
    StaffId VARCHAR(255) PRIMARY KEY,
    Username VARCHAR(255) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    FirstName NVARCHAR(255),
    LastName NVARCHAR(255),
    Email VARCHAR(255),
    Phone VARCHAR(255),
    Gender VARCHAR(50),
    BirthDate DATE,
    Status VARCHAR(50),
	IsWork BIT DEFAULT 1, -- '0' busy , '1' available
    RoleId INT,
    FOREIGN KEY (RoleId) REFERENCES Role(RoleId)
);

CREATE TABLE Customer (
    CustomerId INT IDENTITY(1,1) PRIMARY KEY,
    Username VARCHAR(100) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    FirstName NVARCHAR(255),
    LastName NVARCHAR(255),
	Phone VARCHAR(255),
    Email VARCHAR(255),
    Gender VARCHAR(50),
    BirthDate DATE,
    Status VARCHAR(50),
    Address VARCHAR(255)
);

CREATE TABLE Product (
    ProductId VARCHAR(255) PRIMARY KEY,
    ProductName NVARCHAR(255) NOT NULL,
    Brand VARCHAR(255),
    Price BIGINT,
    CustomerId INT,
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)
);

CREATE TABLE WarrantyForm (
    FormId INT IDENTITY(1,1) PRIMARY KEY,
    ProductId VARCHAR(255) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NULL,
    Status VARCHAR(50) DEFAULT 'Active', 
    Verified varchar(3) DEFAULT null, 
    FaultType VARCHAR(50) CHECK (FaultType IN ('Manufacturer', 'User')) NULL,
	TechnicianVerify varchar(3) DEFAULT null, -- '1' nếu đã xác nhận, '0' nếu chưa 
    ImageUrl VARCHAR(500), -- URL ảnh
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId)
);

CREATE TABLE WarrantyRequirement (
    RequirementId INT IDENTITY(1,1) PRIMARY KEY,
    ProductId VARCHAR(255) NOT NULL,
    CustomerId INT NOT NULL,
    StaffId VARCHAR(255) NULL, -- Technician tiếp nhận
    Status VARCHAR(50) NULL, -- Pending / Approved / Rejected
    Description NVARCHAR(255),
    ImageUrl VARCHAR(500), -- URL ảnh
    RegisterDate DATE DEFAULT GETDATE() NOT NULL,
    IsPay VARCHAR(3), -- 'Yes' nếu có phí, 'No' nếu miễn phí
    FormId INT UNIQUE NULL, -- Mỗi Requirement chỉ có 1 Form duy nhất
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId),
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId),
    FOREIGN KEY (FormId) REFERENCES WarrantyForm(FormId) 
);


CREATE TABLE Invoice (
    InvoiceId INT IDENTITY(1,1) PRIMARY KEY,
    RequirementId INT UNIQUE NULL, -- Chỉ có invoice nếu yêu cầu bảo hành có phí
    Price bigint NULL,
    Status VARCHAR(50) DEFAULT 'Unpaid', -- Unpaid / Paid
    Note NVARCHAR(MAX),
	IsConfirmed Bit ,
    FOREIGN KEY (RequirementId) REFERENCES WarrantyRequirement(RequirementId)
);

CREATE TABLE Component (
    ComponentId INT IDENTITY(1,1) PRIMARY KEY,
    ComponentName VARCHAR(255) NOT NULL,
    Brand VARCHAR(255),
    Status VARCHAR(50),
    Price bigint,
	Amount INT NOT NULL DEFAULT 0,
    StaffId VARCHAR(255),
    InvoiceId INT NULL, -- Linh kiện có thể có hóa đơn riêng
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId),
    FOREIGN KEY (InvoiceId) REFERENCES Invoice(InvoiceId)
);

CREATE TABLE Notification (
    NotificationId INT IDENTITY(1,1) PRIMARY KEY,
    CustomerId INT,
    RequirementId INT,
    Title VARCHAR(255),
    Message NVARCHAR(MAX),
    IsRead BIT DEFAULT 0, -- 'Y' nếu đã đọc, 'N' nếu chưa
    SendTime DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
    FOREIGN KEY (RequirementId) REFERENCES WarrantyRequirement(RequirementId)
);

CREATE TABLE Schedule (
    ScheduleId INT IDENTITY(1,1) PRIMARY KEY,
    StaffId VARCHAR(255),
    StartTime TIME,
    EndTime TIME,
    Date DATE,
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
);

CREATE TABLE WarrantyProcessing (
    ProcessingId INT IDENTITY(1,1) PRIMARY KEY,
    RequirementId INT NOT NULL,
    StaffId VARCHAR(255) NOT NULL,
    Status VARCHAR(255) DEFAULT 'Approved' CHECK (Status IN ('Approved','In Repair', 'Completed', 'Start Repair')),
    Note VARCHAR(255),
    ReturnDate DATE,
	IsAccept VARCHAR(255) Null,
    FOREIGN KEY (RequirementId) REFERENCES WarrantyRequirement(RequirementId),
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
);


CREATE TABLE TokenForgetPassword (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    Token VARCHAR(255) NOT NULL,
    ExpiryTime DATETIME NOT NULL,
    IsUsed BIT NOT NULL DEFAULT 0,
    CustomerId INT ,
	StaffId VARCHAR(255)  ,
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
	FOREIGN KEY (StaffId) REFERENCES Staff( StaffId)

);
CREATE TABLE Report (
    ReportId INT IDENTITY(1,1) PRIMARY KEY,
    Comment NVARCHAR(MAX),
    CustomerId INT,
    ProcessingId INT,
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
    FOREIGN KEY (ProcessingId ) REFERENCES WarrantyProcessing(ProcessingId)
);

CREATE TABLE Blog (
    BlogId INT IDENTITY(1,1) PRIMARY KEY,
    Title NVARCHAR(255) NOT NULL, -- Tiêu đề blog
    Description NVARCHAR(MAX), -- Mô tả ngắn hiển thị bên ngoài
    Content NVARCHAR(MAX), -- Nội dung đầy đủ khi nhấn vào xem chi tiết
    ImageUrl VARCHAR(500), -- URL ảnh đại diện cho blog
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời gian tạo
    StaffId VARCHAR(255),
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
);
CREATE TABLE [Transaction] (
    TransactionId INT IDENTITY(1,1) PRIMARY KEY,
    CustomerId INT NOT NULL,
    InvoiceId INT NOT NULL,
    Amount bigint NOT NULL,
    PaymentMethod VARCHAR(50) NOT NULL, -- VNPay, Momo, Bank Transfer, etc.
    PaymentStatus VARCHAR(50) NOT NULL, -- Success, Pending, Failed
    TransactionDate DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
    FOREIGN KEY (InvoiceId) REFERENCES Invoice(InvoiceId)
);

-- Chèn dữ liệu vào bảng Role
INSERT INTO Role (RoleName) VALUES
('Technician'),
('Service Manager'),
('Admin'),
('Cashier');

-- Chèn dữ liệu vào bảng Staff
INSERT INTO Staff (StaffId, Username, Password, FirstName, LastName, Email, Phone, Gender, BirthDate, Status, IsWork, RoleId) VALUES
('S001', 'tech1', 'pass123', 'John', 'Doe', 'john@example.com', '123456789', 'Male', '1990-05-15', 'Active', 1, 1),
('S002', 'tech2', 'pass123', 'Jane', 'Smith', 'jane@example.com', '987654321', 'Female', '1992-08-22', 'Active', 1, 1),
('S003', 'manager1', 'pass123', 'Michael', 'Brown', 'michael@example.com', '1122334455', 'Male', '1985-03-10', 'Active', 1, 2),
('S004', 'admin1', 'pass123', 'Alice', 'Johnson', 'alice@example.com', '2233445566', 'Female', '1988-12-05', 'Active', 1, 3),
('S005', 'cashier1', 'pass123', 'David', 'Wilson', 'david@example.com', '3344556677', 'Male', '1995-07-18', 'Active', 1, 4),
('S006', 'tech3', 'pass123', 'Laura', 'Miller', 'laura@example.com', '4455667788', 'Female', '1993-04-25', 'Active', 1, 1),
('S007', 'tech4', 'pass123', 'Kevin', 'Anderson', 'kevin@example.com', '5566778899', 'Male', '1991-11-30', 'Active', 1, 1),
('S008', 'manager2', 'pass123', 'Sophia', 'Martinez', 'sophia@example.com', '6677889900', 'Female', '1987-07-14', 'Active', 1, 2),
('S009', 'admin2', 'pass123', 'James', 'Taylor', 'james@example.com', '7788990011', 'Male', '1986-02-28', 'Active', 1, 3),
('S010', 'cashier2', 'pass123', 'Olivia', 'Harris', 'olivia@example.com', '8899001122', 'Female', '1994-09-05', 'Active', 1, 4);

-- Chèn dữ liệu vào bảng Customer
INSERT INTO Customer (Username, Password, FirstName, LastName, Phone, Email, Gender, BirthDate, Status, Address)
VALUES 
('user1', 'password123', 'John', 'Doe', '0123456789', 'thangbachi2542004@gmail.com', 'Male', '1990-05-15', 'Active', '123 Main St'),
('user2', 'password456', 'Jane', 'Smith', '0987654321', 'jane.smith@example.com', 'Female', '1995-08-25', 'Active', '456 Elm St');

INSERT INTO Product (ProductId, ProductName, Brand, Price, CustomerId) VALUES
('P001', 'Laptop Dell XPS 13', 'Dell', 35000000, 1),
('P002', 'Laptop HP Spectre x360', 'HP', 32000000, 1),
('P003', 'Laptop MacBook Pro 14', 'Apple', 45000000, 1),
('P004', 'Laptop Asus ROG Strix', 'Asus', 38000000, 1),
('P005', 'Laptop Lenovo ThinkPad X1', 'Lenovo', 30000000, 1),
('P006', 'Laptop Acer Predator Helios', 'Acer', 34000000, 1),
('P007', 'Laptop MSI Stealth 15M', 'MSI', 36000000, 1),
('P008', 'Laptop Samsung Galaxy Book3', 'Samsung', 28000000, 1),
('P009', 'Laptop Razer Blade 15', 'Razer', 50000000, 1),
('P010', 'Laptop Microsoft Surface Laptop 5', 'Microsoft', 33000000, 1),
('P011', 'Laptop LG Gram 17', 'LG', 29000000, 1),
('P012', 'Laptop Huawei MateBook X Pro', 'Huawei', 31000000, 1);
INSERT INTO WarrantyRequirement (ProductId, CustomerId, Status, Description, RegisterDate)
VALUES ('P001', 1, 'Pending', 'Khách hàng yêu cầu bảo hành do lỗi phần cứng', GETDATE());
INSERT INTO Blog (Title, Description, Content, ImageUrl, StaffId) VALUES
(N'Laptop Bảo Hành Miễn Phí', N'Hướng dẫn về chính sách bảo hành miễn phí', N'Nội dung chi tiết về bảo hành miễn phí...', 'images/blog1.jpg', 'S001'),
(N'Sửa Laptop Bị Lỗi Màn Hình', N'Cách khắc phục lỗi màn hình phổ biến', N'Nội dung chi tiết về sửa lỗi màn hình...', 'images/blog2.jpg', 'S002'),
(N'Làm Gì Khi Laptop Không Khởi Động', N'Hướng dẫn kiểm tra và sửa lỗi khi laptop không khởi động', N'Nội dung chi tiết...', 'images/blog3.jpg', 'S003'),
(N'Nâng Cấp RAM Cho Laptop', N'Lợi ích của việc nâng cấp RAM', N'Nội dung chi tiết về nâng cấp RAM...', 'images/blog4.jpg', 'S004'),
(N'Bảo Vệ Pin Laptop Đúng Cách', N'Mẹo giúp kéo dài tuổi thọ pin laptop', N'Nội dung chi tiết...', 'images/blog5.jpg', 'S005'),
(N'Cách Tăng Hiệu Suất Laptop', N'Các cách giúp laptop chạy nhanh hơn', N'Nội dung chi tiết...', 'images/blog6.jpg', 'S006'),
(N'Khắc Phục Laptop Chạy Chậm', N'Nguyên nhân và cách xử lý khi laptop chạy chậm', N'Nội dung chi tiết...', 'images/blog7.jpg', 'S007'),
(N'Cách Chọn Laptop Phù Hợp', N'Những điều cần biết trước khi mua laptop', N'Nội dung chi tiết...', 'images/blog8.jpg', 'S008'),
(N'Sửa Laptop Bị Nóng', N'Nguyên nhân và cách giảm nhiệt độ laptop', N'Nội dung chi tiết...', 'images/blog9.jpg', 'S009'),
(N'Tại Sao Laptop Không Nhận USB', N'Hướng dẫn sửa lỗi laptop không nhận USB', N'Nội dung chi tiết...', 'images/blog10.jpg', 'S010'),
(N'Cách Vệ Sinh Laptop Đúng Cách', N'Hướng dẫn vệ sinh laptop giúp kéo dài tuổi thọ', N'Nội dung chi tiết...', 'images/blog11.jpg', 'S001'),
(N'Cập Nhật Driver Cho Laptop', N'Tại sao cần cập nhật driver và cách thực hiện', N'Nội dung chi tiết...', 'images/blog12.jpg', 'S001');
INSERT INTO WarrantyForm (ProductId, StartDate, EndDate, Status, Verified)
VALUES 
('P001', '2023-01-01', '2024-01-01', 'Completed', 1),
('P001', '2024-01-02', '2025-01-02', 'Active', 1),
('P001', '2024-02-01', '2025-02-01', 'Active', 1),
('P001', '2023-03-05', '2024-03-05', 'Completed', 0),
('P001', '2024-04-10', '2025-04-10', 'Active', 1),
('P001', '2023-05-15', '2024-05-15', 'Active', 1),
('P001', '2023-06-20', '2024-06-20', 'Active', 0),
('P001', '2022-07-25', '2023-07-25', 'Canceled', 0),
('P001', '2024-08-30', '2025-08-30', 'Active', 1),
('P001', '2023-09-10', '2024-09-10', 'Active', 1);


