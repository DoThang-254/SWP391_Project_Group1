<%-- 
    Document   : ServiceManager
    Created on : Jan 24, 2025, 8:55:03 PM
    Author     : thang
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Service Manager</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Morris chart -->
        <link href="css/morris/morris.css" rel="stylesheet" type="text/css" />
        <!-- jvectormap -->
        <link href="css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
        <!-- Date Picker -->
        <link href="css/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
        <!-- fullCalendar -->
        <!-- <link href="css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" /> -->
        <!-- Daterange picker -->
        <link href="css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <!-- iCheck for checkboxes and radio inputs -->
        <link href="css/iCheck/all.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <!-- <link href="css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" /> -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Theme style -->
        <link href="css/style.css" rel="stylesheet" type="text/css" />



        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
              <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
              <![endif]-->

        <style type="text/css">

        </style>
    </head>

    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="index.html" class="logo">
                ASUS
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        <!-- Messages: style can be found in dropdown.less-->
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
                                                    <img src="img/26115.jpg" class="img-circle" alt="User Image" />
                                                </div>
                                                <h4>
                                                    Support Team
                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                                <small class="pull-right"><i class="fa fa-clock-o"></i> 5 mins</small>
                                            </a>
                                        </li><!-- end message -->
                                        <li>
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="img/26115.jpg" class="img-circle" alt="user image" />
                                                </div>
                                                <h4>
                                                    Director Design Team

                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                                <small class="pull-right"><i class="fa fa-clock-o"></i> 2 hours</small>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="img/avatar.png" class="img-circle" alt="user image" />
                                                </div>
                                                <h4>
                                                    Developers

                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                                <small class="pull-right"><i class="fa fa-clock-o"></i> Today</small>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="img/26115.jpg" class="img-circle" alt="user image" />
                                                </div>
                                                <h4>
                                                    Sales Department

                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                                <small class="pull-right"><i class="fa fa-clock-o"></i> Yesterday</small>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="img/avatar.png" class="img-circle" alt="user image" />
                                                </div>
                                                <h4>
                                                    Reviewers

                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                                <small class="pull-right"><i class="fa fa-clock-o"></i> 2 days</small>
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
                                <span>Duy Khang <i class="caret"></i></span>
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
                                    <a href="#"><i class="fa fa-ban fa-fw pull-right"></i> Logout</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="img/26115.jpg" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>Hello, Khang</p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
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
                    <!-- /.search form -->
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li class="active">
                            <a href="index.html">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                            </a>
                        </li>
                        <li>
                            <a href="general.html">
                                <i class="fa fa-gavel"></i> <span>General</span>
                            </a>
                        </li>

                        <li>
                            <a href="basic_form.html">
                                <i class="fa fa-globe"></i> <span>Basic Elements</span>
                            </a>
                        </li>

                        <li>
                            <a href="simple.html">
                                <i class="fa fa-glass"></i> <span>Simple tables</span>
                            </a>
                        </li>

                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <aside class="right-side">

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-8">
                            <section class="panel">
                                <header class="panel-heading">
                                    Technician List
                                </header>

                                <div class="panel-body table-responsive">
                                    <div class="box-tools m-b-15">
                                        <form action="technicianlist" method="get" class="input-group">
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
                                                <th>Fullname</th>
                                                <th>Email</th>
                                                <th>Phone</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="technician" items="${technicians}">
                                                <tr>
                                                    <td>${technician.staffId}</td>
                                                    <td>${technician.username}</td>
                                                    <td>${technician.firstName} ${technician.lastName}</td>
                                                    <td>${technician.email}</td>
                                                    <td>${technician.phone}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${technician.status == 'Active'}">
                                                                <span class="label label-primary">${technician.status}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="label label-danger">${technician.status}</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>

                                                    <td>
                                                        <a href="technicianlist?action=edit&staffId=${technician.staffId}">Edit</a> |
                                                        <a href="technicianlist?action=delete&staffId=${technician.staffId}">Delete</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>

                                                   <div class="row">
                                                       <div class="col-md-6">
                                                           <a class="btn btn-success btn-sm" href="addTechnician.jsp">Add New</a>
                                                       </div>
                                                       <div class="col-md-6 text-right">
                                                           <span style="margin-right: 20px" class="total-records">
                                                                ${startRecord}-${endRecord} of ${totalTechnicians}
                                                           </span>
                                                           <ul class="pagination pagination-sm no-margin pull-right">
                                                               <c:if test="${currentPage > 1}">
                                                                   <li><a href="technicianlist?page=${currentPage - 1}">&laquo;</a></li>
                                                                   </c:if>
                                                                   <c:forEach var="i" begin="1" end="${totalPages}">
                                                                   <li class="${i == currentPage ? 'active' : ''}">
                                                                       <a href="technicianlist?page=${i}">${i}</a>
                                                                   </li>
                                                               </c:forEach>
                                                               <c:if test="${currentPage < totalPages}">
                                                                   <li><a href="technicianlist?page=${currentPage + 1}">&raquo;</a></li>
                                                                   </c:if>
                                                           </ul>
                                                       </div>
                                                   </div>




                                </div>
                            </section>
                        </div>
                    </div>
                </section>



                <div class="row">

                    <div class="col-md-7">
                        <section class="panel tasks-widget">
                            <header class="panel-heading">
                                Manager list
                            </header>
                            <div class="panel-body">

                                <div class="task-content">

                                    <ul class="task-list">
                                        <li>

                                            <div class="task-title">
                                                <span class="task-title-sp">Director is Modern Dashboard</span>
                                                <span class="label label-success">2 Days</span>
                                                <div class="pull-right hidden-phone">
                                                    <button class="btn btn-default btn-xs">
                                                        <i class="fa fa-eye"></i>
                                                    </button>


                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-pencil"></i></button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-times"></i></button>
                                                </div>
                                            </div>
                                        </li>
                                        <li>

                                            <div class="task-title">
                                                <span class="task-title-sp">Fully Responsive & Bootstrap 3.0.2
                                                    Compatible</span>
                                                <span class="label label-danger">Done</span>
                                                <div class="pull-right hidden-phone">
                                                    <button class="btn btn-default btn-xs">
                                                        <i class="fa fa-eye"></i>
                                                    </button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-pencil"></i></button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-times"></i></button>
                                                </div>
                                            </div>
                                        </li>
                                        <li>

                                            <div class="task-title">
                                                <span class="task-title-sp">Latest Design Concept</span>
                                                <span class="label label-warning">Company</span>
                                                <div class="pull-right hidden-phone">
                                                    <button class="btn btn-default btn-xs">
                                                        <i class="fa fa-eye"></i>
                                                    </button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-pencil"></i></button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-times"></i></button>
                                                </div>
                                            </div>
                                        </li>
                                        <li>

                                            <div class="task-title">
                                                <span class="task-title-sp">Write well documentation for this
                                                    theme</span>
                                                <span class="label label-primary">3 Days</span>
                                                <div class="pull-right hidden-phone">
                                                    <button class="btn btn-default btn-xs">
                                                        <i class="fa fa-eye"></i>
                                                    </button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-pencil"></i></button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-times"></i></button>
                                                </div>
                                            </div>
                                        </li>
                                        <li>

                                            <div class="task-title">
                                                <span class="task-title-sp">Don't bother to download this
                                                    Dashbord</span>
                                                <span class="label label-inverse">Now</span>
                                                <div class="pull-right">
                                                    <button class="btn btn-default btn-xs">
                                                        <i class="fa fa-eye"></i>
                                                    </button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-pencil"></i></button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-times"></i></button>
                                                </div>
                                            </div>
                                        </li>
                                        <li>

                                            <div class="task-title">
                                                <span class="task-title-sp">Give feedback for the template</span>
                                                <span class="label label-success">2 Days</span>
                                                <div class="pull-right hidden-phone">
                                                    <button class="btn btn-default btn-xs">
                                                        <i class="fa fa-eye"></i>
                                                    </button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-pencil"></i></button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-times"></i></button>
                                                </div>
                                            </div>
                                        </li>
                                        <li>

                                            <div class="task-title">
                                                <span class="task-title-sp">Tell your friends about this admin
                                                    template</span>
                                                <span class="label label-danger">Now</span>
                                                <div class="pull-right hidden-phone">
                                                    <button class="btn btn-default btn-xs">
                                                        <i class="fa fa-eye"></i>
                                                    </button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-pencil"></i></button>
                                                    <button class="btn btn-default btn-xs"><i
                                                            class="fa fa-times"></i></button>
                                                </div>
                                            </div>
                                        </li>

                                    </ul>
                                </div>

                                <div class=" add-task-row">
                                    <div class="table-foot">
                                        <ul class="pagination pagination-sm no-margin pull-right">
                                            <li><a href="#">&laquo;</a></li>
                                            <li><a href="#">1</a></li>
                                            <li><a href="#">2</a></li>
                                            <li><a href="#">3</a></li>
                                            <li><a href="#">&raquo;</a></li>
                                        </ul>
                                    </div>
                                    <a class="btn btn-success btn-sm pull-left" href="#">Add New Tasks</a>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
                <!-- row end -->
                </section><!-- /.content -->

                <div class="footer-main">
                    Copyright &copy Director, 2025
                </div>
            </aside><!-- /.right-side -->

        </div><!-- ./wrapper -->


        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>

        <!-- jQuery UI 1.10.3 -->
        <script src="js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <!-- daterangepicker -->
        <script src="js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>

        <script src="js/plugins/chart.js" type="text/javascript"></script>

        <!-- datepicker
            <script src="js/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>-->
        <!-- Bootstrap WYSIHTML5
            <script src="js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>-->
        <!-- iCheck -->
        <script src="js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        <!-- calendar -->
        <script src="js/plugins/fullcalendar/fullcalendar.js" type="text/javascript"></script>

        <!-- Director App -->
        <script src="js/Director/app.js" type="text/javascript"></script>

        <!-- Director dashboard demo (This is only for demo purposes) -->
        <script src="js/Director/dashboard.js" type="text/javascript"></script>

        <!-- Director for demo purposes -->
        <script type="text/javascript">
            $('input').on('ifChecked', function (event) {
                // var element = $(this).parent().find('input:checkbox:first');
                // element.parent().parent().parent().addClass('highlight');
                $(this).parents('li').addClass("task-done");
                console.log('ok');
            });
            $('input').on('ifUnchecked', function (event) {
                // var element = $(this).parent().find('input:checkbox:first');
                // element.parent().parent().parent().removeClass('highlight');
                $(this).parents('li').removeClass("task-done");
                console.log('not');
            });

        </script>
        <script>
            $('#noti-box').slimScroll({
                height: '400px',
                size: '5px',
                BorderRadius: '5px'
            });

            $('input[type="checkbox"].flat-grey, input[type="radio"].flat-grey').iCheck({
                checkboxClass: 'icheckbox_flat-grey',
                radioClass: 'iradio_flat-grey'
            });
        </script>
        <script type="text/javascript">
            $(function () {
                "use strict";
                //BAR CHART
                var data = {
                    labels: ["January", "February", "March", "April", "May", "June", "July"],
                    datasets: [
                        {
                            label: "My First dataset",
                            fillColor: "rgba(220,220,220,0.2)",
                            strokeColor: "rgba(220,220,220,1)",
                            pointColor: "rgba(220,220,220,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(220,220,220,1)",
                            data: [65, 59, 80, 81, 56, 55, 40]
                        },
                        {
                            label: "My Second dataset",
                            fillColor: "rgba(151,187,205,0.2)",
                            strokeColor: "rgba(151,187,205,1)",
                            pointColor: "rgba(151,187,205,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(151,187,205,1)",
                            data: [28, 48, 40, 19, 86, 27, 90]
                        }
                    ]
                };
                new Chart(document.getElementById("linechart").getContext("2d")).Line(data, {
                    responsive: true,
                    maintainAspectRatio: false,
                });

            });
            // Chart.defaults.global.responsive = true;
        </script>
    </body>

</html>
