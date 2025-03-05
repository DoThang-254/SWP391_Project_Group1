<%-- 
    Document   : Payment
    Created on : Feb 28, 2025, 8:26:06 AM
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
        <h2>Thanh toán qua VNPay</h2>
        <c:forEach var="i" items="${requestScope.list}">
            <c:choose>
                <c:when test="${!i.confirmed}">
                    <!-- Chỉ hiển thị form xác nhận email nếu chưa xác nhận -->
                    <form action="sendconfirm" method="post">
                        <p>Mã hóa đơn: ${i.invoiceId}</p>
                        <p>Số tiền phải trả: ${i.price} VNĐ</p>

                        <label>Email nhận xác nhận:</label>
                        <input type="email" name="email" required>
                        <input type="hidden" name="customerId" value="${i.requirement.customer.customerId}">

                        <input type="hidden" name="invoiceId" value="${i.invoiceId}">
                        ${requestScope.msg}
                        <br>
                        <button type="submit">Xác nhận qua Email</button>
                    </form>
                </c:when>

                <c:otherwise>
                    <!-- Chỉ hiển thị nút thanh toán nếu đã xác nhận -->
                    <form action="vnpay" method="post">
                        <p>Mã hóa đơn: ${i.invoiceId}</p>
                        <p>Số tiền phải trả: ${i.price} VNĐ</p>
                        <input type="hidden" name="amount" value="${i.price}">
                        <input type="hidden" name="invoiceId" value="${i.invoiceId}">
                        <input type="hidden" name="customerId" value="${i.requirement.customer.customerId}">
                        <input type="hidden" name="productId" value="${i.requirement.product.productId}">
                        <button type="submit">Thanh toán</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </c:forEach>


        <hr>
        <a href="transactionHistory.jsp">Xem lịch sử giao dịch</a>    </body>
</html>
