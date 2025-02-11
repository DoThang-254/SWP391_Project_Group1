<%-- 
    Document   : LoginForm
    Created on : Jan 10, 2025, 9:04:17 AM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="login" method="POST">
            Username<input type="text" name="userName" value="${cookie.username.value}" />
            <br>
            Password <input type="password" name="passWord" value="${cookie.password.value}" /> <br>
            Remember me <input type="checkbox" name="rememberMe" value=""/> <br>

            <select name="Role">
                <option value="2">Customer</option>
                <option value="4">Service Management</option>
                <option value="3">Technician</option>
                <option value="1">Admin</option>

            </select>

            <c:if test="${requestScope.msg != null}">
                <p style="color: red"> ${requestScope.msg}</p>
            </c:if>
            <input type="submit" value="Login" />
        </form>
        <p> Don't have account ? <a href="register">Register now</a></p>
        <p> <a href="forgotpassword">Forgot Password</a></p>
       
    </body>
</html>
