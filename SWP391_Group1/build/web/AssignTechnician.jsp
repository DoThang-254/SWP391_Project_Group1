<%-- 
    Document   : simple
    Created on : Jan 24, 2025, 10:20:41 AM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
        <c:import url="StaffNavbar.jsp"/>

        <div class="wrapper row-offcanvas row-offcanvas-left">
            <c:import url="StaffMenu.jsp"/>


            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">

                <!-- Main content -->
                <section class="content">

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="panel">
                                <header class="panel-heading">
                                    List Of Technician
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

                                        <div class="col-md-3">
                                            <label for="filterBrand">Brand</label>
                                            <select name="filterBrand" id="filterBrand" class="form-control">
                                                <option value="" ${empty requestScope.brand ? 'selected' : ''}>-- All Brands --</option>
                                                <option value="BrandY" ${requestScope.brand == 'BrandY' ? 'selected' : ''}>BrandY</option>
                                                <option value="BrandX" ${requestScope.brand == 'BrandX' ? 'selected' : ''}>BrandX</option>
                                            </select>
                                        </div>
                                        <div class="col-md-3">
                                            <label for="filterBrand">amount of product</label>
                                            <select name="amount" id="filterBrand" class="form-control">
                                                <option value="">-- All --</option>

                                                <option value="5" > 5 </option>
                                                <option value="6" >6</option>
                                                <option value="7">7</option>
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
                                                <th>Staff ID
                                                    <a href="searchinformation?sort=productid&order=asc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}">
                                                        <i class="fa fa-arrow-up"></i>
                                                    </a>
                                                    <a href="searchinformation?sort=productid&order=desc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}">
                                                        <i class="fa fa-arrow-down"></i>
                                                    </a>
                                                </th>
                                                <th>
                                                    Assign Task
                                                </th>

                                            </tr>
                                        </thead>
                                        <c:if test="${requestScope.list == null || requestScope.list.isEmpty()}">
                                            <tr>
                                                <td colspan="7" style="text-align: center;">Staff is not exist</td>
                                            </tr>
                                        </c:if>
                                        <c:forEach var="s" items="${requestScope.list}">


                                            <tr>
                                                <td>${s.staffId}</td>
                                                <td> <form action="assigntechnician" method="post">
                                                        <input type="hidden" name="requirementId" value="${requestScope.requirementId}">
                                                        <input type="hidden" name="staffId" value="${s.staffId}">
                                                        <input type="submit" value="Assign">
                                                    </form></td>

                                            </tr>
                                        </c:forEach>
                                    </table>

                                    <br>
                                    <div class="table-foot">
                                        <ul class="pagination pagination-sm no-margin pull-right">
                                            <c:forEach begin="1" end="${requestScope.endpage}" var="i">
                                                <li>
                                                    <a class="${tag == i ? 'active' : ''}"
                                                       href="searchinformation?index=${i}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&sort=${requestScope.sort}&order=${requestScope.order}&amount=${requestScope.amount}">${i}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                ${requestScope.errorMessage}


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

    /* Nếu còn bảo hành, nền xanh lá */
    .warranty-active {
        background-color: #28a745; /* Xanh lá */
        color: white;
        font-weight: bold;
    }

    /* Nếu hết hạn bảo hành, nền đỏ */
    .warranty-expired {
        background-color: #dc3545; /* Đỏ */
        color: white;
        font-weight: bold;
    }

</style>