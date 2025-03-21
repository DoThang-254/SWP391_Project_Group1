<%-- 
    Document   : ViewStaff
    Created on : Mar 18, 2025, 8:06:28 AM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thông tin nhân viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f9;
            font-family: Arial, sans-serif;
        }
        .staff-info {
            max-width: 500px;
            margin: 50px auto;
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
        }
        h2 {
            margin-bottom: 20px;
            color: #333;
            text-align: center;
        }
        p {
            margin-bottom: 10px;
            font-size: 16px;
        }
        .label {
            font-weight: bold;
            color: #555;
        }
        .back-button {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="staff-info">
        <h2>Thông tin nhân viên</h2>
        <p><span class="label">Họ và tên:</span> ${requestScope.staff.firstName} ${requestScope.staff.lastName}</p>
        <p><span class="label">Email:</span> ${requestScope.staff.email}</p>
        <p><span class="label">Số điện thoại:</span> ${requestScope.staff.phone}</p>
        <p><span class="label">Giới tính:</span> ${requestScope.staff.gender}</p>
        <p><span class="label">Ngày sinh:</span> 
            <fmt:formatDate value="${requestScope.staff.birthDate}" pattern="dd/MM/yyyy" />
        </p>
        <div class="back-button">
            <button class="btn btn-secondary" onclick="window.history.back();">Quay lại</button>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
