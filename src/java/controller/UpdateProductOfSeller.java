/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import dao.UserDAO;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author nkiem
 */
@WebServlet(name = "UpdateProductOfSeller", urlPatterns = {"/updateproductofseller"})
@MultipartConfig
public class UpdateProductOfSeller extends HttpServlet {

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
            out.println("<title>Servlet UpdateProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProduct at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String productID = request.getParameter("id");
        System.out.println("ID from request: " + productID);
        String name = request.getParameter("name");
        String OriginalPrice = request.getParameter("originalPrice");
        String CurrentPrice = request.getParameter("currentPrice");
        String des = request.getParameter("description");
        String categoryID = request.getParameter("categoryID");
        BigDecimal originalPrice = null;
        BigDecimal currentPrice = null;

        try {
            originalPrice = new BigDecimal(OriginalPrice);
            currentPrice = new BigDecimal(CurrentPrice);
        } catch (NumberFormatException e) {
        }
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.selectById(productID);

        product.setName(name);
        product.setDescription(des);
        product.setCategoryID(categoryID);
        product.setOriginalPrice(originalPrice);
        product.setCurrentPrice(currentPrice);
        // Xử lý upload ảnh
        Part filePart = request.getPart("imgURL");
        if (filePart != null && filePart.getSize() > 0) {
            // Đường dẫn lưu ảnh (tương tự như với user)
            String uploadPath = "D:/ShoppCart/web/assets/images/products/";
            System.out.println("Upload Path: " + uploadPath);

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));

            String newFileName = productID + fileExtension; // Ví dụ: 12345.jpg, 12345.png
            File file = new File(uploadDir, newFileName);

            // Lưu file vào thư mục
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            product.setImageURL("./assets/images/products/" + newFileName);
        }

        productDAO.update(product);
        response.sendRedirect("listproductofseller?userID="+product.getSellerID());
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
