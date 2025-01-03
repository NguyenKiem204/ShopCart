package dao;
import entity.Order;
import entity.ProductCheckOut;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author nkiem
 */


public class OrderDAO implements DAOInterface<Order, String>{

    @Override
    public int insert(Order order) {
        int row = 0;
        String sql = "INSERT INTO Orders (OrderID, UserID, OrderDate, Status, ShippingAddress, TotalAmount, PaymentMethod) VALUES (?, ?, ?, ?, ?, ?, ?)";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, order.getOrderID());
            ps.setString(2, order.getUserID());
            ps.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setString(4, order.getStatus());
            ps.setString(5, order.getShippingAddress());
            ps.setBigDecimal(6, order.getTotalAmount());
            ps.setString(7, order.getPaymentMethod());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public int update(Order order) {
        int row = 0;
        String sql = "UPDATE Orders SET UserID = ?, OrderDate = ?, Status = ?, ShippingAddress = ?, TotalAmount = ?, PaymentMethod = ? WHERE OrderID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, order.getUserID());
            ps.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setString(3, order.getStatus());
            ps.setString(4, order.getShippingAddress());
            ps.setBigDecimal(5, order.getTotalAmount());
            ps.setString(6, order.getPaymentMethod());
            ps.setString(7, order.getOrderID());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    public int delete(String orderID) {
        int row = 0;
        String sql = "DELETE FROM Orders WHERE OrderID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, orderID);
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<Order> selectAll() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getString("OrderID"));
                order.setUserID(rs.getString("UserID"));
                order.setOrderDate(rs.getDate("OrderDate"));
                order.setStatus(rs.getString("Status"));
                order.setShippingAddress(rs.getString("ShippingAddress"));
                order.setTotalAmount(rs.getBigDecimal("TotalAmount"));
                order.setPaymentMethod(rs.getString("PaymentMethod"));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Order selectById(String orderID) {
        Order order = null;
        String sql = "SELECT * FROM Orders WHERE OrderID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, orderID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setOrderID(rs.getString("OrderID"));
                    order.setUserID(rs.getString("UserID"));
                    order.setOrderDate(rs.getDate("OrderDate"));
                    order.setStatus(rs.getString("Status"));
                    order.setShippingAddress(rs.getString("ShippingAddress"));
                    order.setTotalAmount(rs.getBigDecimal("TotalAmount"));
                    order.setPaymentMethod(rs.getString("PaymentMethod"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public int delete(Order t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public List<Order> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
      public List<ProductCheckOut> getProductCheckOuts(String userId) {
    List<ProductCheckOut> productCheckOutList = new ArrayList<>();
    Map<String, ProductCheckOut> productMap = new HashMap<>();
    
    String query = "SELECT " +
            "oi.ProductID, " +
            "oi.Quantity, " +
            "o.TotalAmount AS TotalPrice, " +
            "p.ImageURL, " +
            "u.UserID AS SellerID, " +
            "u.FirstName + ' ' + u.LastName AS SellerName, " +
            "p.Name AS ProductName, " +
            "p.OriginalPrice, " +
            "p.CurrentPrice " +
            "FROM Orders o " +
            "JOIN OrderItems oi ON o.OrderID = oi.OrderID " +
            "JOIN Products p ON oi.ProductID = p.ProductID " +
            "JOIN Users u ON p.SellerID = u.UserID " +
            "WHERE o.UserID = ?";

    try (Connection connection = DBContext.getConnection(); 
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, userId);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                int quantity = rs.getInt("Quantity");
                BigDecimal totalPrice = rs.getBigDecimal("TotalPrice");

                if (productMap.containsKey(productID)) {
                    ProductCheckOut existingProduct = productMap.get(productID);
                    existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
                    existingProduct.setTotalPrice(existingProduct.getTotalPrice().add(totalPrice));
                } else {
                    ProductCheckOut productCheckOut = new ProductCheckOut();
                    productCheckOut.setProductID(productID);
                    productCheckOut.setQuantity(quantity);
                    productCheckOut.setTotalPrice(totalPrice);
                    productCheckOut.setImageURL(rs.getString("ImageURL"));
                    productCheckOut.setSellerID(rs.getString("SellerID"));
                    productCheckOut.setSellerName(rs.getString("SellerName"));
                    productCheckOut.setProductName(rs.getString("ProductName"));
                    productCheckOut.setOriginalPrice(rs.getBigDecimal("OriginalPrice"));
                    productCheckOut.setCurrentPrice(rs.getBigDecimal("CurrentPrice"));

                    productMap.put(productID, productCheckOut);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    productCheckOutList.addAll(productMap.values());
    return productCheckOutList;
}
}

