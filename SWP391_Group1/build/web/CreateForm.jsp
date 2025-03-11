<%-- 
    Document   : CreateForm
    Created on : Mar 7, 2025, 8:43:29 AM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update Form</h1>
        <form action="createform" method="post">
            <label for="formId">Form Id:</label>
            <input type="text" id="formId" name="formId" value="${requestScope.form.formId}" readonly> <br>

            <label for="productId">Product Id:</label>
            <input type="text" id="productId" name="productId" value="${requestScope.form.product.productId}" readonly> <br>

            <label for="startDate">Start Date:</label>
            <input type="text" id="startDate" name="startDate" value="${requestScope.form.startDate}"> <br>

            <fmt:formatDate value="${requestScope.form.endDate}" pattern="yyyy-MM-dd" var="formattedDate" />

            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" name="endDate" value="${formattedDate}"> <br>

            <label for="status">Status:</label>
            <input type="text" id="status" name="status" value="${requestScope.form.status}" readonly> <br>

            <label for="verified">Customer Verified:</label>
            <input type="text" id="verified" name="verified" 
                   value="${empty requestScope.form.verified ? 'Chưa xác nhận' : requestScope.form.verified}" 
                   readonly> 
            <br>

            <label for="faultType">Fault Type:</label>
            <select id="faultType" name="faultType">
                <option value="manufacturer" ${requestScope.form.faultType == 'manufacturer' ? 'selected' : ''}>Lỗi nhà sản xuất</option>
                <option value="user" ${requestScope.form.faultType == 'user' ? 'selected' : ''}>Lỗi người dùng</option>
            </select> <br>

            <label for="img">Upload Image:</label>
            <input type="file" id="img" name="img"> <br>
            ${requestScope.msg}
            <br>
            <input type="submit" value="Update Form">
        </form>

        <a href="technicianrequest">Back</a>
    </body>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            color: #333;
            text-align: center;
            margin: 0;
            padding: 20px;
        }

        h1 {
            color: red; /* Đỏ đô */
        }

        form {
            background-color: white;
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            border-left: 5px solid red; /* Viền đỏ đô */
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 10px;
            text-align: left;
            color: red;
        }

        input, select {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input:focus, select:focus {
            border-color: #8B0000;
            outline: none;
            box-shadow: 0 0 5px rgba(139, 0, 0, 0.5);
        }

        input[type="submit"] {
            background-color: red;
            color: white;
            font-weight: bold;
            border: none;
            padding: 10px;
            cursor: pointer;
            border-radius: 4px;
            transition: 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #a40000;
        }

        a {
            display: inline-block;
            margin-top: 10px;
            text-decoration: none;
            color: #8B0000;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

    </style>
</html>
