create database demo4

CREATE TABLE Roles (
    RoleID INT IDENTITY(1,1) PRIMARY KEY,
    RoleName NVARCHAR(50) NOT NULL UNIQUE -- Admin, Customer, Technician, ServiceManager
);
CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(50) UNIQUE NOT NULL,
    PasswordHash NVARCHAR(255) NOT NULL,
    RoleID INT NOT NULL FOREIGN KEY REFERENCES Roles(RoleID) -- Liên kết với bảng Roles
);
CREATE TABLE Staff (
    StaffID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL UNIQUE FOREIGN KEY REFERENCES Users(UserID),
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100),
    Phone NVARCHAR(15),
    Position NVARCHAR(50) NOT NULL -- Technician hoặc ServiceManager
);
CREATE TABLE Customers (
    CustomerID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL UNIQUE FOREIGN KEY REFERENCES Users(UserID),
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL,
    Phone NVARCHAR(15) NOT NULL
);

INSERT INTO Roles (RoleName) VALUES
('Admin'),
('Customer'),
('Technician'),
('ServiceManager');

SET IDENTITY_INSERT Users ON;
INSERT INTO Users (UserID, Username, PasswordHash, RoleID)
VALUES (0, 'admin', HASHBYTES('SHA2_256', 'admin123'), (SELECT RoleID FROM Roles WHERE RoleName = 'Admin'));
SET IDENTITY_INSERT Users OFF;

DECLARE @NewUserID INT;

-- Thêm tài khoản Technician
INSERT INTO Users (Username, PasswordHash, RoleID)
VALUES ('tech1', HASHBYTES('SHA2_256', 'tech123'), (SELECT RoleID FROM Roles WHERE RoleName = 'Technician'));

SET @NewUserID = SCOPE_IDENTITY();

INSERT INTO Staff (UserID, FullName, Email, Phone, Position)
VALUES (@NewUserID, 'Nguyen Van B', 'tech1@example.com', '0987654321', 'Technician');

-- Thêm tài khoản ServiceManager
INSERT INTO Users (Username, PasswordHash, RoleID)
VALUES ('manager1', HASHBYTES('SHA2_256', 'manager123'), (SELECT RoleID FROM Roles WHERE RoleName = 'ServiceManager'));

SET @NewUserID = SCOPE_IDENTITY();

INSERT INTO Staff (UserID, FullName, Email, Phone, Position)
VALUES (@NewUserID, 'Tran Van C', 'manager1@example.com', '0345678912', 'ServiceManager');



-- Đặt CREATE PROCEDURE trong một batch riêng biệt
GO
CREATE PROCEDURE AddCustomer
    @Username NVARCHAR(50),
    @Password NVARCHAR(50),
    @FullName NVARCHAR(100),
    @Email NVARCHAR(100),
    @Phone NVARCHAR(15)
AS
BEGIN
    DECLARE @NewUserID INT;

    -- Thêm tài khoản vào bảng Users
    INSERT INTO Users (Username, PasswordHash, RoleID)
    VALUES (@Username, HASHBYTES('SHA2_256', @Password), (SELECT RoleID FROM Roles WHERE RoleName = 'Customer'));

    -- Lấy UserID vừa tạo
    SET @NewUserID = SCOPE_IDENTITY();

    -- Thêm thông tin khách hàng vào bảng Customers
    INSERT INTO Customers (UserID, FullName, Email, Phone)
    VALUES (@NewUserID, @FullName, @Email, @Phone);
END;
GO


EXEC AddCustomer 'customer1', 'password123', 'Nguyen Van A', 'nguyenvana@example.com', '0912345678';
EXEC AddCustomer 'customer2', 'password123', 'Tran Thi B', 'tranthib@example.com', '0923456789';
EXEC AddCustomer 'customer3', 'password123', 'Le Van C', 'levanc@example.com', '0934567890';
EXEC AddCustomer 'customer4', 'password123', 'Hoang Thi D', 'hoangthid@example.com', '0945678901';
EXEC AddCustomer 'customer5', 'password123', 'Phan Van E', 'phanvane@example.com', '0956789012';
EXEC AddCustomer 'customer6', 'password123', 'Nguyen Thi F', 'nguyenthif@example.com', '0967890123';
EXEC AddCustomer 'customer7', 'password123', 'Tran Van G', 'tranvang@example.com', '0978901234';
EXEC AddCustomer 'customer8', 'password123', 'Le Thi H', 'lethih@example.com', '0989012345';
EXEC AddCustomer 'customer9', 'password123', 'Hoang Van I', 'hoangvani@example.com', '0990123456';
EXEC AddCustomer 'customer10', 'password123', 'Pham Thi J', 'phamthij@example.com', '0901234567';


SELECT * FROM Customers;
