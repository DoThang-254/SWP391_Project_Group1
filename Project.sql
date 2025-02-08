CREATE TABLE Role (
    RoleId INT IDENTITY(1,1) PRIMARY KEY,
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
    CustomerId INT IDENTITY(1,1) PRIMARY KEY,
    Username VARCHAR(100) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
	Phone VARCHAR(255),
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
    InvoiceId INT IDENTITY(1,1) PRIMARY KEY,
    Price FLOAT,
    Status VARCHAR(255),
    Note VARCHAR(255),
    FormId INT UNIQUE
);

CREATE TABLE Component (
    ComponentId INT IDENTITY(1,1) PRIMARY KEY,
    ComponentName VARCHAR(255),
    Brand VARCHAR(255),
    Status VARCHAR(255),
    Price FLOAT,
    Amount INT,
    StaffId VARCHAR(255),
    InvoiceId INT,
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId),
    FOREIGN KEY (InvoiceId) REFERENCES Invoice(InvoiceId)
);


CREATE TABLE WarrantyRequirement (
    RequirementId INT IDENTITY(1,1) PRIMARY KEY,
    Status VARCHAR(255),
    Description VARCHAR(255),
    RegisterDate DATE,
    CustomerId INT,
    StaffId VARCHAR(255),
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
);

CREATE TABLE WarrantyForm (
    FormId INT IDENTITY(1,1) PRIMARY KEY,
    StartDate DATE,
    EndDate DATE,
    Status VARCHAR(255),
    RequirementId INT UNIQUE,
    VerificationCode VARCHAR(255),
    VerificationMethod VARCHAR(255),
    Verified CHAR(1),
    Price FLOAT,
    InvoiceId INT UNIQUE, 
    FOREIGN KEY (RequirementId) REFERENCES WarrantyRequirement(RequirementId),
    FOREIGN KEY (InvoiceId) REFERENCES Invoice(InvoiceId) 
);


CREATE TABLE Notification (
    NotificationId INT IDENTITY(1,1) PRIMARY KEY,
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
    InformationId INT IDENTITY(1,1) PRIMARY KEY,
    Status VARCHAR(255),
    Note VARCHAR(255),
    ReturnDate DATE,
    ProductId VARCHAR(255),
    StaffId VARCHAR(255),
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId),
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
);

CREATE TABLE Schedule (
    ScheduleId INT IDENTITY(1,1) PRIMARY KEY,
    StartTime TIME(7),
    EndTime TIME(7),
    Date DATE,
    StaffId VARCHAR(255),
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
);

CREATE TABLE Report (
    ReportId INT IDENTITY(1,1) PRIMARY KEY,
    Comment VARCHAR(255),
    CustomerId INT,
    InformationId INT,
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
    FOREIGN KEY (InformationId) REFERENCES WarrantyInformation(InformationId)
);

CREATE TABLE Blog (
    BlogId INT IDENTITY(1,1) PRIMARY KEY,
    Content VARCHAR(255),
    StaffId VARCHAR(255),
    FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
);

INSERT INTO Role (RoleName)
VALUES 
    ('Admin'),
    ('Technician'),
    ('Customer Service');
INSERT INTO Staff (StaffId, Username, Password, FirstName, LastName, Email, Phone, Gender, BirthDate, Status, RoleId)
VALUES 
    ('S001', 'admin1', 'password123', 'John', 'Doe', 'admin1@example.com', '0123456789', 'Male', '1985-05-15', 'Active', 1),
    ('S002', 'tech1', 'password123', 'Jane', 'Smith', 'tech1@example.com', '0987654321', 'Female', '1990-07-20', 'Active', 2),
    ('S003', 'service1', 'password123', 'Emily', 'Brown', 'service1@example.com', '0112233445', 'Female', '1992-10-10', 'Active', 3);
INSERT INTO Customer (Username, Password, FirstName, LastName, Phone, Email, Gender, BirthDate, Status, Address)
VALUES 
('user1', 'password123', 'John', 'Doe', '0123456789', 'john.doe@example.com', 'Male', '1990-05-15', 'Active', '123 Main St'),
('user2', 'password456', 'Jane', 'Smith', '0987654321', 'jane.smith@example.com', 'Female', '1995-08-25', 'Active', '456 Elm St');

INSERT INTO Product (ProductId, ProductName, WarrantyDateTime, Price, Brand, BuyTime, CustomerId)
VALUES 
    ('P001', 'Laptop A', '2025-01-01', 1500.00, 'BrandX', '2024-01-01', 1),
    ('P002', 'Laptop B', '2025-06-01', 1200.00, 'BrandY', '2024-06-01', 2);
INSERT INTO Invoice (Price, Status, Note, FormId)
VALUES 
    (200.00, 'Paid', 'Warranty fee', 1), 
    (100.00, 'Pending', 'Component replacement', 2);
INSERT INTO Component (ComponentName, Brand, Status, Price, Amount, StaffId, InvoiceId)
VALUES 
    ('Battery', 'BrandX', 'In Stock', 50.00, 10, 'S002', 1),
    ('Keyboard', 'BrandY', 'In Stock', 30.00, 5, 'S002', 2);
INSERT INTO WarrantyRequirement (Status, Description, RegisterDate, CustomerId, StaffId)
VALUES 
    ('Pending', 'Laptop not powering on', '2025-01-15', 1, 'S003'), 
    ('Approved', 'Screen flickering issue', '2025-01-20', 2, 'S002'); 
INSERT INTO WarrantyForm (StartDate, EndDate, Status, RequirementId, VerificationCode, VerificationMethod, Verified, Price, InvoiceId)
VALUES 
    ('2025-01-15', '2025-01-30', 'Active', 1, 'ABC123', 'Email', 'Y', 0.00, 1),
    ('2025-01-20', '2025-02-10', 'Pending', 2, 'XYZ789', 'SMS', 'N', 50.00, 2);
INSERT INTO Notification (CustomerId, RequirementId, Title, Message, IsRead, SendTime)
VALUES 
    (1, 1, 'Warranty Request Received', 'Your warranty request has been received and is being processed.', 'N', '2025-01-15'),
    (2, 2, 'Warranty Request Approved', 'Your warranty request has been approved.', 'N', '2025-01-20');
INSERT INTO WarrantyInformation (Status, Note, ReturnDate, ProductId, StaffId)
VALUES 
    ('In Progress', 'Diagnosing issue', NULL, 'P001', 'S002'),
    ('Completed', 'Screen replaced', '2025-01-22', 'P002', 'S002');
INSERT INTO Schedule (StartTime, EndTime, Date, StaffId)
VALUES 
    ('09:00:00', '17:00:00', '2025-01-25', 'S002'),
    ('10:00:00', '18:00:00', '2025-01-26', 'S003');
INSERT INTO Report (Comment, CustomerId, InformationId)
VALUES 
    ('Service was quick and efficient.', 1, 1),
    ('Satisfied with the replacement.', 2, 2);
INSERT INTO Blog (Content, StaffId)
VALUES 
    ('Tips to maintain your laptop battery.', 'S003'),
    ('How to clean your keyboard safely.', 'S002');


