create database demo1 


-- Bảng Users (Tất cả các loại tài khoản)
CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(50) UNIQUE NOT NULL,
    PasswordHash NVARCHAR(255) NOT NULL,
    Role NVARCHAR(20) NOT NULL -- Admin, Customer, Technician, ServiceManager
);

-- Thêm Admin với UserID = 0
SET IDENTITY_INSERT Users ON;
INSERT INTO Users (UserID, Username, PasswordHash, Role)
VALUES (0, 'admin', HASHBYTES('SHA2_256', 'admin123'), 'Admin');
SET IDENTITY_INSERT Users OFF;

-- Bảng Customers (Thông tin khách hàng)
CREATE TABLE Customers (
    CustomerID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL FOREIGN KEY REFERENCES Users(UserID),
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL,
    Phone NVARCHAR(15) NOT NULL
);

-- Bảng Technicians (Thông tin kỹ thuật viên)
CREATE TABLE Technicians (
    TechnicianID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL FOREIGN KEY REFERENCES Users(UserID),
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100),
    Phone NVARCHAR(15)
);

-- Bảng ServiceManagers (Thông tin quản lý dịch vụ)
CREATE TABLE ServiceManagers (
    ServiceManagerID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL FOREIGN KEY REFERENCES Users(UserID),
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL,
    Phone NVARCHAR(15) NOT NULL
);
