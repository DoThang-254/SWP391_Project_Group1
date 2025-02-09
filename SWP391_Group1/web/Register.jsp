<%-- 
    Document   : Register
    Created on : Jan 10, 2025, 4:23:08 PM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>

        <h2>Register</h2>
        <form action="register" method="post">
            Username <input type="text" name="userName" value="${requestScope.userName}" required />
            <br>
            Password <input type="password" name="passWord" required /> <br>
            Confirm Password <input type="password" name="confirmPassword" required /> <br>
            First name <input type="text" name="firstName" value="${requestScope.firstName}" required /> <br>
            Last name <input type="text" name="lastName" value="${requestScope.lastName}" required /> <br>

            <select name="gender">
                <option value="Male" ${requestScope.gender == 'Male' ? 'selected' : ''}>Male</option>
                <option value="Female" ${requestScope.gender == 'Female' ? 'selected' : ''}>Female</option>
                <option value="Other" ${requestScope.gender == 'Other' ? 'selected' : ''}>Other</option>
            </select>

            Email <input type="text" name="email" value="${requestScope.email}" required /> <br>
            Phone <input type="text" name="phone" value="${requestScope.phone}" required /> <br>
            Birth Date <input type="date" name="birthdate" value="${requestScope.dob}" required /> <br>
            Address <input type="text" name="address" value="${requestScope.address}" required /> <br>

            <input type="hidden" name="status" value="Inactive" /> <br>

            <c:if test="${requestScope.msg != null}">
                <p style="color: red">${requestScope.msg}</p>
            </c:if>
            <c:if test="${requestScope.msg2 != null}">
                <p style="color: greenyellow">${requestScope.msg2}</p>
            </c:if>

            <input type="submit" value="Register" />
            <p><a href="login">Back</a></p>
        </form>

    </body>
</html>
<style>
    h2 {
        text-align: center; /* Căn giữa */
        font-size: 28px; /* Tăng kích thước */
        color: #333; /* Màu chữ */
        font-weight: bold; /* In đậm */
        margin-bottom: 20px;
    }

    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
    }

    h1 {
        color: #333;
        font-size: 32px;
        text-align: center;
    }

    form {
        max-width: 600px;
        margin: 20px auto;
        padding: 25px;
        background-color: #ffffff;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }

    input[type="text"], input[type="password"], input[type="date"], select {
        width: 100%;
        padding: 12px;
        margin: 12px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        font-size: 16px;
    }

    input[type="submit"] {
        padding: 12px 25px;
        background-color: red; /* Blue color for Register button */
        color: white;
        border: none;
        border-radius: 4px;
        font-size: 18px;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }

    a.update-button {
        display: inline-block;
        padding: 10px 20px;
        background-color: red; /* Red for Update button */
        color: white;
        text-decoration: none;
        border-radius: 4px;
        font-size: 16px;
    }

    a.update-button:hover {
        background-color: darkred;
    }

    p {
        font-size: 16px;
    }

    p[style*="color: red"] {
        color: red;
        font-weight: bold;
    }

    p[style*="color: greenyellow"] {
        color: #28a745;
        font-weight: bold;
    }
</style>

