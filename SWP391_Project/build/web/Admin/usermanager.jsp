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
                            <header class="panel-heading">
                                User Manager
                            </header>
                            <!-- <div class="box-header"> -->
                            <!-- <h3 class="box-title">Responsive Hover Table</h3> -->

                            <!-- </div> -->
                            <div class="panel-body table-responsive">
                                <div class="sliderList">
                                    <table class="table table-hover " id="tablepro">
                                        <thead>
                                            <tr style="cursor: pointer; font-size: 15px;  text-align: center;">
                                                <th style="width: 55px;">ID</th>
                                                <th>Username</th>
                                                <th>FullName</th>
                                                <th>Email</th>
                                                <th>Phone</th>
                                                <th>Role</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% int no=1;%>
                                        <c:forEach var="s" items="${User}">
                                            <tr>
                                                <td ><%=no++%></td>
                                                <td width="15%">${s.getFullName()}</td>
                                                <td style="width: 340px;">${s.getUsername()}</td>
                                                <td>${s.getEmail()}</td>
                                                <td>${s.getPhone()}</td>
                                                <td>${s.getPosition()}</td>
                                                <th><a class="btn btn-primary"><i class="fa fa-refresh"></i></a></th>
                                            </tr>
                                            <!-- Show detail modal -->
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
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
