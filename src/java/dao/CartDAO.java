/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Cart;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author nkiem
 */
public class CartDAO implements DAOInterface<Cart, String>{

    @Override
    public int insert(Cart cart) {
        int row = 0;
        String sql = "INSERT INTO Carts (UserID) VALUES (?)";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cart.getUserID());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public int update(Cart cart) {
        int row = 0;
        String sql = "UPDATE Carts SET UserID = ? WHERE CartID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cart.getUserID());
            ps.setString(2, cart.getCartID());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int delete(Cart cart) {
        int row = 0;
        String sql = "DELETE FROM Carts WHERE CartID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cart.getCartID());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<Cart> selectAll() {
        List<Cart> list = new ArrayList<>();
        String sql = "SELECT * FROM Carts";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String cartID = rs.getString("CartID");
                String userID = rs.getString("UserID");
                Cart cart = new Cart(cartID, userID);
                list.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Cart selectById(String id) {
        Cart cart = null;
        String sql = "SELECT * FROM Carts WHERE CartID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String cartID = rs.getString("CartID");
                    String userID = rs.getString("UserID");
                    cart = new Cart(cartID, userID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
    public Cart findCartByUserID(String id) {
        Cart cart = null;
        String sql = "SELECT * FROM Carts WHERE userID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String cartID = rs.getString("CartID");
                    String userID = rs.getString("UserID");
                    cart = new Cart(cartID, userID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
    public Cart getCartByUserID(String userID) {
        Cart cart = null;
        String sql = "SELECT CartID FROM Carts WHERE UserID = ?";
        try (Connection connection = DBContext.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(sql)) {
             
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cart = new Cart(userID);
                cart.setCartID(rs.getString("CartID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
    @Override
    public List<Cart> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
