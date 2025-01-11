<%-- 
    Document   : ChangePassword
    Created on : Jan 11, 2025, 9:27:58 AM
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
        <form action="changepassword" method="POST">
            Username<input type="text" name="userName" value="${sessionScope.user.userName}" readonly />
            <br>
            Password <input type="password" name="password" required/> <br>
            Confirm Password <input type="password" name="confirmPassword"  required/> <br>
            
            <br>
            <c:if test="${requestScope.msg != null}">
                <p style="color: red"> ${requestScope.msg}</p>
            </c:if>
            <c:if test="${requestScope.msg2 != null}">
                <p style="color: greenyellow"> ${requestScope.msg2}</p>
            </c:if>
            <input type="submit" value="Change" />

        </form>
    </body>
</html>
