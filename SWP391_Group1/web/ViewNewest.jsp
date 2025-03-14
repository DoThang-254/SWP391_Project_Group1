<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Warranty Form Details</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container mt-5">
        <h2 class="text-center">Warranty Form Details</h2>

        <c:choose>
            <c:when test="${not empty wf}">
                <table class="table table-bordered mt-3">
                    <tr>
                        <th>Form ID</th>
                        <td>${wf.formId}</td>
                    </tr>
                    <tr>
                        <th>Product ID</th>
                        <td>${wf.product.productId}</td>
                    </tr>
                    <tr>
                        <th>Start Date</th>
                        <td><fmt:formatDate value="${wf.startDate}" pattern="dd/MM/yyyy"/></td>
                    </tr>
                    <tr>
                        <th>End Date</th>
                        <td><fmt:formatDate value="${wf.endDate}" pattern="dd/MM/yyyy"/></td>
                    </tr>
                    <tr>
                        <th>Status</th>
                        <td>${wf.status}</td>
                    </tr>
                </table>
            </c:when>
            <c:otherwise>
                <p class="text-danger text-center">No active warranty form found.</p>
            </c:otherwise>
        </c:choose>

        <a href="javascript:history.back()" class="btn btn-primary mt-3">Back</a>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
