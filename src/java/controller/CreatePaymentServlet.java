/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author nkiem
 */
@WebServlet(name = "CreatePaymentServlet", urlPatterns = {"/createpayment"})
public class CreatePaymentServlet extends HttpServlet {

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
            out.println("<title>Servlet CreatePaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreatePaymentServlet at " + request.getContextPath() + "</h1>");
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
// Thông tin từ VNPAY sandbox
private final String vnp_TmnCode = "WZZC5G32"; // Mã TMN từ VNPAY
private final String vnp_HashSecret = "SZ63R5DPN5ZAMFVPUSK8V5CJKR8FUQLU"; // HashSecret từ VNPAY
private final String vnp_Url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html"; // URL của cổng thanh toán VNPAY Sandbox

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String orderId = String.valueOf(new Date().getTime()); // Mã đơn hàng (đơn giản là thời gian hiện tại)
    String amount = request.getParameter("amount"); // Lấy số tiền từ form (số tiền phải nhân với 100)
    String bankCode = request.getParameter("bankCode"); // Lựa chọn ngân hàng (có thể bỏ qua)
    System.out.println("Amount: " + amount);
    System.out.println("Bankcode: " + bankCode);

    // Cấu hình các tham số gửi lên VNPAY
    Map<String, String> vnp_Params = new HashMap<>();
    vnp_Params.put("vnp_Version", "2.1.0");
    vnp_Params.put("vnp_Command", "pay");
    vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
    
    // Lưu ý số tiền phải được nhân với 100
    vnp_Params.put("vnp_Amount", String.valueOf(Integer.parseInt(amount) * 100)); // Đơn vị là VND x100
    vnp_Params.put("vnp_CurrCode", "VND");

    // URL trả về sau khi thanh toán xong
    String returnUrl = "http://localhost:8080/ngkiemshopping/payment-return";
    vnp_Params.put("vnp_ReturnUrl", returnUrl);
    vnp_Params.put("vnp_TxnRef", orderId); // Mã giao dịch của hệ thống
    
    // Sử dụng URLEncoder cho OrderInfo để mã hóa khoảng trắng
    vnp_Params.put("vnp_OrderInfo", URLEncoder.encode("Thanh toan don hang: " + orderId, "UTF-8").replace("+", "%20"));
    vnp_Params.put("vnp_OrderType", "other");

    // Thời gian tạo yêu cầu (theo chuẩn yyyyMMddHHmmss)
    String vnp_CreateDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

    // IP của khách hàng
    String ipAddress = request.getRemoteAddr();
    vnp_Params.put("vnp_IpAddr", ipAddress);

    // Nếu có bankCode (ngân hàng chọn sẵn) thì thêm vào tham số
    if (bankCode != null && !bankCode.isEmpty()) {
        vnp_Params.put("vnp_BankCode", bankCode);
    }

    // Mã hóa tham số và tạo URL chuyển hướng
    List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
    Collections.sort(fieldNames);
    StringBuilder hashData = new StringBuilder();
    StringBuilder query = new StringBuilder();
    try {
        for (String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            if (hashData.length() > 0) {
                hashData.append('&');
                query.append('&');
            }
            hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, "UTF-8").replace("+", "%20")); // Mã hóa khoảng trắng
            query.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, "UTF-8").replace("+", "%20"));
        }

        // Tạo chữ ký (signature) SHA256
        String vnp_SecureHash = hmacSHA512(vnp_HashSecret, hashData.toString());
        query.append("&vnp_SecureHash=").append(vnp_SecureHash);

        // Tạo URL chuyển hướng
        String paymentUrl = vnp_Url + "?" + query.toString();
        response.sendRedirect(paymentUrl); // Chuyển hướng tới VNPAY để thanh toán

    } catch (UnsupportedEncodingException e) {
        e.printStackTrace(); // Ghi log lỗi
    } catch (Exception e) {
        e.printStackTrace(); // Ghi log lỗi
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi không mong muốn.");
    }
}

// Hàm mã hóa HMAC SHA512
private static String hmacSHA512(String key, String data) {
    try {
        byte[] hmacKey = key.getBytes("UTF-8");
        byte[] hmacData = data.getBytes("UTF-8");
        SecretKeySpec secretKey = new SecretKeySpec(hmacKey, "HmacSHA512");
        Mac mac = Mac.getInstance("HmacSHA512");
        mac.init(secretKey);
        byte[] hashBytes = mac.doFinal(hmacData);
        return bytesToHex(hashBytes);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "";
}

// Hàm chuyển đổi byte sang chuỗi Hex
private static String bytesToHex(byte[] bytes) {
    StringBuilder hexString = new StringBuilder();
    for (byte b : bytes) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) {
            hexString.append('0');
        }
        hexString.append(hex);
    }
    return hexString.toString();
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
