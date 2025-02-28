<%-- 
    Document   : PaymentReturn
    Created on : Feb 28, 2025, 8:35:15 AM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Kết quả giao dịch</h2>

        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>

        <c:if test="${not empty orderId}">
            <p><strong>Mã đơn hàng:</strong> ${orderId}</p>
            <p><strong>Số tiền:</strong> ${amount} VND</p>
            <p><strong>Ngân hàng:</strong> ${bankCode}</p>
        </c:if>

        <a href="home">Quay về trang chủ</a>    </body>
</html>
