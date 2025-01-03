<%-- 
    Document   : testpayment
    Created on : Oct 25, 2024, 1:22:14 PM
    Author     : nkiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="createpayment" method="get">
            Số tiền (VNĐ): <input type="text" name="amount" /><br />
            Mã ngân hàng (optional): <input type="text" name="bankCode" /><br />
            <button type="submit">Thanh toán</button>
        </form>

    </body>
</html>
