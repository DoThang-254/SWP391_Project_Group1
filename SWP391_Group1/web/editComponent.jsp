<%-- 
    Document   : editComponent
    Created on : Mar 2, 2025, 9:55:49 PM
    Author     : khang
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Component</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- Bootstrap CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    
    <style>
        .container {
            max-width: 600px;
            margin-top: 50px;
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .btn-custom {
            width: 100%;
        }
    </style>
</head>
<body class="skin-black">

    <header class="header">
        <a href="index.html" class="logo">ASUS</a>
        <nav class="navbar navbar-static-top"></nav>
    </header>

    <div class="wrapper row-offcanvas row-offcanvas-left">
        <aside class="left-side sidebar-offcanvas">
            <section class="sidebar">
                <ul class="sidebar-menu">
                    <li><a href="componentlist"><i class="fa fa-dashboard"></i> <span>Component</span></a></li>
                    <li><a href="adminlist"><i class="fa fa-user"></i> <span>Admins</span></a></li>
                </ul>
            </section>
        </aside>

        <aside class="right-side">
            <section class="content">
                <div class="container">
                    <h2>Edit Component</h2>

                    <form action="componentlist" method="post">
                        <input type="hidden" name="action" value="update" />
                        <input type="hidden" name="componentId" value="${component.componentId}" />
                        
                        <div class="form-group">
                            <label>Component Name:</label>
                            <input type="text" name="componentName" class="form-control" value="${component.componentName}" required />
                        </div>
                        
                        <div class="form-group">
                            <label>Brand:</label>
                            <input type="text" name="brand" class="form-control" value="${component.brand}" required />
                        </div>
                        
                        <div class="form-group">
                            <label>Status:</label>
                            <select name="status" class="form-control" required>
                                <option value="Available" ${component.status == 'Available' ? 'selected' : ''}>Available</option>
                                <option value="Out of Stock" ${component.status == 'Out of Stock' ? 'selected' : ''}>Out of Stock</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label>Price:</label>
                            <input type="number" name="price" class="form-control" value="${component.price}" required />
                        </div>
                        
                        <div class="form-group">
                            <label>Amount:</label>
                            <input type="number" name="amount" class="form-control" value="${component.amount}" required />
                        </div>
                        
                        <div class="form-group">
                            <label>Staff ID:</label>
                            <input type="text" name="staffId" class="form-control" value="${component.staff.staffId}" required />
                        </div>
                        
                        <div class="form-group">
                            <label>Invoice ID:</label>
                            <input type="number" name="invoiceId" class="form-control" value="${component.invoice.invoiceId}" required />
                        </div>
                        
                        <button type="submit" class="btn btn-primary btn-custom">Update Component</button>
                    </form>
                </div>
            </section>
        </aside>
    </div>

    <!-- Bootstrap JS -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
