<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Product Details</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f9;
            font-family: Arial, sans-serif;
        }
        .card {
            margin-top: 50px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .card-title {
            color: #333;
        }
        .card-text {
            color: #555;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center mt-4">Product Details</h2>
        
            <div class="card mx-auto" style="width: 400px;">
                <div class="card-body">
                    <h5 class="card-title">Product Name: <c:out value="${requestScope.product.productName}" /></h5>
                                    <p class="card-text"><strong>Brand:</strong> <c:out value="${requestScope.product.brand}" /></p>

                    <p class="card-text"><strong>Price:</strong> <c:out value="${requestScope.product.price}" /></p>
                <button onclick="history.back()" class="btn btn-primary">Back</button>
                </div>
            </div>

        
    </div>
</body>
</html>
