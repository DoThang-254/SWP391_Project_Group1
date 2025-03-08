<%-- 
    Document   : CreateForm
    Created on : Mar 7, 2025, 8:43:29 AM
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
        <h1>Update Form</h1>
        <form action="createform" method="post">
            <label for="formId">Form Id:</label>
            <input type="text" id="formId" name="formId" value="${requestScope.form.formId}"> <br>

            <label for="productId">Product Id:</label>
            <input type="text" id="productId" name="productId" value="${requestScope.form.product.productId}"> <br>

            <label for="startDate">Start Date:</label>
            <input type="text" id="startDate" name="startDate" value="${requestScope.form.startDate}"> <br>

            <label for="endDate">End Date:</label>
            <input type="text" id="endDate" name="endDate" value="${requestScope.form.endDate}"> <br>

            <label for="status">Status:</label>
            <input type="text" id="status" name="status" value="${requestScope.form.status}"> <br>

            <label for="verified">Customer Verified:</label>
            <input type="text" id="verified" name="verified" value="${requestScope.form.verified}" readonly> <br>

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


    </body>
</html>
