<%-- 
    Document   : ForgotPassword
    Created on : Jan 10, 2025, 9:31:37 PM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <form action="forgotpassword" method="POST">
            Enter your username : <input type="text" name="username" required/> <br>
            Your new password <input  readonly  value="<c:if test="${requestScope.token != null}">${requestScope.token}</c:if>" /> <br>
            <c:if test="${requestScope.msg != null}">${requestScope.msg}</c:if>
            <input type="submit" value="OK" />
            
        </form>


    </body>
</html>
