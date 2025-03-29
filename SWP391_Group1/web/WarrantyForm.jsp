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
        <c:import url="navbar.jsp"/>

        <div class="wrapper row-offcanvas row-offcanvas-left">
            <c:import url="menu.jsp"/>


            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">

                <!-- Main content -->
                <section class="content">

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="panel">
                                <header class="panel-heading">
                                    Search Warranty Form

                                </header>
                                
                                <div class="panel-body table-responsive">


                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>Form ID</th>
                                                <th>Product ID</th>
                                                <th>Start Date</th>
                                                <th>End Date</th>
                                                <th>Status</th>
                                                <th>Verified</th>
                                                <th>Fault Type</th>
                                                <th>Img</th>

                                            </tr>
                                        </thead>
                                        <c:if test="${requestScope.form == null || requestScope.form.isEmpty()}">
                                            <tr>
                                                <td colspan="7" style="text-align: center;">Your Warranty Form is not exist</td>
                                            </tr>
                                        </c:if>


                                        <c:forEach var="w" items="${form}">

                                            <tr>
                                                <td>${w.formId}</td>
                                                <td>${w.product.productId}</td>
                                                <td>${w.startDate}</td>
                                                <td>${w.endDate}</td>
                                                <td>${w.status}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${w.technicianVerify eq 'yes'}">
                                                            <span style="color: green; font-weight: bold;">Đã xác nhận</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span style="color: red; font-weight: bold;">Đã từ chối</span>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </td>
                                                <td>${w.faultType}</td>
                                                <td><img src="${w.imgUrl}" alt="Hình ảnh" width="100" height="100"></td>
                                            </tr>
                                        </c:forEach>
                                    </table>

                                    <br>
                                    <div class="table-foot">
                                        <ul class="pagination pagination-sm no-margin pull-right">
                                            <c:forEach begin="1" end="${requestScope.endpage}" var="i">
                                                <li>
                                                    <a class="${tag == i ? 'active' : ''}"
                                                       href="warrantyformdetail?index=${i}&productid=${requestScope.productid}">${i}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>



                                    <br>
                                    <a href="javascript:window.history.back();">Back</a>
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
    /* Căn giữa nội dung trong ô */
    .center-content {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    /* Style chung cho nút */
    button {
        border: none;
        padding: 10px 15px;
        border-radius: 5px;
        cursor: pointer;
        font-size: 14px;
        transition: 0.3s;
        margin-top: 5px; /* Tạo khoảng cách giữa nút và nội dung */
    }

    /* Định dạng thẻ <a> bên trong nút */
    button a {
        text-decoration: none;
        color: white;
        font-weight: bold;
        display: block;
    }

    /* Nút bảo hành miễn phí */
    button.free-warranty {
        background-color: #28a745; /* Màu xanh lá */
    }

    button.free-warranty:hover {
        background-color: #218838;
    }

    /* Nút bảo hành có phí */
    button.paid-warranty {
        background-color: #dc3545; /* Màu đỏ */
    }

    button.paid-warranty:hover {
        background-color: #c82333;
    }

    /* Màu đỏ cho thông báo */
    .warning-text {
        color: red;
        font-weight: bold;
        margin-bottom: 5px;
    }

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