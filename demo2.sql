CREATE DATABASE demo5;
GO
USE demo5;
GO

-- Bảng Roles
CREATE TABLE Roles (
    RoleID INT IDENTITY(1,1) PRIMARY KEY,
    RoleName NVARCHAR(50) NOT NULL UNIQUE -- Admin, Technician, ServiceManager
);

-- Bảng Staff
CREATE TABLE Staff (
    StaffID INT IDENTITY(1,1) PRIMARY KEY,
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100),
    Phone NVARCHAR(15),
    Position NVARCHAR(50) NOT NULL, -- Technician hoặc ServiceManager
    Password NVARCHAR(255) NOT NULL, -- Mật khẩu
    RoleID INT NOT NULL FOREIGN KEY REFERENCES Roles(RoleID) -- Liên kết với bảng Roles
);

-- Bảng Customers
CREATE TABLE Customers (
    CustomerID INT IDENTITY(1,1) PRIMARY KEY,
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL,
    Phone NVARCHAR(15) NOT NULL,
    Password NVARCHAR(255) NOT NULL -- Mật khẩu
);
