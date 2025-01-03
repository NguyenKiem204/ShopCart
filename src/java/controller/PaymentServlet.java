/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import entity.Product;
import entity.ProductCheckOut;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nkiem
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String productID = request.getParameter("productID");
    String quantity = request.getParameter("quantity");

    // Khởi tạo danh sách sản phẩm để checkout
    List<ProductCheckOut> list = new ArrayList<>(); 
    ProductDAO productDAO = new ProductDAO();

    // Lấy sản phẩm từ DAO
    ProductCheckOut productCheckOut = productDAO.getProductCheckOutByProductID(productID);
    if (productCheckOut != null && quantity != null) {
        try {
            int qty = Integer.parseInt(quantity);
            productCheckOut.setQuantity(qty);
            list.add(productCheckOut);
        } catch (NumberFormatException e) {
            System.err.println("Giá trị quantity không hợp lệ: " + quantity);
        }
    }
    if (!list.isEmpty()) {
        request.setAttribute("list", list);
    } else {
        request.setAttribute("errorMessage", "Không có sản phẩm nào được thêm vào giỏ hàng.");
    }

    request.getRequestDispatcher("payment.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductCheckOut> list = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        String[] productIDs = request.getParameterValues("productID[]");
        String[] quantities = request.getParameterValues("quantity[]");
        String[] prices = request.getParameterValues("price[]");

        if (productIDs != null && quantities != null && prices != null) {
            for (int i = 0; i < productIDs.length; i++) {
                String productID = productIDs[i];
                int quantity = Integer.parseInt(quantities[i]);
                BigDecimal price = new BigDecimal(prices[i]);

                System.out.println("Product ID: " + productID);
                System.out.println("Quantity: " + quantity);
                System.out.println("Price: " + price);

                ProductCheckOut productCheckOut = productDAO.getProductCheckOutByProductID(productID);
                if (productCheckOut != null) {
                    productCheckOut.setQuantity(quantity);

                    BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(quantity));
                    productCheckOut.setTotalPrice(totalPrice);

                    list.add(productCheckOut);
                }
            }
        }

        request.setAttribute("list", list);
        request.getRequestDispatcher("payment.jsp").forward(request, response);
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
