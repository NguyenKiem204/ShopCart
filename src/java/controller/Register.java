/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import configure.EmailSender;
import configure.TokenGenerator;
import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.mail.MessagingException;

/**
 *
 * @author nkiem
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();

        if (userDAO.selectByEmail(email) != null) {
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("password", password);

            request.setAttribute("exist_user", "***Email đã tồn tại!!!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        User user = new User(firstName, lastName, email, password, phone, address, "User", "./assets/images/my-account/original.jpg");
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        String token = TokenGenerator.generateToken();
        System.out.println("Token Before: " + token);
        session.setAttribute("tokenSession", token);
        session.setAttribute("registrationTime", System.currentTimeMillis());

        try {
            EmailSender.sendVerificationEmail(email, token);
            System.out.println("Email xác minh đã được gửi thành công.");
        } catch (MessagingException ex) {
            ex.printStackTrace();
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("checkmail");
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
