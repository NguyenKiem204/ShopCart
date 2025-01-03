-- Thêm dữ liệu vào bảng Categories
INSERT INTO Categories (CategoryName, ParentCategoryID) VALUES
('Điện tử', NULL),
('Máy tính', 1),
('Điện thoại di động', 1),
('Thiết bị gia dụng', NULL),
('Thiết bị nhà bếp', 4);

-- Thêm dữ liệu vào bảng Users
INSERT INTO Users (FirstName, LastName, Email, PasswordHash, PhoneNumber, Address, City, PostalCode, Country, Role) VALUES
('Nguyễn', 'Văn A', 'nguyen.a@example.com', 'hashedpassword123', '+84 123 456 789', '123 Đường A', 'Hà Nội', '100000', 'Việt Nam', 'Admin'),
('Trần', 'Thị B', 'tran.b@example.com', 'hashedpassword456', '+84 987 654 321', '456 Đường B', 'Thành phố Hồ Chí Minh', '700000', 'Việt Nam', 'User'),
('Lê', 'Công C', 'le.c@example.com', 'hashedpassword789', '+84 543 210 987', '789 Đường C', 'Đà Nẵng', '500000', 'Việt Nam', 'User');

-- Thêm dữ liệu vào bảng Products
INSERT INTO Products (Name, Description, Price, Stock, CategoryID, ImageURL) VALUES
('Laptop', 'Laptop hiệu suất cao', 1200000.00, 50, 2, 'laptop.jpg'),
('Điện thoại thông minh', 'Mẫu điện thoại thông minh mới nhất', 800000.00, 100, 3, 'smartphone.jpg'),
('Lò vi sóng', 'Lò vi sóng mạnh mẽ', 200000.00, 30, 4, 'microwave.jpg'),
('Máy xay sinh tố', 'Máy xay sinh tố tốc độ cao', 150000.00, 20, 5, 'blender.jpg');

-- Thêm dữ liệu vào bảng Orders
INSERT INTO Orders (UserID, OrderDate, Status, ShippingAddress, TotalAmount, PaymentMethod) VALUES
((SELECT UserID FROM Users WHERE FirstName = 'Nguyễn' AND LastName = 'Văn A'), GETDATE(), 'Đang xử lý', '123 Đường A, Hà Nội', 1200000.00, 'Thẻ tín dụng'),
((SELECT UserID FROM Users WHERE FirstName = 'Trần' AND LastName = 'Thị B'), GETDATE(), 'Đang giao', '456 Đường B, Thành phố Hồ Chí Minh', 800000.00, 'Tiền mặt');

-- Thêm dữ liệu vào bảng OrderItems
INSERT INTO OrderItems (OrderID, ProductID, Quantity, Price) VALUES
(1, 1, 1, 1200000.00),
(1, 2, 2, 1600000.00),
(2, 2, 1, 800000.00);

-- Thêm dữ liệu vào bảng Carts
INSERT INTO Carts (UserID) VALUES
((SELECT UserID FROM Users WHERE FirstName = 'Nguyễn' AND LastName = 'Văn A')),
((SELECT UserID FROM Users WHERE FirstName = 'Trần' AND LastName = 'Thị B'));

-- Thêm dữ liệu vào bảng CartItems
INSERT INTO CartItems (CartID, ProductID, Quantity) VALUES
(1, 1, 1),
(1, 2, 2),
(2, 3, 1);

-- Thêm dữ liệu vào bảng Payments
INSERT INTO Payments (OrderID, PaymentDate, PaymentAmount, PaymentMethod) VALUES
(1, GETDATE(), 1200000.00, 'Thẻ tín dụng'),
(2, GETDATE(), 800000.00, 'Tiền mặt');

-- Thêm dữ liệu vào bảng Shipping
INSERT INTO Shipping (OrderID, ShippingMethod, ShippingDate, DeliveryDate, ShippingAddress) VALUES
(1, 'Giao hàng nhanh', GETDATE(), DATEADD(DAY, 3, GETDATE()), '123 Đường A, Hà Nội'),
(2, 'Giao hàng tiêu chuẩn', GETDATE(), DATEADD(DAY, 5, GETDATE()), '456 Đường B, Thành phố Hồ Chí Minh');
