/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import entity.User;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author nkiem
 */
@WebServlet(name = "UpdateUser", urlPatterns = {"/updateUser"})
@MultipartConfig
public class UpdateUser extends HttpServlet {

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
            out.println("<title>Servlet UpdateUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateUser at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        System.out.println("ID received: " + id);

        UserDAO userDAO = new UserDAO();
        User user = userDAO.selectById(id);
        String ln = request.getParameter("lastname");
        String fn = request.getParameter("firstname");
        String em = request.getParameter("email");
        String pn = request.getParameter("phonenumber");
        String ad = request.getParameter("address");
        String rl = request.getParameter("role");

        user.setFirstName(fn);
        user.setLastName(ln);
        user.setEmail(em);
        user.setPhoneNumber(pn);
        user.setAddress(ad);
        user.setRole(rl);
        // Xử lý upload ảnh
        Part filePart = request.getPart("imgURL");
        if (filePart != null && filePart.getSize() > 0) {
//            String uploadPath = getServletContext().getRealPath("/assets/images/my-account/");
            String uploadPath = "D:/ShoppCart/web/assets/images/my-account/";

            System.out.println("Upload Path: " + uploadPath);

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));


            String newFileName = id + fileExtension; // Ví dụ: 12345.jpg, 12345.png
            File file = new File(uploadDir, newFileName);

            // Lưu file vào thư mục
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            user.setImgURL("./assets/images/my-account/" + newFileName);
        }

        userDAO.update(user);
        response.sendRedirect("listuser");
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
