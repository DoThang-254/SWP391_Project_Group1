<%-- 
    Document   : Payment
    Created on : Feb 28, 2025, 8:26:06 AM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Thanh toán qua VNPay</h2>
        <form action="vnpay" method="post">
            <label>Số tiền (VNĐ):</label>
            <input type="number" name="amount" required>
            <button type="submit">Thanh toán</button>
        </form>
        <hr>
        <a href="transactionHistory.jsp">Xem lịch sử giao dịch</a>    </body>
</html>
