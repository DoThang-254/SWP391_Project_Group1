﻿USE [master]
GO

/*******************************************************************************
   Drop database if it exists
********************************************************************************/
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'LaptopWarranty')
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
	Address VARCHAR(255) ,
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
    Status VARCHAR(50) NOT NULL, 
    --Verified varchar(3) DEFAULT null, 
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
    FormId INT NULL, -- Mỗi Requirement chỉ có 1 Form duy nhất
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

/*CREATE TABLE Component (
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
);*/

CREATE TABLE WarrantyProcessing (
    ProcessingId INT IDENTITY(1,1) PRIMARY KEY,
    RequirementId INT NOT NULL,
    StaffId VARCHAR(255) NOT NULL,
    Status VARCHAR(255) DEFAULT 'Approved' CHECK (Status IN ('Approved','In Repair', 'Completed', 'Start Repair')),
    Note VARCHAR(255),
    ReturnDate DATE,
	--IsAccept VARCHAR(255) Null,
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
/*
CREATE TABLE Report (
    ReportId INT IDENTITY(1,1) PRIMARY KEY,
    Comment NVARCHAR(MAX),
    CustomerId INT,
    ProcessingId INT,
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
    FOREIGN KEY (ProcessingId ) REFERENCES WarrantyProcessing(ProcessingId)
);*/

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
('S003', 'manager1', 'pass123', 'Michael', 'Brown', 'thangmoneo2542004@gmail.com', '1122334455', 'Male', '1985-03-10', 'Active', 1, 2),
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
('P012', 'Laptop Huawei MateBook X Pro', 'Huawei', 31000000, 1),
('P013', 'Laptop MSI Stealth 15M', 'MSI', 31000000, 2) ,
('P014', 'Laptop LG Gram 17', 'LG', 31000000, 2),
('P015', 'Laptop Samsung Galaxy Book3', 'Huawei', 31000000, 2),
('P016', 'Laptop Lenovo ThinkPad X1', 'Lenovo', 31000000, 2),
('P017', 'Laptop MacBook Pro 14', 'Apple', 31000000, 2),
('P018', 'Laptop Dell XPS 13', 'Dell', 31000000, 2),
('P019', 'Laptop Huawei Pro', 'Huawei', 31000000, 2);


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

INSERT INTO WarrantyForm (ProductId, StartDate, EndDate, Status) VALUES
('P001', '2024-01-10', '2025-01-10', 'Active'),
('P002', '2024-02-15', '2025-02-15', 'Active'),
('P003', '2024-03-20', '2025-03-20', 'Active'),
('P004', '2024-04-05', '2025-04-05', 'Active'),
('P005', '2024-05-18', '2025-05-18', 'Active'),
('P006', '2024-06-22', '2025-06-22', 'Active'),
('P007', '2024-07-07', '2025-07-07', 'Active'),
('P008', '2024-08-12', '2025-08-12', 'Active'),
('P009', '2024-09-25', '2025-09-25', 'Active'),
('P010', '2024-10-30', '2025-10-30', 'Active'),
('P011', '2024-11-14', '2025-11-14', 'Active'),
('P012', '2024-12-29', '2025-12-29', 'Active'),
('P013', '2023-01-08', '2024-01-08', 'Active'),
('P014', '2023-02-19', '2024-02-19', 'Active'),
('P015', '2023-03-25', '2024-03-25', 'Active'),
('P016', '2023-04-30', '2024-04-30', 'Active'),
('P017', '2023-05-05', '2024-05-05', 'Active'),
('P018', '2023-06-15', '2024-06-15', 'Active');


/*INSERT INTO WarrantyRequirement (ProductId, CustomerId, StaffId, Status, Description, ImageUrl, RegisterDate, IsPay, FormId)
VALUES
('P001', 1, 'S001', 'Pending', N'Lỗi màn hình', 'https://example.com/image1.jpg', GETDATE(), 'No', null),
('P002', 2, 'S002', 'Approved', N'Lỗi bàn phím', 'https://example.com/image2.jpg', GETDATE(), 'Yes', null),
('P003', 3, 'S003', 'Rejected', N'Pin chai', 'https://example.com/image3.jpg', GETDATE(), 'No', NULL),
('P004', 4, 'S004', 'Pending', N'Hỏng mainboard', 'https://example.com/image4.jpg', GETDATE(), 'Yes', null),
('P005', 5, 'S005', 'Approved', N'Lỗi touchpad', 'https://example.com/image5.jpg', GETDATE(), 'No', null),
('P006', 6, 'S006', 'Pending', N'Không nhận sạc', 'https://example.com/image6.jpg', GETDATE(), 'Yes', null),
('P007', 7, 'S007', 'Rejected', N'Hỏng loa', 'https://example.com/image7.jpg', GETDATE(), 'No', null),
('P008', 8, 'S008', 'Approved', N'Lỗi phần mềm', 'https://example.com/image8.jpg', GETDATE(), 'Yes', null),
('P009', 9, 'S009', 'Pending', N'Máy bị treo', 'https://example.com/image9.jpg', GETDATE(), 'No', null),
('P010', 10, 'S010', 'Rejected', N'Lỗi card đồ họa', 'https://example.com/image10.jpg', GETDATE(), 'Yes', NULL);*/
