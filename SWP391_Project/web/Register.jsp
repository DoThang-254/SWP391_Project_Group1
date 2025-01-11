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
            Full name<input type="text" name="fullName" required /> <br>
            Email<input type="text" name="email" required /> <br>
            Phone<input type="text" name="phone" required /> <br>
            
            <select name="Role">
                <option value="2">Customer</option>
                <option value="4">Service Management</option>
                <option value="3">Technician</option>
                <option value="1">Admin</option>

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
