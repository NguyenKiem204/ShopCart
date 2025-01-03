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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nkiem
 */
@WebServlet(name = "Search", urlPatterns = {"/search"})
public class Search extends HttpServlet {

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
            out.println("<title>Servlet Search</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Search at " + request.getContextPath() + "</h1>");
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
        String input = request.getParameter("search");

        Map<String, List<String>> productKeywords = new HashMap<>();
        productKeywords.put("Áo", Arrays.asList("áo thun", "áo sơ mi", "áo khoác", "áo hoodie", "áo nỉ", "áo phông", "áo dài tay", "áo cộc tay", "áo không tay"));
        productKeywords.put("Quần", Arrays.asList("quần jean", "quần tây", "quần short", "quần dài", "quần baggy", "quần jogger", "quần kaki", "quần legging"));
        productKeywords.put("Váy", Arrays.asList("chân váy", "váy dạ hội", "váy xòe", "váy suông", "váy ôm", "váy maxi", "váy midi", "váy bút chì"));
        productKeywords.put("Giày", Arrays.asList("giày thể thao", "giày cao gót", "giày búp bê", "giày lười", "giày sandal", "giày tây", "giày đế xuồng"));
        productKeywords.put("Phụ kiện", Arrays.asList("mũ", "khăn choàng", "dây chuyền", "nhẫn", "vòng tay", "khuyên tai", "kính râm", "thắt lưng"));
        productKeywords.put("Túi xách", Arrays.asList("túi đeo chéo", "túi xách tay", "ba lô", "túi clutch", "túi tote", "túi đeo vai"));
        productKeywords.put("Đầm", Arrays.asList("đầm dạ hội", "đầm dự tiệc", "đầm công sở", "đầm maxi", "đầm suông", "đầm ôm body", "đầm ren"));

        List<String> filteredKeywords = new ArrayList<>();
        if (input != null && !input.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : productKeywords.entrySet()) {
                for (String keyword : entry.getValue()) {
                    if (keyword.toLowerCase().contains(input.toLowerCase())) {
                        filteredKeywords.addAll(entry.getValue()); // Thêm tất cả từ khóa con
                        break;
                    }
                }
            }
        }

        ProductDAO productDAO = new ProductDAO();
        List<Product> list = productDAO.search(input);

        request.setAttribute("input", input);
        request.setAttribute("list", list);
        request.setAttribute("filteredKeywords", filteredKeywords);

        request.getRequestDispatcher("search.jsp").forward(request, response);
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
        String input = request.getParameter("searchh");
        request.setAttribute("input", input);

        Map<String, List<String>> productKeywords = new HashMap<>();
        productKeywords.put("Áo", Arrays.asList("áo thun", "áo sơ mi", "áo khoác", "áo hoodie", "áo nỉ", "áo phông", "áo dài tay", "áo cộc tay", "áo không tay"));
        productKeywords.put("Quần", Arrays.asList("quần jean", "quần tây", "quần short", "quần dài", "quần baggy", "quần jogger", "quần kaki", "quần legging"));
        productKeywords.put("Váy", Arrays.asList("chân váy", "váy dạ hội", "váy xòe", "váy suông", "váy ôm", "váy maxi", "váy midi", "váy bút chì"));
        productKeywords.put("Giày", Arrays.asList("giày thể thao", "giày cao gót", "giày búp bê", "giày lười", "giày sandal", "giày tây", "giày đế xuồng"));
        productKeywords.put("Phụ kiện", Arrays.asList("mũ", "khăn choàng", "dây chuyền", "nhẫn", "vòng tay", "khuyên tai", "kính râm", "thắt lưng"));
        productKeywords.put("Túi xách", Arrays.asList("túi đeo chéo", "túi xách tay", "ba lô", "túi clutch", "túi tote", "túi đeo vai"));
        productKeywords.put("Đầm", Arrays.asList("đầm dạ hội", "đầm dự tiệc", "đầm công sở", "đầm maxi", "đầm suông", "đầm ôm body", "đầm ren"));

        List<String> filteredKeywords = new ArrayList<>();
        if (input != null && !input.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : productKeywords.entrySet()) {
                for (String keyword : entry.getValue()) {
                    if (keyword.toLowerCase().contains(input.toLowerCase())) {
                        filteredKeywords.addAll(entry.getValue());
                        break;
                    }
                }
            }
        }
        request.setAttribute("filteredKeywords", filteredKeywords);
        String[] brands = request.getParameterValues("brand");
        request.setAttribute("selectedBrands", brands != null ? Arrays.asList(brands) : Collections.emptyList());

        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");

        BigDecimal minPrice = minPriceStr != null && !minPriceStr.isEmpty() ? new BigDecimal(minPriceStr) : null;
        BigDecimal maxPrice = maxPriceStr != null && !maxPriceStr.isEmpty() ? new BigDecimal(maxPriceStr) : null;

        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);

        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.selectAllInput(input, brands, minPrice, maxPrice);

        request.setAttribute("list", products);
        request.getRequestDispatcher("search.jsp").forward(request, response);
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
