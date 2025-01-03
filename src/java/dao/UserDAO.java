/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.User;

/**
 *
 * @author nkiem
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import configure.PasswordUtil;
import java.util.logging.Logger;

public class UserDAO implements DAOInterface<User, String> {

    PasswordUtil passwordEncode = new PasswordUtil();

    @Override
 public int insert(User user) {
    int row = 0;
    String sqlInsert = "INSERT INTO Users (FirstName, LastName, Email, PasswordHash, PhoneNumber, Address, ImgURL, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//    String sqlSelect = "SELECT UserID FROM Users WHERE Email = ?"; 
    System.out.println(sqlInsert);

    try (Connection connection = DBContext.getConnection(); PreparedStatement psInsert = connection.prepareStatement(sqlInsert)) {
        psInsert.setNString(1, user.getFirstName());
        psInsert.setNString(2, user.getLastName());
        psInsert.setString(3, user.getEmail());
        String password = passwordEncode.hashPassword(user.getPasswordHash());
        psInsert.setString(4, password);
        psInsert.setString(5, user.getPhoneNumber());
        psInsert.setNString(6, user.getAddress());
        psInsert.setString(7, user.getImgURL());
        psInsert.setString(8, user.getRole());

        row = psInsert.executeUpdate();
        System.out.println("(" + row + " row(s) affected)");
    } catch (SQLException ex) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return row;
}
 public int insertFull(User user) {
    int row = 0;
    String sqlInsert = "INSERT INTO Users (UserID, FirstName, LastName, Email, PasswordHash, PhoneNumber, Address, ImgURL, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    System.out.println(sqlInsert);

    try (Connection connection = DBContext.getConnection(); PreparedStatement psInsert = connection.prepareStatement(sqlInsert)) {
        psInsert.setString(1, user.getUserID());
        psInsert.setNString(2, user.getFirstName());
        psInsert.setNString(3, user.getLastName());
        psInsert.setString(4, user.getEmail());
        String password = passwordEncode.hashPassword(user.getPasswordHash());
        psInsert.setString(5, password);
        psInsert.setString(6, user.getPhoneNumber());
        psInsert.setNString(7, user.getAddress());
        psInsert.setString(8, user.getImgURL());
        psInsert.setString(9, user.getRole());

        row = psInsert.executeUpdate();
        System.out.println("(" + row + " row(s) affected)");
    } catch (SQLException ex) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return row;
}

    @Override
    public int update(User user) {
        int row = 0;
        String sql = "UPDATE Users SET FirstName = ?, LastName = ?, Email = ?, PasswordHash = ?, PhoneNumber = ?, Address = ?,  Role = ?, ImgURL = ? WHERE UserID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
//            String password = passwordEncode.hashPassword(user.getPasswordHash());
            ps.setString(4, user.getPasswordHash());
            ps.setString(5, user.getPhoneNumber());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getRole());
            ps.setString(8, user.getImgURL());
            ps.setString(9, user.getUserID());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int delete(User user) {
        int row = 0;
        String sql = "DELETE FROM Users WHERE UserID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUserID());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<User> selectAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("UserID");
                String firstName = rs.getNString("FirstName");
                String lastName = rs.getNString("LastName");
                String email = rs.getString("Email");
                String passwordHash = rs.getString("PasswordHash");
                String phoneNumber = rs.getString("PhoneNumber");
                String address = rs.getNString("Address");
                String role = rs.getString("Role");
                String imgURL = rs.getString("ImgURL");
                User user = new User(userID, firstName, lastName, email, passwordHash, phoneNumber, address, role, imgURL);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User selectById(String id) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String userID = rs.getString("UserID");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String email = rs.getString("Email");
                    String passwordHash = rs.getString("PasswordHash");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String address = rs.getString("Address");
                    String role = rs.getString("Role");
                    String imgURL = rs.getString("ImgURL");
                    user = new User(userID, firstName, lastName, email, passwordHash, phoneNumber, address, role, imgURL);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public User selectByEmail(String xemail) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE Email = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, xemail);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String userID = rs.getString("UserID");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String email = rs.getString("Email");
                    String passwordHash = rs.getString("PasswordHash");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String address = rs.getString("Address");
                    String role = rs.getString("Role");
                    String imgURL = rs.getString("ImgURL");
                    user = new User(userID, firstName, lastName, email, passwordHash, phoneNumber, address, role, imgURL);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User checkLogin(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE Email = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String userID = rs.getString("UserID");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String address = rs.getString("Address");
                    String role = rs.getString("Role");
                    String imgURL = rs.getString("ImgURL");
                    String storedPasswordHash = rs.getString("PasswordHash");

                    // Kiểm tra mật khẩu nhập vào với mật khẩu đã mã hóa
                    if (passwordEncode.checkPassword(password, storedPasswordHash)) {
                        user = new User(userID, firstName, lastName, email, password, phoneNumber, address, role, imgURL);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<User> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Nếu bạn cần thực hiện phương thức này, hãy thêm mã cho nó
    }
}
