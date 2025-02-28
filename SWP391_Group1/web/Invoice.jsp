<%-- 
    Document   : Invoice
    Created on : Feb 28, 2025, 3:52:37 PM
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
        requirement Id : ${requestScope.invoice.requirement.requirementId}
        <form action="invoice" method="POST">
            <input type="hidden" name="invoiceId" value="${requestScope.invoice.invoiceId}" />
            <input type="hidden" name="reqId" value="${requestScope.invoice.requirement.requirementId}" />

            price : <input type="text" name="price" value="${requestScope.invoice.price}" /><br>
            note :  <input type="text" name="note" value="${requestScope.invoice.note}" />
            <input type="submit" value="Update">
        </form>
        status : ${requestScope.invoice.status} <br>
        <a href="task">Back</a>
    </body>
</html>
