<%-- 
    Document   : menu
    Created on : Feb 21, 2025, 9:39:48 AM
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
        <header class="header">
            <a href="home" class="logo">
                ASUS
            </a>
            <nav class="navbar navbar-static-top" role="navigation">
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <c:if test="${sessionScope.Customer != null}">
                    <div class="navbar-right">
                        <ul class="nav navbar-nav">
                            <li class="dropdown messages-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-envelope"></i>
                                    <span class="label label-success">4</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header">You have 4 messages</li>
                                    <li>
                                        <!-- inner menu: contains the actual data -->
                                        <ul class="menu">
                                            <li><!-- start message -->
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="img/avatar3.png" class="img-circle" alt="User Image" />
                                                    </div>
                                                    <h4>
                                                        Support Team
                                                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                                    </h4>
                                                    <p>Why not buy a new awesome theme?</p>
                                                </a>
                                            </li><!-- end message -->
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="img/avatar2.png" class="img-circle" alt="user image" />
                                                    </div>
                                                    <h4>
                                                        Director Design Team
                                                        <small><i class="fa fa-clock-o"></i> 2 hours</small>
                                                    </h4>
                                                    <p>Why not buy a new awesome theme?</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="img/avatar.png" class="img-circle" alt="user image" />
                                                    </div>
                                                    <h4>
                                                        Developers
                                                        <small><i class="fa fa-clock-o"></i> Today</small>
                                                    </h4>
                                                    <p>Why not buy a new awesome theme?</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="img/avatar2.png" class="img-circle" alt="user image" />
                                                    </div>
                                                    <h4>
                                                        Sales Department
                                                        <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                                    </h4>
                                                    <p>Why not buy a new awesome theme?</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="img/avatar.png" class="img-circle" alt="user image" />
                                                    </div>
                                                    <h4>
                                                        Reviewers
                                                        <small><i class="fa fa-clock-o"></i> 2 days</small>
                                                    </h4>
                                                    <p>Why not buy a new awesome theme?</p>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#">See All Messages</a></li>
                                </ul>
                            </li>

                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu">

                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-user"></i>
                                    <span>${sessionScope.Customer.username}<i class="caret"></i></span>
                                </a>
                                <ul class="dropdown-menu dropdown-custom dropdown-menu-right">
                                    <li class="dropdown-header text-center">Account</li>

                                    <li>
                                        <a href="#">
                                            <i class="fa fa-clock-o fa-fw pull-right"></i>
                                            <span class="badge badge-success pull-right">10</span> Updates</a>
                                        <a href="#">
                                            <i class="fa fa-envelope-o fa-fw pull-right"></i>
                                            <span class="badge badge-danger pull-right">5</span> Messages</a>
                                        <a href="#"><i class="fa fa-magnet fa-fw pull-right"></i>
                                            <span class="badge badge-info pull-right">3</span> Subscriptions</a>
                                        <a href="#"><i class="fa fa-question fa-fw pull-right"></i> <span
                                                class="badge pull-right">11</span> FAQ</a>
                                    </li>

                                    <li class="divider"></li>

                                    <li>
                                        <a href="#">
                                            <i class="fa fa-user fa-fw pull-right"></i>
                                            Profile
                                        </a>
                                        <a data-toggle="modal" href="#modal-user-settings">
                                            <i class="fa fa-cog fa-fw pull-right"></i>
                                            Settings
                                        </a>
                                    </li>

                                    <li class="divider"></li>

                                    <li>
                                        <a href="logout"><i class="fa fa-ban fa-fw pull-right"></i> Logout</a>
                                    </li>  
                                </ul>

                            </li>
                        </ul>
                                
                    </div>
                </c:if>

                <c:if test="${sessionScope.Customer == null}">
                    <div class="navbar-right">
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="login">Login</a>
                            </li>  
                        </ul>
                    </div>
                </c:if>
            </nav>
        </header>    
    </body>
</html>

