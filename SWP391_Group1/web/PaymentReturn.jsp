<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Kết quả giao dịch</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            body {
                background-color: #f8f9fa;
                font-family: Arial, sans-serif;
            }
            .container {
                max-width: 500px;
                margin-top: 50px;
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            h2 {
                margin-bottom: 20px;
            }
            .btn-custom {
                font-size: 16px;
                padding: 10px 20px;
                width: 200px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Kết quả giao dịch</h2>

            <c:if test="${not empty message}">
                <div class="alert alert-info">${message}</div>
            </c:if>

            <c:if test="${not empty orderId}">
                <p><strong>Mã đơn hàng:</strong> ${orderId}</p>
                <p><strong>Số tiền:</strong> ${amount} VND</p>
                <p><strong>Ngân hàng:</strong> ${bankCode}</p>
            </c:if>

            <hr>

            <div class="text-center">
                <a href="home" class="btn btn-danger btn-custom">Về trang chủ</a>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
