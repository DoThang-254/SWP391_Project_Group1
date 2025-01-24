CREATE TABLE Role (
    RoleId INT PRIMARY KEY,
    RoleName VARCHAR(255) NOT NULL
);

CREATE TABLE Staff (
    StaffId VARCHAR(255) PRIMARY KEY,
    Username VARCHAR(255) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Email VARCHAR(255),
    Phone VARCHAR(255),
    Gender VARCHAR(255),
    BirthDate DATE,
    Status VARCHAR(255),
    RoleId INT,
    FOREIGN KEY (RoleId) REFERENCES Role(RoleId)
);

CREATE TABLE Customer (
    CustomerId INT PRIMARY KEY,
    Username VARCHAR(100) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Email VARCHAR(255),
    Gender VARCHAR(255),
    BirthDate DATE,
    Status VARCHAR(255),
    Address VARCHAR(255)
);

CREATE TABLE Product (
    ProductId VARCHAR(255) PRIMARY KEY,
    ProductName VARCHAR(255),
    WarrantyDateTime DATE,
    Price FLOAT(10),
    Brand VARCHAR(255),
    BuyTime DATE,
    CustomerId INT,
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)
);

CREATE TABLE Invoice (
    InvoiceId INT PRIMARY KEY,
    Price FLOAT(10),
    Status VARCHAR(255),
    Note VARCHAR(255),
    FormId INT UNIQUE
);

CREATE TABLE Component (
    ComponentId INT PRIMARY KEY,
    ComponentName VARCHAR(255),
    Brand VARCHAR(255),
    Status VARCHAR(255),
    Price FLOAT(10),
    Amount INT,
    StaffId VARCHAR(255),
    InvoiceId INT,
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId),
    FOREIGN KEY (InvoiceId) REFERENCES Invoice(InvoiceId)
);


CREATE TABLE WarrantyRequirement (
    RequirementId INT PRIMARY KEY,
    Status VARCHAR(255),
    Description VARCHAR(255),
    RegisterDate DATE,
    CustomerId INT,
    StaffId VARCHAR(255),
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
);

CREATE TABLE WarrantyForm (
    FormId INT PRIMARY KEY,
    StartDate DATE,
    EndDate DATE,
    Status VARCHAR(255),
    RequirementId INT Unique,
    VerificationCode VARCHAR(255),
    VerificationMethod VARCHAR(255),
    Verified CHAR(1),
    Price FLOAT(10),
    InvoiceId INT unique, 
    FOREIGN KEY (RequirementId) REFERENCES WarrantyRequirement(RequirementId),
    FOREIGN KEY (InvoiceId) REFERENCES Invoice(InvoiceId) 
);


CREATE TABLE Notification (
    NotificationId INT PRIMARY KEY,
    CustomerId INT,
    RequirementId INT,
    Title VARCHAR(255),
    Message VARCHAR(255),
    IsRead CHAR(1),
    SendTime DATE,
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
    FOREIGN KEY (RequirementId) REFERENCES WarrantyRequirement(RequirementId)
);

CREATE TABLE WarrantyInformation (
    InformationId INT PRIMARY KEY,
    Status VARCHAR(255),
    Note VARCHAR(255),
    ReturnDate DATE,
    ProductId VARCHAR(255),
    StaffId VARCHAR(255),
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId),
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
);

CREATE TABLE Schedule (
    ScheduleId INT PRIMARY KEY,
    StartTime TIME(7),
    EndTime TIME(7),
    Date DATE,
    StaffId VARCHAR(255),
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
);

CREATE TABLE Report (
    ReportId INT PRIMARY KEY,
    Comment VARCHAR(255),
    CustomerId INT,
    InformationId INT,
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
    FOREIGN KEY (InformationId) REFERENCES WarrantyInformation(InformationId)
);

CREATE TABLE Blog (
    BlogId INT PRIMARY KEY,
    Content VARCHAR(255),
    StaffId VARCHAR(255),
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
)

-- Insert vào bảng Role
INSERT INTO Role (RoleId, RoleName) 
VALUES 
(1, 'Admin'), 
(2, 'Technician'), 
(3, 'Customer Support');

-- Insert vào bảng Staff
INSERT INTO Staff (StaffId, Username, Password, FirstName, LastName, Email, Phone, Gender, BirthDate, Status, RoleId)
VALUES 
('S001', 'admin', 'password123', 'John', 'Doe', 'john.doe@example.com', '0123456789', 'Male', '1985-05-15', 'Active', 1),
('S002', 'tech1', 'techpass', 'Alice', 'Smith', 'alice.smith@example.com', '0987654321', 'Female', '1990-07-22', 'Active', 2),
('S003', 'support1', 'supportpass', 'Bob', 'Brown', 'bob.brown@example.com', '0223344556', 'Male', '1988-10-01', 'Active', 3);

-- Insert vào bảng Customer
INSERT INTO Customer (CustomerId, Username, Password, FirstName, LastName, Email, Gender, BirthDate, Status, Address)
VALUES 
(1, 'customer1', 'custpass1', 'Emma', 'Williams', 'emma.w@example.com', 'Female', '1992-03-12', 'Active', '123 Main St'),
(2, 'customer2', 'custpass2', 'Liam', 'Johnson', 'liam.j@example.com', 'Male', '1995-08-25', 'Active', '456 Elm St');

-- Insert vào bảng Product
INSERT INTO Product (ProductId, ProductName, WarrantyDateTime, Price, Brand, BuyTime, CustomerId)
VALUES 
('P001', 'Laptop X1', '2025-01-15', 1200.00, 'BrandA', '2023-01-10', 1),
('P002', 'Laptop Pro', '2024-12-20', 1500.00, 'BrandB', '2023-06-15', 2);

-- Insert vào bảng Invoice
INSERT INTO Invoice (InvoiceId, Price, Status, Note, FormId)
VALUES 
(1, 150.00, 'Paid', 'Replacement part', 1),
(2, 200.00, 'Pending', 'Battery issue', 2);

-- Insert vào bảng Component
INSERT INTO Component (ComponentId, ComponentName, Brand, Status, Price, Amount, StaffId, InvoiceId)
VALUES 
(1, 'Battery', 'BrandA', 'Available', 50.00, 10, 'S002', 1),
(2, 'Keyboard', 'BrandB', 'Out of Stock', 70.00, 0, 'S002', 2);

-- Insert vào bảng WarrantyRequirement
INSERT INTO WarrantyRequirement (RequirementId, Status, Description, RegisterDate, CustomerId, StaffId)
VALUES 
(1, 'Received', 'Screen malfunction', '2025-01-10', 1, 'S003'),
(2, 'Pending', 'Battery issue', '2025-01-12', 2, 'S003');

-- Insert vào bảng WarrantyForm
INSERT INTO WarrantyForm (FormId, StartDate, EndDate, Status, RequirementId, VerificationCode, VerificationMethod, Verified, Price, InvoiceId)
VALUES 
(1, '2025-01-11', '2025-02-11', 'Active', 1, 'CODE123', 'Email', 'Y', 0.00, 1),
(2, '2025-01-13', '2025-02-13', 'Pending', 2, 'CODE456', 'Phone', 'N', 200.00, 2);

-- Insert vào bảng Notification
INSERT INTO Notification (NotificationId, CustomerId, RequirementId, Title, Message, IsRead, SendTime)
VALUES 
(1, 1, 1, 'Warranty Received', 'Your warranty request has been received.', 'N', '2025-01-11'),
(2, 2, 2, 'Action Required', 'Your warranty request is pending approval.', 'N', '2025-01-13');

-- Insert vào bảng WarrantyInformation
INSERT INTO WarrantyInformation (InformationId, Status, Note, ReturnDate, ProductId, StaffId)
VALUES 
(1, 'Completed', 'Screen replaced', '2025-01-20', 'P001', 'S002'),
(2, 'In Progress', 'Battery replacement', NULL, 'P002', 'S002');

-- Insert vào bảng Schedule
INSERT INTO Schedule (ScheduleId, StaffTime, EndTime, Date, StaffId)
VALUES 
(1, '09:00:00', '17:00:00', '2025-01-24', 'S002'),
(2, '10:00:00', '18:00:00', '2025-01-25', 'S003');

-- Insert vào bảng Report
INSERT INTO Report (ReportId, Comment, CustomerId, InformationId)
VALUES 
(1, 'Great service!', 1, 1),
(2, 'Waiting too long.', 2, 2);

-- Insert vào bảng Blog
INSERT INTO Blog (BlogId, Content, StaffId)
VALUES 
(1, 'How to maintain your laptop.', 'S003'),
(2, 'Common laptop issues and fixes.', 'S002');
