/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package configure;

import dao.UserDAO;
import entity.AccountFacebook;
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
@WebServlet(name="LoginFacebook", urlPatterns={"/loginfacebook"})
public class LoginFacebook extends HttpServlet {
   
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
            out.println("<title>Servlet LoginFacebook</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginFacebook at " + request.getContextPath () + "</h1>");
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
        String code = request.getParameter("code");
        System.out.println(code);
        FacebookLogin fb = new FacebookLogin();
        String accessToken = fb.getToken(code);
        AccountFacebook account = fb.getUserInfo(accessToken);
        System.out.println(account);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.selectById(account.getId());
        HttpSession session = request.getSession();
       if(user==null){
          User user1 = new User(account.getId(), account.getName(), account.getName(), " ", "qưertyuio", "+84 123 456 789", account.getLocation(), "User", account.getPictureUrl());
          userDAO.insertFull(user1);
          session.setAttribute("account", user1);
          response.sendRedirect("home");
          return;
       }else if(user!=null){
           session.setAttribute("account", user);
           response.sendRedirect("home");
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
