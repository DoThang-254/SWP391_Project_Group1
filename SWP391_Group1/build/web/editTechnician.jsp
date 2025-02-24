<%-- Document   : editTechnician Created on : Feb 6, 2025, 10:02:56 PM Author : khang --%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Technician</title>
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
                        <img src="img/26115.jpg" class="img-circle" alt="User Image" />
                    </div>
                    <div class="pull-left info">
                        <p>Hello, Jane</p>
                        <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                    </div>
                </div>
                <ul class="sidebar-menu">
                    <li><a href="index.html"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
                    <li><a href="technicianlist"><i class="fa fa-users"></i> <span>Technicians</span></a></li>
                </ul>
            </section>
        </aside>

        <aside class="right-side">
            <section class="content">
                <div class="container">
                    <h2>Edit Technician</h2>

                    <form action="technicianlist" method="post">
                        <input type="hidden" name="action" value="update" />
                        <input type="hidden" name="staffId" value="${technician.staffId}" />

                        <div class="form-group">
                            <label>Username:</label>
                            <input type="text" name="username" class="form-control" value="${technician.username}" required />
                        </div>

                        <div class="form-group">
                            <label>First Name:</label>
                            <input type="text" name="firstName" class="form-control" value="${technician.firstName}" required />
                        </div>

                        <div class="form-group">
                            <label>Last Name:</label>
                            <input type="text" name="lastName" class="form-control" value="${technician.lastName}" required />
                        </div>

                        <div class="form-group">
                            <label>Email:</label>
                            <input type="email" name="email" class="form-control" value="${technician.email}" required />
                        </div>

                        <div class="form-group">
                            <label>Phone:</label>
                            <input type="text" name="phone" class="form-control" value="${technician.phone}" required />
                        </div>

                        <div class="form-group">
                            <label>Gender:</label>
                            <select name="gender" class="form-control" required>
                                <option value="Male" ${technician.gender == 'Male' ? 'selected' : ''}>Male</option>
                                <option value="Female" ${technician.gender == 'Female' ? 'selected' : ''}>Female</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Birth Date:</label>
                            <input type="date" name="birthDate" class="form-control" value="${technician.birthDate}" required />
                        </div>

                        <div class="form-group">
                            <label>Status:</label>
                            <select name="status" class="form-control" required>
                                <option value="Active" ${technician.status == 'Active' ? 'selected' : ''}>Active</option>
                                <option value="Inactive" ${technician.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                            </select>
                        </div>

                                <div class="form-group">
                                    <label>Role:</label>
                                    <input type="text" class="form-control" value="Technician" readonly />
                                    <input type="hidden" name="roleId" value="${technician.roleId}" />
                                </div>


                        <button type="submit" class="btn btn-primary btn-custom">Update Technician</button>
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
