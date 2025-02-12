<%-- 
    Document   : listTechnicians
    Created on : Feb 6, 2025, 10:02:33 PM
    Author     : khang
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Technicians List</title>
</head>
<body>
    <h2>Technicians List</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Staff ID</th>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="technician" items="${technicians}">
                <tr>
                    <td>${technician.staffId}</td>
                    <td>${technician.username}</td>
                    <td>${technician.firstName}</td>
                    <td>${technician.lastName}</td>
                    <td>${technician.email}</td>
                    <td>
                        <a href="TechnicianController?action=edit&staffId=${technician.staffId}">Edit</a> |
                        <a href="TechnicianController?action=delete&staffId=${technician.staffId}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="addTechnician.jsp">Add New Technician</a>
</body>
</html>
