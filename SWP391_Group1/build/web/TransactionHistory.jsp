<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Transaction History</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Completed Transactions</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Customer ID</th>
                    <th>Invoice ID</th>
                    <th>Amount</th>
                    <th>Payment Method</th>
                    <th>Transaction Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="t" items="${requestScope.list}">
                <tr>
                    <td>${t.transactionId}</td>
                    <td>${t.customerId}</td>
                    <td>${t.invoiceId}</td>
                    <td>${t.amount}</td>
                    <td>${t.paymentMethod}</td>
                    <td>${t.transactionDate}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>