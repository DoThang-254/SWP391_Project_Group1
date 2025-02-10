
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
INSERT INTO Customer (Username, Password, FirstName, LastName, Email, Gender, BirthDate, Status, Address)
VALUES 
    ('cust1', 'password123', 'Alice', 'Johnson', 'alice.j@example.com', 'Female', '1995-12-05', 'Active', '123 Main Street'),
    ('cust2', 'password123', 'Bob', 'Williams', 'bob.w@example.com', 'Male', '1988-03-25', 'Active', '456 Elm Street');
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


