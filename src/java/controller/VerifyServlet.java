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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nkiem
 */
@WebServlet(name = "VerifyServlet", urlPatterns = {"/verify"})
public class VerifyServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VerifyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String tokenFromSession = (String) session.getAttribute("tokenSession");
        User userr = (User)session.getAttribute("user");
        String tokenFromLink = request.getParameter("token");
        System.out.println("TokenFromSession: " + tokenFromSession);
        System.out.println("TokenFromLink: " + tokenFromLink);
        System.out.println("User: " + userr);
        if (tokenFromSession != null && tokenFromSession.equals(tokenFromLink)) {
            long registrationTime = (Long) session.getAttribute("registrationTime");
            long currentTime = System.currentTimeMillis();

            if (currentTime - registrationTime <= 300000) {
                User user = (User) session.getAttribute("user");
                saveUserToDatabase(user);
                session.invalidate();
                response.sendRedirect("registersuccess");
            } else {
                session.invalidate();
                response.sendRedirect("accessdenied");
            }
        } else {
            response.getWriter().write("Token không hợp lệ.");
        }
    }

    private void saveUserToDatabase(User user) {
        UserDAO userDAO = new UserDAO();
        userDAO.insert(user);
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
