<%-- 
    Document   : newjsp
    Created on : Jan 12, 2025, 12:57:47 PM
    Author     : tuan7
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@page import="Model.User" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Manager</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">


        <style type="text/css">
            .left-aside{
                height: 1550px;
            }
        </style>
    </head>
    <body class="skin-black">
        <c:if test="${param['index']==null }">   
            <c:set var = "index" scope = "page" value = "1"/>
        </c:if>
        <c:if test="${param['index']!=null}">
            <c:set var = "index" scope = "page" value = "${param['index']}"/>
        </c:if>
        <!-- header logo: style can be found in header.less -->


        <div class="wrapper row-offcanvas row-offcanvas-left" style="height: 100%;">
            <!-- Left side column. contains the logo and sidebar -->


            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="panel">
                                <header class="panel-heading" style="display: flex; justify-content: center; font-size: 2rem; font-weight: bold; margin-bottom: 15px">
                                    User Management
                                </header>
                                <!-- <div class="box-header"> -->
                                <!-- <h3 class="box-title">Responsive Hover Table</h3> -->

                                <div style="margin-bottom: 20px;">
                                    <button style="text-decoration: none; padding: 8px 12px; background-color: #007bff; color: white; border-radius: 5px; border-style: none; margin-right: 5px">Search</button><input type="text" placeholder="Search by username, fullname, email or phone..." style="padding: 8px 12px; width: 400px; border-radius: 5px; border: 1px solid #ddd;">
                                </div>
                                <form method="get" action="userList">
                                    <div class="panel-body table-responsive">
                                        <div class="sliderList">
                                            <table class="table table-hover" id="tablepro" style="width: 100%; border-collapse: collapse; border: 1px solid #ddd; background-color: #f9f9f9;">
                                                <thead>
                                                    <tr style="cursor: pointer; font-size: 15px; text-align: center; background-color: #007bff; color: white;">
                                                        <th style="width: 55px; padding: 8px; border: 1px solid #ddd;">ID</th>
                                                        <th style="padding: 8px; border: 1px solid #ddd;">Username</th>
                                                        <th style="width: 340px; padding: 8px; border: 1px solid #ddd;">Full name</th>
                                                        <th style="padding: 8px; border: 1px solid #ddd;">Email</th>
                                                        <th style="padding: 8px; border: 1px solid #ddd;">Phone</th>
                                                        <th style="padding: 8px; border: 1px solid #ddd;">Position</th>
                                                        <th style="padding: 8px; border: 1px solid #ddd;">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <% int no=1; %>
                                                    <c:forEach var="s" items="${userList}">
                                                        <tr style="text-align: center; background-color: #ffffff;">
                                                            <td style="padding: 8px; border: 1px solid #ddd;"><%= no++ %></td>
                                                            <td style="padding: 8px; border: 1px solid #ddd;">${s.getUserName()}</td>
                                                            <td style="padding: 8px; border: 1px solid #ddd;">${s.getFullName()}</td>
                                                            <td style="padding: 8px; border: 1px solid #ddd;">${s.getEmail()}</td>
                                                            <td style="padding: 8px; border: 1px solid #ddd;">${s.getPhone()}</td>
                                                            <td style="padding: 8px; border: 1px solid #ddd;">${s.getPosition()}</td>
                                                            <td style="padding: 8px; border: 1px solid #ddd;">
                                                                <a class="btn btn-primary">
                                                                    <button>Detail</button>
                                                                    <i class="fa fa-refresh"></i></a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>

                                            <div class="pagination">
                                                <form method="get" action="" class="w-100">
                                                    <div style="text-align: center; margin-top: 20px;">
                                                        <ul style="list-style-type: none; padding: 0; display: inline-flex;">
                                                            <li style="margin: 0 5px;">
                                                                <a href="?index=${page - 1}" style="text-decoration: none; padding: 8px 12px; background-color: #007bff; color: white; border-radius: 5px;">
                                                                    Previous
                                                                </a>
                                                            </li>
                                                            <c:forEach begin="1" end="${num}" var="i">
                                                                <li style="margin: 0 5px;">
                                                                    <a href="?index=${i}" style="text-decoration: none; padding: 8px 12px; background-color:${i == index ? '#007bff' : '#f1f1f1'}; color: ${i == index ? '#f1f1f1' : '#007bff'}; border-radius: 5px;">
                                                                        ${i}
                                                                    </a>
                                                                </li>
                                                            </c:forEach>
                                                            <li style="margin: 0 5px;">
                                                                <a href="?index=${page + 1}" style="text-decoration: none; padding: 8px 12px; background-color: #007bff; color: white; border-radius: 5px;">
                                                                    Next
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </form>
                                            </div>
                                        </div><!-- /.box -->
                                </form>
                            </div>
                        </div>
                </section><!-- /.content -->
                <div class="footer-main">
                </div>
            </aside><!-- /.right-side -->


        </div><!-- ./wrapper -->



        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#tablepro").DataTable({bFilter: false, bInfo: false, paging: false});
            });
        </script>
    </body>
</html>
