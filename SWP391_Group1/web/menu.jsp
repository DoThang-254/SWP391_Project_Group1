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
                    <c:if test="${sessionScope.Customer != null or not empty param.customerId}">

                        <div class="pull-left image">
                            <img src="img/26115.jpg" class="img-circle" alt="User Image" />
                        </div>
                    </c:if>

                    <div class="pull-left info">
                        <c:if test="${sessionScope.Customer != null or not empty param.customerId}">
                            <p>Hello, 
                                <c:choose>
                                    <c:when test="${sessionScope.Customer != null}">
                                        ${sessionScope.Customer.username}
                                    </c:when>
                                    <c:when test="${not empty requestScope.customerUsername}">
                                        ${requestScope.customerUsername}
                                    </c:when>
                                    <c:otherwise>
                                        Guest
                                    </c:otherwise>
                                </c:choose>
                            </p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </c:if>
                    </div>

                </div>
                
                <ul class="sidebar-menu">

                    <li>
                        <a href="home">
                            <i class="fa fa-dashboard"></i> <span>Home page</span>
                        </a>
                    </li>

                    <li>
                        <a href="blog">
                            <i class="fa fa-globe"></i> <span>Blog</span>
                        </a>
                    </li>
                    <c:if test="${sessionScope.Customer != null}">
                        <li>
                            <a href="searchinformation">
                                <i class="fa fa-dashboard"></i> 
                                <span>View Detail Products & Warranty Form</span>
                            </a>
                        </li>
                        <li>
                            <a href="historyrequest">
                                <i class="fa fa-globe"></i> <span>History Request</span>
                            </a>
                        </li>
                        <li>
                            <a href="customerrequest">
                                <i class="fa fa-globe"></i> <span>Purchase Request</span>
                            </a>
                        </li>

                        <li>
                            <a href="customerprocess">
                                <i class="fa fa-globe"></i> <span>View Warranty Process</span>
                            </a>
                        </li>
                    </c:if>


                </ul>
            </section>

        </aside>

        <!-- Right side column. Contains the navbar and content of the page -->

        <!-- /.sidebar -->
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                let currentUrl = window.location.pathname;
                let activeUrl = localStorage.getItem("activeMenu") || "home"; // Mặc định Home nếu chưa có lưu

                let menuItems = document.querySelectorAll(".sidebar-menu li");

                // Xóa tất cả class "active"
                menuItems.forEach(item => item.classList.remove("active"));

                // Đánh dấu menu được chọn
                let isActiveSet = false; // Đảm bảo chỉ có 1 mục active
                menuItems.forEach(item => {
                    let link = item.querySelector("a");
                    if (link && (link.getAttribute("href") === activeUrl || (activeUrl === "home" && link.getAttribute("href") === "home")) && !isActiveSet) {
                        item.classList.add("active");
                        isActiveSet = true; // Đánh dấu đã đặt "active" để tránh trùng lặp
                    }

                    // Gán sự kiện click để lưu vào localStorage
                    link.addEventListener("click", function () {
                        localStorage.setItem("activeMenu", link.getAttribute("href"));
                    });
                });
            });
        </script>





    </body>
</html>
