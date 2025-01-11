<%-- 
    Document   : HomePage
    Created on : Jan 10, 2025, 10:08:41 AM
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
        <h1>Hello <c:if test="${sessionScope.user != null}">
                ${sessionScope.user.userName}
        </c:if> </h1>
        
         <p> <a href="changepassword">Change Password</a></p>
        
        <form action="logout">
            <input type="submit" value="Logout" />
        </form>
    </body>
</html>
