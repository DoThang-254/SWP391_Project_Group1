<%-- 
    Document   : simple
    Created on : Jan 24, 2025, 10:20:41 AM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Customer</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- google font -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Theme style -->
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link href="css/paging.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
              <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
            <![endif]-->

    </head>

    <body class="skin-black">
        <header class="header">
            <a href="Customer.jsp" class="logo">
                ASUS
            </a>
            <nav class="navbar navbar-static-top" role="navigation">
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
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
                            <p>Hello, ${sessionScope.Customer.username}</p>

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
                    <ul class="sidebar-menu">

                        <li class="active">
                            <a href="CustomerHomePage.jsp">
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
                                <i class="fa fa-gavel"></i> <span>Warranty Inforamtion</span>
                            </a>
                        </li>

                        <li>
                            <a href="basic_form.html">
                                <i class="fa fa-globe"></i> <span>Basic Elements</span>
                            </a>
                        </li>



                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">

                <!-- Main content -->
                <section class="content">

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="panel">
                                <header class="panel-heading">
                                    Search Warranty Information

                                </header>
                                <div class="filter-section">
                                    <form action="searchinformation" method="get" class="row">
                                        <input type="hidden" name="sort" value="${requestScope.sort}">
                                        <input type="hidden" name="order" value="${requestScope.order}">
                                        <div class="box-tools m-b-15">
                                            <div class="input-group">                                            
                                                <input type="text" name="table_search" class="form-control input-sm pull-right"
                                                       style="width: 150px;" placeholder="Search" 
                                                       value="${not empty requestScope.save ? requestScope.save : ''}" />

                                                <div class="input-group-btn">
                                                    <button type="submit" class="btn btn-sm btn-default">
                                                        <i class="fa fa-search"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Filter Product Name -->


                                        <!-- Filter Brand -->
                                        <div class="col-md-3">
                                            <label for="filterBrand">Brand</label>
                                            <select name="filterBrand" id="filterBrand" class="form-control">
                                                <option value="" ${empty requestScope.brand ? 'selected' : ''}>-- All Brands --</option>
                                                <option value="BrandY" ${requestScope.brand == 'BrandY' ? 'selected' : ''}>BrandY</option>
                                                <option value="BrandX" ${requestScope.brand == 'BrandX' ? 'selected' : ''}>BrandX</option>
                                            </select>
                                        </div>




                                        <!-- Filter Price -->
                                        <div class="col-md-3">

                                            <label for="filterPriceRange">Price Range</label>
                                            <select name="filterPriceRange" id="filterPriceRange" class="form-control">
                                                <option value="" ${empty requestScope.priceRange ? 'selected' : ''}>Tất cả giá</option>
                                                <option value="0-5000" ${requestScope.priceRange == '0-5000' ? 'selected' : ''}>Dưới 5.000</option>
                                                <option value="5000-10000" ${requestScope.priceRange == '5000-10000' ? 'selected' : ''}>5.000 - 10.000</option>
                                                <option value="10000-15000" ${requestScope.priceRange == '10000-15000' ? 'selected' : ''}>10.000 - 15.000</option>
                                                <option value="15000-20000" ${requestScope.priceRange == '15000-20000' ? 'selected' : ''}>15.000 - 20.000</option>
                                                <option value="20000+" ${requestScope.priceRange == '20000+' ? 'selected' : ''}>Trên 20.000</option>
                                            </select>

                                        </div>


                                        <!-- Submit Button -->
                                        <div class="col-md-12 text-right mt-3">
                                            <button type="submit" class="btn btn-primary">
                                                <i class="fa fa-filter"></i> Apply Filters
                                            </button>
                                            <a href="searchinformation" class="btn btn-secondary">
                                                <i class="fa fa-times"></i> Reset
                                            </a>
                                        </div>
                                    </form>
                                </div>
                                <div class="panel-body table-responsive">


                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>Product ID
                                                    <a href="searchinformation?sort=productid&order=asc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}">
                                                        <i class="fa fa-arrow-up"></i>
                                                    </a>
                                                    <a href="searchinformation?sort=productid&order=desc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}">
                                                        <i class="fa fa-arrow-down"></i>
                                                    </a>
                                                </th>

                                                <th>Product Name
                                                    <a href="searchinformation?sort=productname&order=asc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&filterPriceRange=${requestScope.priceRange}"><i class="fa fa-arrow-up"></i></a>
                                                    <a href="searchinformation?sort=productname&order=desc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&filterPriceRange=${requestScope.priceRange}"><i class="fa fa-arrow-down"></i></a>
                                                </th>
                                                <th>Warranty End Time 
                                                    <a href="searchinformation?sort=WarrantyDateTime&order=asc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&filterPriceRange=${requestScope.priceRange}"><i class="fa fa-arrow-up"></i></a>
                                                    <a href="searchinformation?sort=WarrantyDateTime&order=desc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&filterPriceRange=${requestScope.priceRange}"><i class="fa fa-arrow-down"></i></a>
                                                </th>
                                                <th>Price
                                                    <a href="searchinformation?sort=Price&order=asc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&filterPriceRange=${requestScope.priceRange}"><i class="fa fa-arrow-up"></i></a>
                                                    <a href="searchinformation?sort=Price&order=desc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&filterPriceRange=${requestScope.priceRange}"><i class="fa fa-arrow-down"></i></a>
                                                </th>
                                                <th>Brand
                                                    <a href="searchinformation?sort=Brand&order=asc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&filterPriceRange=${requestScope.priceRange}"><i class="fa fa-arrow-up"></i></a>
                                                    <a href="searchinformation?sort=Brand&order=desc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&filterPriceRange=${requestScope.priceRange}"><i class="fa fa-arrow-down"></i></a>
                                                </th>
                                                <th>Buy Time
                                                    <a href="searchinformation?sort=BuyTime&order=asc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&filterPriceRange=${requestScope.priceRange}"><i class="fa fa-arrow-up"></i></a>
                                                    <a href="searchinformation?sort=BuyTime&order=desc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&filterPriceRange=${requestScope.priceRange}"><i class="fa fa-arrow-down"></i></a>
                                                </th>
                                                <th>View Details

                                                </th>
                                            </tr>
                                        </thead>
                                        <c:if test="${requestScope.listA == null || requestScope.listA.isEmpty()}">
                                            <tr>
                                                <td colspan="7" style="text-align: center;">Your Product is not exist</td>
                                            </tr>
                                        </c:if>
                                        <c:forEach var="c" items="${requestScope.listA}">
                                            <tr>
                                                <td>${c.productId}</td>
                                                <td>${c.productName}</td>
                                                <td>${c.warrantyDateTime}</td>
                                                <td>${c.price}</td>
                                                <td>${c.brand}</td>
                                                <td>${c.buyTime}</td>
                                                <td>
                                                    <!-- Bootstrap Modal -->
                                                    <div class="modal fade" id="productModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="productTitle">Product Details</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <p><strong>Product ID:</strong> <span id="modalProductId"></span></p>
                                                                    <p><strong>Name:</strong> <span id="modalProductName"></span></p>
                                                                    <p><strong>Brand:</strong> <span id="modalProductBrand"></span></p>
                                                                    <p><strong>Price:</strong> <span id="modalProductPrice"></span></p>
                                                                    <p><strong>Description:</strong> <span id="modalProductDescription"></span></p>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>


                                                    <a href="detail?productid=${c.productId}" class="btn btn-info btn-sm" title="View Details">
                                                        <i class="fa fa-eye"></i>
                                                    </a>




                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>

                                    <br>
                                    <div class="table-foot">
                                        <ul class="pagination pagination-sm no-margin pull-right">
                                            <c:forEach begin="1" end="${requestScope.endpage}" var="i">
                                                <li>
                                                    <a class="${tag == i ? 'active' : ''}"
                                                       href="searchinformation?index=${i}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&sort=${requestScope.sort}&order=${requestScope.order}">${i}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </section><!-- /.content -->

            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Director App -->
        <script src="js/Director/app.js" type="text/javascript"></script>
    </body>

</html>

<style>
    /* Style cho biểu tượng filter và sort */
    .fa-sort, .fa-filter {
        margin-left: 5px;
        cursor: pointer;
        color: #555;
        transition: color 0.3s ease;
    }

    .fa-sort:hover, .fa-filter:hover {
        color: #007bff;
    }

    /* Style cho nút hành động View Details */
    .btn-info {
        background-color: #17a2b8;
        border-color: #17a2b8;
        color: #fff;
        border-radius: 4px;
        padding: 5px 10px;
        font-size: 12px;
    }

    .btn-info:hover {
        background-color: #138496;
        border-color: #117a8b;
    }

    /* Style cho icon trong nút */
    .btn-info .fa-eye {
        margin-right: 5px;
    }

    /* Style cho bảng và các cột */
    .table th, .table td {
        vertical-align: middle;
        text-align: center;
    }

    .pagination .active {
        font-weight: bold;
        color: #fff;
        background-color: red;
        border-color: #007bff;
    }

</style>