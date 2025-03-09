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
                                    Requirement Warranty Management

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
                                                <th>Requirement ID
                                                    <a href="searchinformation?sort=productid&order=asc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}">
                                                        <i class="fa fa-arrow-up"></i>
                                                    </a>
                                                    <a href="searchinformation?sort=productid&order=desc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}">
                                                        <i class="fa fa-arrow-down"></i>
                                                    </a>
                                                </th>

                                                <th>Product Id
                                                    <a href="searchinformation?sort=productname&order=asc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&filterPriceRange=${requestScope.priceRange}"><i class="fa fa-arrow-up"></i></a>
                                                    <a href="searchinformation?sort=productname&order=desc&index=${requestScope.tag}&table_search=${requestScope.save}&filterBrand=${requestScope.brand}&filterPriceRange=${requestScope.priceRange}"><i class="fa fa-arrow-down"></i></a>
                                                </th>
                                                <th>Customer

                                                </th> 
                                                <th>Register Date

                                                </th> 

                                                <th>Staff

                                                </th> 

                                                <th>Status

                                                </th>
                                                <th>Description

                                                </th>
                                                <th> is pay

                                                </th>
                                                <th>fault type</th>
                                                <th>Customer feedback</th>
                                                <th>Accept requirement</th>

                                                <th>
                                                    Form 
                                                </th> 
                                            </tr>
                                        </thead>
                                        <c:if test="${requestScope.list == null || requestScope.list.isEmpty()}">
                                            <tr>
                                                <td colspan="7" style="text-align: center;">Warranty Form is not exist</td>
                                            </tr>
                                        </c:if>
                                        <script>
                                            function confirmUpdate(form) {
                                                let confirmAction = confirm("Bạn có chắc muốn cập nhật trạng thái này không?");
                                                if (confirmAction) {
                                                    form.submit();
                                                }
                                            }
                                        </script>
                                        <c:forEach var="r" items="${requestScope.list}">
                                            <tr>
                                                <td>${r.requirementId}</td>
                                                <td>${r.product.productId}</td>
                                                <td>${r.customer.firstName} ${r.customer.lastName}</td>
                                                <td>${r.registerDate}</td>
                                                <td>${r.staff.staffId}</td>

                                                <!-- Dữ liệu từ listB -->
                                                <td>
                                                    <form action="requestmanagement" method="post">
                                                        <input type="hidden" name="requirementId" value="${r.requirementId}">
                                                        <select name="status" class="form-control" onchange="confirmUpdate(this.form)">
                                                            <option value="Pending" ${r.status == 'Pending' ? 'selected' : ''}>Pending</option>
                                                            <option value="Đợi lấy hàng" ${r.status == 'Đợi lấy hàng' ? 'selected' : ''}>Đợi lấy hàng</option>
                                                            <option value="Approved" ${r.status == 'Approved' ? 'selected' : ''}>Approved</option>
                                                            <option value="Rejected" ${r.status == 'Rejected' ? 'selected' : ''}>Rejected</option>
                                                        </select>
                                                    </form>
                                                </td>
                                                <td>${r.description}</td>
                                                <td>${r.isPay}</td>

                                                <!-- Dữ liệu từ listA -->
                                                <td>${r.form.faultType}</td>
                                                <td class="${r.form.verified eq 'yes' ? 'verified-yes' : (r.form.verified eq 'no' ? 'verified-no' : 'verified-empty')}">
                                                    <c:choose>
                                                        <c:when test="${r.form.verified eq 'yes'}">Đã xác nhận</c:when>
                                                        <c:when test="${r.form.verified eq 'no'}">Đã từ chối</c:when>
                                                        <c:otherwise>Chưa xác nhận</c:otherwise>
                                                    </c:choose>
                                                </td>

                                                <td class="${r.form.technicianVerify eq 'yes' ? 'verified-yes' : (r.form.technicianVerify eq 'no' ? 'verified-no' : 'verified-empty')}">
                                                    <c:choose>
                                                        <c:when test="${r.form.technicianVerify eq 'yes'}">Đã xác nhận</c:when>
                                                        <c:when test="${r.form.technicianVerify eq 'no'}">Đã từ chối</c:when>
                                                        <c:otherwise>
                                                            Chưa xác nhận 
                                                            <form action="techverify" method="post">
                                                                <input type="hidden" name="email" value="${r.staff.email}">
                                                                <input type="hidden" name="formId" value="${r.form.formId}">
                                                                <input type="hidden" name="customerId" value="${r.customer.customerId}">
                                                                <input type="hidden" name="productId" value="${r.product.productId}">
                                                                <input type="hidden" name="requirementid" value="${r.requirementId}">
                                                                <input type="hidden" name="staffid" value="${r.staff.staffId}">
                                                                <button type="submit" name="action" value="confirm" 
                                                                        onclick="return confirm('Bạn có chắc chắn muốn xác nhận yêu cầu này?');">
                                                                    Xác nhận qua Email
                                                                </button>
                                                                <button type="submit" name="action" value="reject" 
                                                                        onclick="return confirm('Bạn có chắc chắn muốn từ chối yêu cầu này?');">
                                                                    Từ chối
                                                                </button>
                                                            </form>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>

                                                <td>
                                                    <c:choose>
                                                        <c:when test="${r.form.formId != 0}">
                                                            <a href="updateform?formId=${r.form.formId}" class="btn btn-warning btn-sm" title="Update Form">
                                                                Update Form
                                                            </a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="createform?productid=${r.product.productId}&requireId=${r.requirementId}" class="btn btn-info btn-sm" title="Create New Form">
                                                                Create New Form
                                                            </a>
                                                        </c:otherwise>
                                                    </c:choose>
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