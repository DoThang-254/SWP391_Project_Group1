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
        <form action="register" method="POST">
            Username<input type="text" name="userName" required />
            <br>
            Password <input type="password" name="passWord" required/> <br>
            Confirm Password <input type="password" name="confirmPassword"  required/> <br>
            <select name="Role">
                <option>Customer</option>
                <option>Staff</option>
            </select>
            <br>
            <c:if test="${requestScope.msg != null}">
                <p style="color: red"> ${requestScope.msg}</p>
            </c:if>
            <c:if test="${requestScope.msg2 != null}">
                <p style="color: greenyellow"> ${requestScope.msg2}</p>
            </c:if>
            <input type="submit" value="Register" />

        </form>
    </body>
</html>
