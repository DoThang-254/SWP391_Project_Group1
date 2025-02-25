<%-- 
    Document   : addAdmin
    Created on : Feb 25, 2025, 6:17:55 PM
    Author     : khang
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Admin</title>
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

    <!-- Header -->
    <header class="header">
        <a href="index.html" class="logo">ASUS</a>
        <nav class="navbar navbar-static-top">
            <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
        </nav>
    </header>

    <div class="wrapper row-offcanvas row-offcanvas-left">
        <aside class="left-side sidebar-offcanvas">
            <section class="sidebar">
                <div class="user-panel">
                    <div class="pull-left image">
                        <img src="img/admin-avatar.jpg" class="img-circle" alt="User Image" />
                    </div>
                    <div class="pull-left info">
                        <p>Hello, Admin</p>
                        <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                    </div>
                </div>
                <ul class="sidebar-menu">
                    <li><a href="index.html"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
                    <li><a href="adminlist"><i class="fa fa-user"></i> <span>Admins</span></a></li>
                </ul>
            </section>
        </aside>

        <aside class="right-side">
            <section class="content">
                <div class="container">
                    <h2>Add Admin</h2>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <form action="adminlist" method="post">
                        <input type="hidden" name="action" value="add" />

                        <div class="form-group">
                            <label>Staff ID:</label>
                            <input type="text" name="staffId" class="form-control" required />
                        </div>

                        <div class="form-group">
                            <label>Username:</label>
                            <input type="text" name="username" class="form-control" required />
                        </div>

                        <div class="form-group">
                            <label>First Name:</label>
                            <input type="text" name="firstName" class="form-control" required />
                        </div>

                        <div class="form-group">
                            <label>Last Name:</label>
                            <input type="text" name="lastName" class="form-control" required />
                        </div>

                        <div class="form-group">
                            <label>Email:</label>
                            <input type="email" name="email" class="form-control" required />
                        </div>

                        <div class="form-group">
                            <label>Phone:</label>
                            <input type="text" name="phone" class="form-control" required />
                        </div>

                        <div class="form-group">
                            <label>Gender:</label>
                            <select name="gender" class="form-control" required>
                                <option value="">Select Gender</option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Birth Date:</label>
                            <input type="date" name="birthDate" class="form-control" required />
                        </div>

                        <div class="form-group">
                            <label>Status:</label>
                            <select name="status" class="form-control" required>
                                <option value="">Select Status</option>
                                <option value="Active">Active</option>
                                <option value="Inactive">Inactive</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Role:</label>
                            <input type="text" class="form-control" value="Admin" readonly />
                            <input type="hidden" name="roleId" value="3" />
                        </div>

                        <button type="submit" class="btn btn-success btn-custom">Add Admin</button>
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
