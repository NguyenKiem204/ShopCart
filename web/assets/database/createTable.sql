CREATE DATABASE ShoppingCart
go
USE SHoppingCart
-- Tạo bảng Categories để phân cấp các loại sản phẩm
CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY IDENTITY(1,1),
    CategoryName NVARCHAR(100),
    ParentCategoryID INT NULL,
    FOREIGN KEY (ParentCategoryID) REFERENCES Categories(CategoryID)
);

CREATE TABLE Users (
    UserID VARCHAR(36) PRIMARY KEY DEFAULT NEWID(), -- Tạo UUID ngẫu nhiên cho mỗi bản ghi
    FirstName NVARCHAR(50),
    LastName NVARCHAR(50),
    Email NVARCHAR(100),
    PasswordHash VARCHAR(255), -- Giữ nguyên kiểu VARCHAR cho mật khẩu
    PhoneNumber NVARCHAR(20),
    Address NVARCHAR(255),
    City NVARCHAR(100),
    PostalCode NVARCHAR(20),
    Country NVARCHAR(100),
    Role VARCHAR(10) -- Cột mới cho vai trò người dùng
);

-- Tạo bảng Products để lưu thông tin sản phẩm
CREATE TABLE Products (
    ProductID INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(255),
    Description NVARCHAR(MAX), -- Sử dụng NVARCHAR(MAX) cho mô tả
    Price DECIMAL(10, 2),
    Stock INT,
    CategoryID INT,
    ImageURL NVARCHAR(255), -- Thay đổi sang NVARCHAR
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

-- Tạo bảng Orders để lưu thông tin đơn hàng
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY IDENTITY(1,1),
    UserID VARCHAR(36), -- Đổi thành VARCHAR(36)
    OrderDate DATETIME DEFAULT GETDATE(),
    Status NVARCHAR(50), -- Thay đổi sang NVARCHAR
    ShippingAddress NVARCHAR(255), -- Thay đổi sang NVARCHAR
    TotalAmount DECIMAL(10, 2),
    PaymentMethod NVARCHAR(50), -- Thay đổi sang NVARCHAR
    FOREIGN KEY (UserID) REFERENCES Users(UserID) -- Cập nhật ràng buộc
);

-- Tạo bảng OrderItems để lưu chi tiết sản phẩm trong đơn hàng
CREATE TABLE OrderItems (
    OrderItemID INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT,
    ProductID INT,
    Quantity INT,
    Price DECIMAL(10, 2),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Tạo bảng Carts để lưu thông tin giỏ hàng của khách hàng
CREATE TABLE Carts (
    CartID INT PRIMARY KEY IDENTITY(1,1),
    UserID VARCHAR(36), -- Đổi thành VARCHAR(36)
    FOREIGN KEY (UserID) REFERENCES Users(UserID) -- Cập nhật ràng buộc
);

-- Tạo bảng CartItems để lưu chi tiết sản phẩm trong giỏ hàng tạm thời
CREATE TABLE CartItems (
    CartItemID INT PRIMARY KEY IDENTITY(1,1),
    CartID INT,
    ProductID INT,
    Quantity INT,
    FOREIGN KEY (CartID) REFERENCES Carts(CartID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Tạo bảng Payments để lưu thông tin thanh toán
CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT,
    PaymentDate DATETIME DEFAULT GETDATE(),
    PaymentAmount DECIMAL(10, 2),
    PaymentMethod NVARCHAR(50), -- Thay đổi sang NVARCHAR
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

-- Tạo bảng Shipping để lưu thông tin vận chuyển
CREATE TABLE Shipping (
    ShippingID INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT,
    ShippingMethod NVARCHAR(50), -- Thay đổi sang NVARCHAR
    ShippingDate DATETIME DEFAULT GETDATE(),
    DeliveryDate DATETIME,
    ShippingAddress NVARCHAR(255), -- Thay đổi sang NVARCHAR
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);
