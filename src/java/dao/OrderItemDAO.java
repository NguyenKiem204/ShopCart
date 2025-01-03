/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nkiem
 */
public class OrderItemDAO implements DAOInterface<OrderItem, String>{
    @Override
    public int insert(OrderItem orderItem) {
        int row = 0;
        String sql = "INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity, Price) VALUES (?, ?, ?, ?, ?)";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, orderItem.getOrderItemID());
            ps.setString(2, orderItem.getOrderID());
            ps.setString(3, orderItem.getProductID());
            ps.setInt(4, orderItem.getQuantity());
            ps.setBigDecimal(5, orderItem.getPrice());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

   @Override
    public int update(OrderItem orderItem) {
        int row = 0;
        String sql = "UPDATE OrderItems SET OrderID = ?, ProductID = ?, Quantity = ?, Price = ? WHERE OrderItemID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, orderItem.getOrderID());
            ps.setString(2, orderItem.getProductID());
            ps.setInt(3, orderItem.getQuantity());
            ps.setBigDecimal(4, orderItem.getPrice());
            ps.setString(5, orderItem.getOrderItemID());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    public int delete(String orderItemID) {
        int row = 0;
        String sql = "DELETE FROM OrderItems WHERE OrderItemID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, orderItemID);
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<OrderItem> selectAll() {
        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT * FROM OrderItems";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderItemID(rs.getString("OrderItemID"));
                orderItem.setOrderID(rs.getString("OrderID"));
                orderItem.setProductID(rs.getString("ProductID"));
                orderItem.setQuantity(rs.getInt("Quantity"));
                orderItem.setPrice(rs.getBigDecimal("Price"));
                list.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public OrderItem selectById(String orderItemID) {
        OrderItem orderItem = null;
        String sql = "SELECT * FROM OrderItems WHERE OrderItemID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, orderItemID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    orderItem = new OrderItem();
                    orderItem.setOrderItemID(rs.getString("OrderItemID"));
                    orderItem.setOrderID(rs.getString("OrderID"));
                    orderItem.setProductID(rs.getString("ProductID"));
                    orderItem.setQuantity(rs.getInt("Quantity"));
                    orderItem.setPrice(rs.getBigDecimal("Price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public int delete(OrderItem t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OrderItem> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
