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
        <title>Invoice Page</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                max-width: 600px;
                margin-top: 50px;
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }
            /* Phóng to văn bản trong ô nhập note */
            .form-control.note-input {
                font-size: 1.2rem; /* Kích thước chữ lớn hơn */
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 class="text-center">Invoice Details</h2>
            <p><strong>Requirement ID:</strong> ${requestScope.invoice.requirement.requirementId}</p>
            <form action="invoice" method="POST">
                <input type="hidden" name="invoiceId" value="${requestScope.invoice.invoiceId}" />
                <input type="hidden" name="reqId" value="${requestScope.invoice.requirement.requirementId}" />

                <div class="mb-3">
                    <label class="form-label"><strong>Price:</strong></label>
                    <input type="text" class="form-control" name="price" value="${requestScope.invoice.price}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label"><strong>Note:</strong></label>
                    <textarea class="form-control note-input" name="note" rows="4">${requestScope.invoice.note}</textarea>
                </div>


                <p class="mt-3"><strong>Status:</strong> ${requestScope.invoice.status}</p>
                <br>

                <button type="submit" class="btn btn-danger w-100">Update</button>
            </form>

            <a href="task" class="btn btn-secondary w-100 mt-2">Back</a>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
