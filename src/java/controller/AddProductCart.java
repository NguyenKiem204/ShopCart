/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.CartDAO;
import dao.CartItemDAO;
import entity.Cart;
import entity.CartItem;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nkiem
 */
@WebServlet(name="AddProductCart", urlPatterns={"/addproductcart"})
public class AddProductCart extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddProductCart</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductCart at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String productID = request.getParameter("productID");
    String quantityStr = request.getParameter("quantity");
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("account");

    if (user == null) {
        System.out.println("User chưa đăng nhập, trả về 401");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Bạn cần đăng nhập trước khi thêm sản phẩm vào giỏ hàng.");
        return;
    }
    
    String userID = user.getUserID();
    System.out.println("User đã đăng nhập với userID: " + userID);
    int quantity = 1;
    if (quantityStr != null && !quantityStr.isEmpty()) {
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            System.out.println("Giá trị số lượng không hợp lệ, sử dụng giá trị mặc định: " + quantity);
        }
    }

    CartDAO cartDAO = new CartDAO();
    Cart cart = cartDAO.getCartByUserID(userID);

    if (cart == null) {
        cart = new Cart(userID);
        cartDAO.insert(cart);
    }
    CartItem cartItem = new CartItem(cart.getCartID(), productID, quantity);
    CartItemDAO cartItemDAO = new CartItemDAO();
    boolean success = cartItemDAO.addProductToCartItem(cartItem);

    if (success) {
        // Phản hồi thành công
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Sản phẩm đã được thêm vào giỏ hàng!");
    } else {
        // Phản hồi thất bại
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write("Thêm sản phẩm vào giỏ hàng thất bại!");
    }
}


    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
