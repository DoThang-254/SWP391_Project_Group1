create database WarrantyManagement3

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
    RequirementId INT PRIMARY KEY,  
    StartDate DATE,
    EndDate DATE,
    Status VARCHAR(255),
    VerificationCode VARCHAR(255),
    VerificationMethod VARCHAR(255),
    Verified CHAR(1),
    Price FLOAT(10),
    InvoiceId INT UNIQUE,  
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
    StaffTime TIME(7),
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
);
