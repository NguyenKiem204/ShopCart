package dao;

import entity.Product;
import entity.ProductCheckOut;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO implements DAOInterface<Product, String> {

    @Override
    public int insert(Product product) {
        int row = 0;
        String sql = "INSERT INTO Products (SellerID, Name, Description, CategoryID, ImageURL, OriginalPrice, currentPrice"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getSellerID());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getCategoryID());
            ps.setString(5, product.getImageURL());
            ps.setBigDecimal(6, product.getOriginalPrice());
            ps.setBigDecimal(7, product.getCurrentPrice());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public int add(Product product) {
        int row = 0;
        String sql = "INSERT INTO Products (ProductID, SellerID, Name, Description, CategoryID, ImageURL, OriginalPrice, currentPrice"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getProductID());
            ps.setString(2, product.getSellerID());
            ps.setString(3, product.getName());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getCategoryID());
            ps.setString(6, product.getImageURL());
            ps.setBigDecimal(7, product.getOriginalPrice());
            ps.setBigDecimal(8, product.getCurrentPrice());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    @Override
    public int update(Product product) {
        int row = 0;
        String sql = "UPDATE Products SET Name = ?, Description = ?, CategoryID = ?, ImageURL = ?, OriginalPrice = ?, CurrentPrice = ? WHERE ProductID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getCategoryID());
            ps.setString(4, product.getImageURL());
            ps.setBigDecimal(5, product.getOriginalPrice());
            ps.setBigDecimal(6, product.getCurrentPrice());
            ps.setString(7, product.getProductID());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int delete(Product product) {
        int row = 0;
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getProductID());
            row = ps.executeUpdate();
            System.out.println("(" + row + " row(s) affected)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<Product> selectAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> search(String input) {
        List<Product> list = new ArrayList<>();
        if (input == null || input.trim().isEmpty()) {
            System.out.println("Input is empty or null");
            return list;
        }
        String[] keywords = input.trim().split("\\s+");
        StringBuilder sql = new StringBuilder("SELECT *, (");
        for (int i = 0; i < keywords.length; i++) {
            if (i > 0) {
                sql.append(" + ");
            }
            sql.append("(CASE WHEN Name LIKE ? THEN 1 ELSE 0 END)"); 
        }
        sql.append(") AS relevance_score FROM Products WHERE");

        for (int i = 0; i < keywords.length; i++) {
            if (i > 0) {
                sql.append(" AND");
            }
            sql.append(" (Name LIKE ?)");
        }
        sql.append(" ORDER BY relevance_score DESC, Name");
        System.out.println("Generated SQL: " + sql);

        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            for (String keyword : keywords) {
                ps.setString(paramIndex++, "%" + keyword + "%");
            }
            for (String keyword : keywords) {
                ps.setString(paramIndex++, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> search1(String input) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE Name LIKE ? OR Description LIKE ? ORDER BY Name";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + input + "%");
            ps.setString(2, "%" + input + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> selectAllInput(String input, String[] brands, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> list = new ArrayList<>();
        if (input == null || input.trim().isEmpty()) {
            System.out.println("Input is empty or null");
            return list;
        }
        String[] keywords = input.trim().split("\\s+");
        StringBuilder sql = new StringBuilder("SELECT * FROM Products WHERE");
        if (keywords.length > 0) {
            sql.append(" (");
            for (int i = 0; i < keywords.length; i++) {
                if (i > 0) {
                    sql.append(" AND ");
                }
                sql.append("Name LIKE ?");
            }
            sql.append(")");
        }
        if (brands != null && brands.length > 0) {
            sql.append(" AND (");
            for (int i = 0; i < brands.length; i++) {
                sql.append("Name LIKE ?");
                if (i < brands.length - 1) {
                    sql.append(" OR ");
                }
            }
            sql.append(")");
        }
        if (minPrice != null) {
            sql.append(" AND CurrentPrice >= ?");
        }
        if (maxPrice != null) {
            sql.append(" AND CurrentPrice <= ?");
        }
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int index = 1;

            for (String keyword : keywords) {
                ps.setString(index++, "%" + keyword + "%");
            }
            if (brands != null && brands.length > 0) {
                for (String brand : brands) {
                    ps.setString(index++, "%" + brand + "%");
                }
            }
            if (minPrice != null) {
                ps.setBigDecimal(index++, minPrice);
            }
            if (maxPrice != null) {
                ps.setBigDecimal(index++, maxPrice);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");

                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> selectAllMenFashion() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products Where CategoryID = 'F2E0EDDE-02A4-43D4-AD33-AE66880A3E48'";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> selectAllFemaleFashion() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products Where CategoryID = '24DBC219-2115-43A9-AE38-4F644444E4F9'";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> selectAllMenFashion(String[] brands, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Products WHERE CategoryID = 'F2E0EDDE-02A4-43D4-AD33-AE66880A3E48'");
        if (brands != null && brands.length > 0) {
            sql.append(" AND (");
            for (int i = 0; i < brands.length; i++) {
                sql.append("Name LIKE ?");
                if (i < brands.length - 1) {
                    sql.append(" OR ");
                }
            }
            sql.append(")");
        }
        if (minPrice != null) {
            sql.append(" AND CurrentPrice >= ?");
        }
        if (maxPrice != null) {
            sql.append(" AND CurrentPrice <= ?");
        }
        System.out.println(sql); 
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            if (brands != null && brands.length > 0) {
                for (String brand : brands) {
                    ps.setString(paramIndex++, "%" + brand + "%"); // Sử dụng LIKE với ký tự đại diện %
                }
            }
            if (minPrice != null) {
                ps.setBigDecimal(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                ps.setBigDecimal(paramIndex++, maxPrice);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> selectAllFemaleFashion(String[] brands, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Products WHERE CategoryID = '24DBC219-2115-43A9-AE38-4F644444E4F9'");
        if (brands != null && brands.length > 0) {
            sql.append(" AND (");
            for (int i = 0; i < brands.length; i++) {
                sql.append("Name LIKE ?");
                if (i < brands.length - 1) {
                    sql.append(" OR ");
                }
            }
            sql.append(")");
        }
        if (minPrice != null) {
            sql.append(" AND CurrentPrice >= ?");
        }
        if (maxPrice != null) {
            sql.append(" AND CurrentPrice <= ?");
        }
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            if (brands != null && brands.length > 0) {
                for (String brand : brands) {
                    ps.setString(paramIndex++, "%" + brand + "%");
                }
            }
            if (minPrice != null) {
                ps.setBigDecimal(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                ps.setBigDecimal(paramIndex++, maxPrice);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> selectTop10MostDiscountedByPercentage() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 10 *, ((OriginalPrice - CurrentPrice) / OriginalPrice) * 100 AS DiscountPercentage "
                + "FROM Products "
                + "WHERE OriginalPrice > 0 "
                + "ORDER BY DiscountPercentage DESC";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> selectsmallthan200() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT *"
                + "FROM Products "
                + "WHERE CurrentPrice < 200000 ";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Product> selectTop20() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 20 * FROM Products ORDER BY NEWID()";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> selectTop20Laptop() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 20 * FROM Products Where CategoryID = '68B81D8E-B14D-4E8C-8CE8-5CB244C43F22' ORDER BY NEWID()";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> selectAllOfMySeller(String id) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products Where SellerID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String sellerID = rs.getString("SellerID");
                String name = rs.getNString("Name");
                String description = rs.getNString("Description");
                String categoryID = rs.getString("CategoryID");
                String imageURL = rs.getString("ImageURL");
                BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                Product product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countProductsBySellerID(String sellerID) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS ProductCount FROM Products WHERE SellerID = ?";
        System.out.println(sql);

        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sellerID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("ProductCount"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Product selectById(String id) {
        Product product = null;
        String sql = "SELECT * FROM Products WHERE ProductID = ?";
        System.out.println(sql);
        try (Connection connection = DBContext.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String sellerID = rs.getString("SellerID");
                    String name = rs.getString("Name");
                    String description = rs.getString("Description");
                    String categoryID = rs.getString("CategoryID");
                    String imageURL = rs.getString("ImageURL");
                    BigDecimal originalPrice = rs.getBigDecimal("OriginalPrice");
                    BigDecimal currentPrice = rs.getBigDecimal("CurrentPrice");
                    product = new Product(productID, sellerID, name, description, categoryID, imageURL, originalPrice, currentPrice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public String getLastInsertedProductId() {
        String lastInsertedId = null;
        String sql = "SELECT TOP 1 id FROM Products ORDER BY created_at DESC";

        try (Connection connection = DBContext.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                lastInsertedId = resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastInsertedId;
    }

    @Override
    public ArrayList<Product> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ProductCheckOut getProductCheckOutByProductID(String productID) {
        ProductCheckOut productCheckOut = null;
        String sql = "SELECT productID, imageURL, name AS productName, originalPrice, currentPrice, "
                + "sellerID, lastName AS sellerName FROM Products "
                + "JOIN Users ON Products.sellerID = Users.userID WHERE productID = ?";

        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, productID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                productCheckOut = new ProductCheckOut();
                productCheckOut.setProductID(rs.getString("productID"));
                productCheckOut.setImageURL(rs.getString("imageURL"));
                productCheckOut.setProductName(rs.getString("productName"));
                productCheckOut.setOriginalPrice(rs.getBigDecimal("originalPrice"));
                productCheckOut.setCurrentPrice(rs.getBigDecimal("currentPrice"));
                productCheckOut.setSellerID(rs.getString("sellerID"));
                productCheckOut.setSellerName(rs.getString("sellerName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productCheckOut;
    }


}
