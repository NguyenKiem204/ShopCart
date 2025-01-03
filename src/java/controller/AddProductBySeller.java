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
import java.util.UUID;

/**
 *
 * @author nkiem
 */
@MultipartConfig
@WebServlet(name="AddProductBySeller", urlPatterns={"/addproduct"})
public class AddProductBySeller extends HttpServlet {
   
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
            out.println("<title>Servlet AddProductBySeller</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductBySeller at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
    String sellerID = request.getParameter("userID");
    String productName = request.getParameter("name");
    String description = request.getParameter("description");
    String currentPrice = request.getParameter("currentPrice");
    String originalPrice = request.getParameter("originalPrice");
    String categoryID = request.getParameter("categoryID");

    BigDecimal cp = null, op = null;
    try {
        cp = new BigDecimal(currentPrice);
        op = new BigDecimal(originalPrice);
    } catch (Exception e) {
        e.printStackTrace();
    }

    String productID = UUID.randomUUID().toString();
    Part filePart = request.getPart("imgURL");
    String imgURL = null;
    if (filePart != null && filePart.getSize() > 0) {
        // Đường dẫn lưu ảnh
        String uploadPath = "D:/ShoppCart/web/assets/images/products/";
        System.out.println("Upload Path: " + uploadPath);

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = productID + fileExtension; // Ví dụ: UUID.jpg, UUID.png

        File file = new File(uploadDir, newFileName);

        // Lưu file vào thư mục
        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imgURL = "./assets/images/products/" + newFileName;
    }

    // Tạo đối tượng Product
    Product product = new Product(productID, sellerID, productName, description, categoryID, imgURL, op, cp);

    // Lưu vào cơ sở dữ liệu
    ProductDAO productDAO = new ProductDAO();
    int rowsInserted = productDAO.insert(product);

    if (rowsInserted > 0) {
        System.out.println("Product was inserted successfully!");
        response.sendRedirect("listproductofseller?userID=" + sellerID); // Điều hướng sau khi thêm thành công
    } else {
        System.out.println("Product insertion failed!");
        response.sendRedirect("accessdenied");
    }
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
