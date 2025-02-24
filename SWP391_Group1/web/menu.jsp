<%-- 
    Document   : menu
    Created on : Feb 21, 2025, 9:43:50 AM
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
        <!-- sidebar: style can be found in sidebar.less -->
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <c:if test="${sessionScope.Customer != null}">

                            <div class="pull-left image">
                                <img src="img/26115.jpg" class="img-circle" alt="User Image" />
                            </div>
                        </c:if>

                        <div class="pull-left info">

                            <c:if test="${sessionScope.Customer != null}">

                                <p>Hello, ${sessionScope.Customer.username}</p>
                                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                            </c:if>



                        </div>
                    </div>
                    <!-- search form -->
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search..." />
                            <span class="input-group-btn">
                                <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i
                                        class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                    <ul class="sidebar-menu">

                        <li class="active">
                            <a href="home">
                                <i class="fa fa-dashboard"></i> <span>Home page</span>
                            </a>
                        </li>
                        <li>
                            <a href="searchinformation">
                                <i class="fa fa-glass"></i> <span>Searching Information</span>
                            </a>
                        </li>
                        <li>
                            <a href="warrantyinformation">
                                <i class="fa fa-gavel"></i> <span>Warranty Information</span>
                            </a>
                        </li>

                        <li>
                            <a href="blog">
                                <i class="fa fa-globe"></i> <span>Blog</span>
                            </a>
                        </li>



                    </ul>
                </section>

            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->

        <!-- /.sidebar -->
    </body>
</html>
