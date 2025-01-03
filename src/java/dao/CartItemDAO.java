/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.CartDetail;
import entity.CartItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nkiem
 */
public class CartItemDAO implements DAOInterface<CartItem, String> {

    @Override
    public int insert(CartItem cartItem) {
        int row = 0;
        String sql = "INSERT INTO CartItems (CartID, ProductID, Quantity) VALUES (?, ?, ?)";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cartItem.getCartID());
            ps.setString(2, cartItem.getProductID());
            ps.setInt(3, cartItem.getQuantity());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public int update(CartItem cartItem) {
        int row = 0;
        String sql = "UPDATE CartItems SET CartID = ?, ProductID = ?, Quantity = ? WHERE CartItemID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cartItem.getCartID());
            ps.setString(2, cartItem.getProductID());
            ps.setInt(3, cartItem.getQuantity());
            ps.setString(4, cartItem.getCartItemID());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int delete(CartItem cartItem) {
        int row = 0;
        String sql = "DELETE FROM CartItems WHERE CartItemID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cartItem.getCartItemID());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    public int getCartItemCountByUserID(String userID) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM CartItems ci "
                + "JOIN Carts c ON ci.CartID = c.CartID "
                + "WHERE c.UserID = ?";
        System.out.println(sql); // Log SQL để kiểm tra
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userID); // Thiết lập giá trị cho tham số userID
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1); // Lấy giá trị từ cột đầu tiên
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<CartItem> selectAll() {
        List<CartItem> list = new ArrayList<>();
        String sql = "SELECT * FROM CartItems";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String cartItemID = rs.getString("CartItemID");
                String cartID = rs.getString("CartID");
                String productID = rs.getString("ProductID");
                int quantity = rs.getInt("Quantity");
                CartItem cartItem = new CartItem(cartItemID, cartID, productID, quantity);
                list.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public CartItem selectById(String id) {
        CartItem cartItem = null;
        String sql = "SELECT * FROM CartItems WHERE CartItemID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String cartItemID = rs.getString("CartItemID");
                    String cartID = rs.getString("CartID");
                    String productID = rs.getString("ProductID");
                    int quantity = rs.getInt("Quantity");
                    cartItem = new CartItem(cartItemID, cartID, productID, quantity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItem;
    }

    public List<CartItem> getCartItemsByCartID(String cartID) {
        List<CartItem> list = new ArrayList<>();
        String sql = "SELECT * FROM CartItems WHERE CartID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cartID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String cartItemID = rs.getString("CartItemID");
                String productID = rs.getString("ProductID");
                int quantity = rs.getInt("Quantity");
                CartItem cartItem = new CartItem(cartItemID, cartID, productID, quantity);
                list.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addProductToCartItem(CartItem cartItem) {
        String sql = "INSERT INTO CartItems (CartID, ProductID, Quantity) VALUES (?, ?, ?)";
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, cartItem.getCartID());
            ps.setString(2, cartItem.getProductID());
            ps.setInt(3, cartItem.getQuantity());
            int row = ps.executeUpdate();
            System.out.println("Rows affected: " + row);  // Thêm dòng này để kiểm tra số dòng bị ảnh hưởng
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CartDetail> getCartDetailsByCartID(String cartID) {
        List<CartDetail> cartDetails = new ArrayList<>();
        String sql = "SELECT ci.cartItemID, ci.cartID, ci.productID, ci.quantity, "
                + "p.imageURL, p.name as productName, p.originalPrice, p.currentPrice, "
                + "p.sellerID, s.lastName, c.userID "
                + "FROM CartItems ci "
                + "JOIN Products p ON ci.productID = p.productID "
                + "JOIN Users s ON p.sellerID = s.userID "
                + "JOIN Carts c ON ci.cartID = c.cartID "
                + "WHERE ci.cartID = ?";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cartID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartDetail cartDetail = new CartDetail();
                cartDetail.setCartItemID(rs.getString("cartItemID"));
                cartDetail.setCartID(rs.getString("cartID"));
                cartDetail.setProductID(rs.getString("productID"));
                cartDetail.setQuantity(rs.getInt("quantity"));
                cartDetail.setImageURL(rs.getString("imageURL"));
                cartDetail.setProductName(rs.getString("productName"));
                cartDetail.setOriginalPrice(rs.getBigDecimal("originalPrice"));
                cartDetail.setCurrentPrice(rs.getBigDecimal("currentPrice"));
                cartDetail.setSellerID(rs.getString("sellerID"));
                cartDetail.setSellerName(rs.getString("lastName"));
                cartDetail.setUserID(rs.getString("userID"));

                cartDetails.add(cartDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartDetails;
    }

    @Override
    public List<CartItem> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
