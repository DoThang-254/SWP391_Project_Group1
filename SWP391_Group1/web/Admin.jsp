<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Manager</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

    <!-- Bootstrap CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />

    <style type="text/css">
        .panel-heading {
            font-size: 20px;
            font-weight: bold;
        }
    </style>
</head>

<body class="skin-black">
    <header class="header">
        <a href="index.html" class="logo">ASUS</a>
        <nav class="navbar navbar-static-top" role="navigation">
            <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <div class="navbar-right">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-user"></i>
                            <span>Admin <i class="caret"></i></span>
                        </a>
                        <ul class="dropdown-menu dropdown-custom dropdown-menu-right">
                            <li class="dropdown-header text-center">Account</li>
                            <li><a href="#"><i class="fa fa-user fa-fw pull-right"></i> Profile</a></li>
                            <li><a href="#"><i class="fa fa-cog fa-fw pull-right"></i> Settings</a></li>
                            <li class="divider"></li>
                            <li><a href="#"><i class="fa fa-ban fa-fw pull-right"></i> Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
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
                    <li><a href="adminlist"><i class="fa fa-user"></i> <span>Admins</span></a></li>
                    <li><a href="componentlist"><i class="fa fa-dashboard"></i> <span>Component</span></a></li>
                    
                </ul>
            </section>
        </aside>

        <aside class="right-side">
            <section class="content">
                <div class="row">
                    <div class="col-md-8">
                        <section class="panel">
                            <header class="panel-heading">
                                Admin List
                            </header>

                            <div class="panel-body table-responsive">
                                <div class="box-tools m-b-15">
                                    <form action="adminlist" method="get" class="input-group">
                                        <input type="text" name="search" class="form-control input-sm pull-right"
                                               style="width: 150px;" placeholder="Search" value="${param.search}" />
                                        <div class="input-group-btn">
                                            <button type="submit" class="btn btn-default input-sm">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </div>
                                    </form>
                                </div>

                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>StaffID</th>
                                            <th>Username</th>
                                            <th>Full Name</th>
                                            <th>Email</th>
                                            <th>Phone</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="admin" items="${admins}">
                                            <tr>
                                                <td>${admin.staffId}</td>
                                                <td>${admin.username}</td>
                                                <td>${admin.firstName} ${admin.lastName}</td>
                                                <td>${admin.email}</td>
                                                <td>${admin.phone}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${admin.status == 'Active'}">
                                                            <span class="label label-primary">${admin.status}</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="label label-danger">${admin.status}</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <a href="adminlist?action=edit&staffId=${admin.staffId}">Edit</a> |
                                                    <a href="adminlist?action=delete&staffId=${admin.staffId}">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                                <div class="row">
                                    <div class="col-md-6">
                                        <a class="btn btn-success btn-sm" href="addAdmin.jsp">Add New</a>
                                    </div>
                                    <div class="col-md-6 text-right">
                                        <span style="margin-right: 20px" class="total-records">
                                            ${startRecord}-${endRecord} of ${totalAdmins}
                                        </span>
                                        <ul class="pagination pagination-sm no-margin pull-right">
                                            <c:if test="${currentPage > 1}">
                                                <li><a href="adminlist?page=${currentPage - 1}">&laquo;</a></li>
                                            </c:if>
                                            <c:forEach var="i" begin="1" end="${totalPages}">
                                                <li class="${i == currentPage ? 'active' : ''}">
                                                    <a href="adminlist?page=${i}">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <c:if test="${currentPage < totalPages}">
                                                <li><a href="adminlist?page=${currentPage + 1}">&raquo;</a></li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </section>

            <div class="footer-main">
                Copyright &copy Director, 2025
            </div>
        </aside>
    </div>

    <!-- Bootstrap JS -->
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>
