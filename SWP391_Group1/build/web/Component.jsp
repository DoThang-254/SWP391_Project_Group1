<%-- 
    Document   : Component
    Created on : Mar 2, 2025, 9:51:25 PM
    Author     : khang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Component Manager</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" />
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
            </nav>
        </header>

        <div class="wrapper row-offcanvas row-offcanvas-left">
            <aside class="left-side sidebar-offcanvas">
                <section class="sidebar">
                    <ul class="sidebar-menu">
                        <li><a href="adminlist"><i class="fa fa-user"></i> <span>Admins</span></a></li>
                        <li><a href="componentlist"><i class="fa fa-dashboard"></i> <span>Component</span></a></li>

                    </ul>
                </section>
            </aside>

            <aside class="right-side">
                <section class="content">
                    <div class="row">
                        <div class="col-md-10">
                            <section class="panel">
                                <header class="panel-heading">
                                    Component List
                                </header>
                                <form action="componentlist" method="get" class="input-group">
                                    <input type="text" name="search" class="form-control input-sm pull-right" 
                                           style="width: 150px;" placeholder="Search" value="${search}" />
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-default input-sm">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </form>

                                <div class="panel-body table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>Component ID</th>
                                                <th>Name</th>
                                                <th>Brand</th>
                                                <th>Status</th>
                                                <th>Price</th>
                                                <th>Amount</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="component" items="${components}">
                                                <tr>
                                                    <td>${component.componentId}</td>
                                                    <td>${component.componentName}</td>
                                                    <td>${component.brand}</td>
                                                    <td>${component.status}</td>
                                                    <td>${component.price}</td>
                                                    <td>${component.amount}</td>
                                                    <td>
                                                        <a href="componentlist?action=edit&componentId=${component.componentId}">Edit</a> | 
                                                        <a href="componentlist?action=delete&componentId=${component.componentId}">Delete</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <a class="btn btn-success btn-sm" href="addComponent.jsp">Add New Component</a>
                                </div>
                            </section>
                        </div>
                    </div>
                </section>
            </aside>
        </div>

        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
