<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Thanh toán VNPay</title>

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
            }
            h2 {
                text-align: center;
                margin-bottom: 20px;
            }
            .btn-custom {
                width: 100%;
                font-size: 16px;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Thanh toán qua VNPay</h2>

            <c:choose>
                <c:when test="${!i.confirmed}">
                    <!-- Trường hợp chưa xác nhận email -->
                    <form action="sendconfirm" method="post">
                        <div class="mb-3">
                            <label class="form-label">Mã hóa đơn:</label>
                            <input type="text" class="form-control" value="${i.invoiceId}" readonly>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Số tiền phải trả:</label>
                            <input type="text" class="form-control" value="${i.price} VNĐ" readonly>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Email nhận xác nhận:</label>
                            <input type="email" class="form-control" name="email" required placeholder="Nhập email của bạn">
                        </div>

                        <input type="hidden" name="customerId" value="${i.requirement.customer.customerId}">
                        <input type="hidden" name="requirementId" value="${requestScope.requirementId}">
                        <input type="hidden" name="formId" value="${requestScope.formId}">
                        <input type="hidden" name="invoiceId" value="${i.invoiceId}">

                        <c:if test="${requestScope.msg != null}">
                            <div class="alert alert-info">${requestScope.msg}</div>
                        </c:if>

                        <button type="submit" class="btn btn-danger btn-custom">Xác nhận qua Email</button>
                    </form>
                </c:when>

                <c:otherwise>
                    <!-- Trường hợp đã xác nhận email -->
                    <form action="vnpay" method="post">
                        <div class="mb-3">
                            <label class="form-label">Mã hóa đơn:</label>
                            <input type="text" class="form-control" value="${i.invoiceId}" readonly>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Số tiền phải trả:</label>
                            <input type="text" class="form-control" value="${i.price} VNĐ" readonly>
                        </div>

                        <input type="hidden" name="amount" value="${i.price}">
                        <input type="hidden" name="invoiceId" value="${i.invoiceId}">
                        <input type="hidden" name="customerId" value="${i.requirement.customer.customerId}">
                        <input type="hidden" name="productId" value="${i.requirement.product.productId}">
                        <input type="hidden" name="formId" value="${requestScope.formId}">
                        <input type="hidden" name="requirementId" value="${requestScope.requirementId}">

                        <button type="submit" class="btn btn-danger btn-custom">Thanh toán</button>
                    </form>
                </c:otherwise>
            </c:choose>

            <hr>
            <div class="d-flex justify-content-between">
                <button onclick="history.back()" class="btn btn-warning">Quay lại</button>
                <a href="transactionhistory" class="btn btn-secondary">Lịch sử giao dịch</a>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
