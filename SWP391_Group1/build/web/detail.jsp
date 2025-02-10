<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Detail</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                text-align: center;
                background-color: red; /* Nền đỏ */
                color: white;
            }
            .product-container {
                display: inline-block;
                padding: 20px;
                background-color: rgba(255, 0, 0, 0.9); /* Nền đỏ đậm hơn */
                border-radius: 10px;
                box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.3);
            }
            .product-container label {
                font-weight: bold;
                display: block;
                margin-top: 10px;
            }
            .product-container input {
                width: 100%;
                padding: 8px;
                border: none;
                border-radius: 5px;
                background-color: #fff;
                color: black;
                font-size: 16px;
                text-align: center;
            }
            .product-container input[readonly] {
                background-color: #f5f5f5; /* Màu nền nhạt hơn */
                cursor: not-allowed;
            }
        </style>
    </head>
    <body>
        <h1>Product Details</h1>

        <c:choose>
            <c:when test="${not empty product}">
                <div class="product-container">
                    <label>Product ID:</label>
                    <input type="text" value="${product.productId}" readonly>

                    <label>Name:</label>
                    <input type="text" value="${product.productName}" readonly>

                    <label>Brand:</label>
                    <input type="text" value="${product.brand}" readonly>

                    <label>Price:</label>
                    <input type="text" value="${product.price}" readonly>

                    
                </div>
            </c:when>
            <c:otherwise>
                <p style="font-size: 20px; font-weight: bold;">Product not found!</p>
            </c:otherwise>
        </c:choose>

    </body>
</html>
