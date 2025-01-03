/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.ProductDAO;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author nkiem
 */
@WebServlet(name="FemaleFashion", urlPatterns={"/femalefashion"})
public class FemaleFashion extends HttpServlet {
   
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
            out.println("<title>Servlet FemaleFashion</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FemaleFashion at " + request.getContextPath () + "</h1>");
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
       ProductDAO productDAO = new ProductDAO();
        List<Product> listmenfashion = productDAO.selectAllFemaleFashion();
        request.setAttribute("list", listmenfashion);
        request.getRequestDispatcher("femalefashion.jsp").forward(request, response);
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
        String[] brands = request.getParameterValues("brand");
        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");

        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;
        ProductDAO productDAO = new ProductDAO();

        if (minPriceStr != null && !minPriceStr.isEmpty()) {
            try {
                minPrice = new BigDecimal(minPriceStr);
            } catch (NumberFormatException e) {
            }
        }

        if (maxPriceStr != null && !maxPriceStr.isEmpty()) {
            try {
                maxPrice = new BigDecimal(maxPriceStr);
            } catch (NumberFormatException e) {
            }
        }

        List<Product> products = productDAO.selectAllFemaleFashion(brands, minPrice, maxPrice);
        request.setAttribute("list", products);

        request.getRequestDispatcher("femalefashion.jsp").forward(request, response);
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
