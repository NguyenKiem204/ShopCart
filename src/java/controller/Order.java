/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import dao.OrderItemDAO;
import entity.OrderItem;
import entity.ProductCheckOut;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author nkiem
 */
@WebServlet(name = "Order", urlPatterns = {"/order"})
public class Order extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet Order</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Order at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String listParam = request.getParameter("list");
        String total = request.getParameter("total");

        System.out.println("list: " + listParam);
        System.out.println("Total: " + total);

        List<ProductCheckOut> productList = new ArrayList<>();
        String[] items = listParam.split(", ProductCheckOut\\{");
        for (String item : items) {
            if (!item.trim().isEmpty()) {
                item = "ProductCheckOut{" + item.trim();
                ProductCheckOut product = ProductCheckOut.fromString(item);
                productList.add(product);
            }
        }
        for (ProductCheckOut pro : productList) {
            pro.setTotalPrice(pro.getTotalPrice().multiply(new BigDecimal(1000)));
        }
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("account");
        String userID = user.getUserID();
        OrderDAO orderDAO = new OrderDAO();
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        for (ProductCheckOut product : productList) {
            String orderID = UUID.randomUUID().toString();
            String orderItemID = UUID.randomUUID().toString();
            orderDAO.insert(new entity.Order(orderID, userID, new Date(), "Done", "Mia's House, Thôn Thái Bình, Xã Bình Yên, Thạch Thất, Hà Nội", product.getCurrentPrice().multiply(new BigDecimal(product.getQuantity())), "Thanh toán khi nhận hàng."));
            orderItemDAO.insert(new OrderItem(orderItemID, orderID, product.getProductID(), product.getQuantity(), product.getCurrentPrice()));
        }
        
        
        request.setAttribute("list", productList);
        request.getRequestDispatcher("order.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
