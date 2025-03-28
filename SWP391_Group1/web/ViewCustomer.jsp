<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Thông tin khách hàng</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white">
                    <h2 class="text-center">Thông tin khách hàng</h2>
                </div>
                <div class="card-body">
                    <c:if test="${not empty customer}">
                        <table class="table table-bordered">
                            <tr>
                                <th>Họ và tên</th>
                                <td>${customer.firstName} ${customer.lastName}</td>
                            </tr>
                            <tr>
                                <th>Username</th>
                                <td>${customer.username}</td>
                            </tr>
                            <tr>
                                <th>Số điện thoại</th>
                                <td>${customer.phone}</td>
                            </tr>
                            <tr>
                                <th>Email</th>
                                <td>${customer.email}</td>
                            </tr>
                            <tr>
                                <th>Giới tính</th>
                                <td>${customer.gender}</td>
                            </tr>
                            <tr>
                                <th>Địa chỉ</th>
                                <td>${customer.address}</td>
                            </tr>
                        </table>
                    </c:if>
                    <c:if test="${empty customer}">
                        <p class="text-danger text-center">Không tìm thấy khách hàng.</p>
                    </c:if>
                </div>
                <div class="card-footer text-center">
                    <a href="javascript:window.history.back();" class="btn btn-secondary">Quay lại danh sách</a>
                </div>
            </div>
        </div>
    </body>
</html>
