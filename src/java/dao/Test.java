/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Cart;
import entity.CartDetail;
import entity.User;
import java.util.List;

/**
 *
 * @author nkiem
 */
public class Test {

    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAO();
//        User user = new User("Xuân", "Vĩnh6", "vinhnx21@gmail.com", "vinh@12345", "096877564", "Thái Nguyên", "User", "./assets/images/my-account/original.webp");
//    int row = userDAO.insert(user);
//        System.out.println(row);
//        ProductDAO productDAO = new ProductDAO();
//        Product product = new Product("4A53CE39-6C76-4823-A5C5-C21C36B658DE", "MacBook Pro 2025 M2 Chip A1 Core 9i", "Không có gì xuất sắc hơn Macbook Pro 2024", "68B81D8E-B14D-4E8C-8CE8-5CB244C43F22", "./assets", BigDecimal.valueOf(5000), BigDecimal.valueOf(222));
//        int row = productDAO.insert(product);
//        System.out.println(row);
//        CartDAO cartDAO = new CartDAO();
//        Cart cart = new Cart("CE7886B7-DE7A-4FF1-BFD3-4B9552EB2A9A");
//        int row = cartDAO.insert(cart);
//        System.out.println(row);
//CartDAO dAO = new  CartDAO();
//Cart cart = dAO.getCartByUserID("AA0BD33A-4587-48C8-B84D-3DBDF5BCBFFA");
//        System.out.println(cart);
CartItemDAO cartItemDAO = new CartItemDAO();
//        List<CartDetail> list = cartItemDAO.getCartDetailsByCartID("88D505F3-1084-4382-ACA7-350A0F9589AD");
//        for (CartDetail cartDetail : list) {
//            System.out.println(cartDetail);
//            
//        }
int count = cartItemDAO.getCartItemCountByUserID("AA0BD33A-4587-48C8-B84D-3DBDF5BCBFFA");
        System.out.println(count);
    }
}
