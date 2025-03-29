<%-- 
    Document   : ServiceManager
    Created on : Jan 24, 2025, 8:55:03 PM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Technician</title>
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
        <c:import url="StaffNavbar.jsp"/>

        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <c:import url="StaffMenu.jsp"/>

            <aside class="right-side">

                <!-- Main content -->
                <section class="content">

                    <div class="row">

                        <div class="col-md-8">
                            <section class="panel">
                                <header class="panel-heading">
                                    Task
                                </header>
                                <div class="panel-body table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Status</th>
                                                <th>View Product</th>
                                                <th>Invoice</th>

                                                <th>View Warranty Form</th>
                                                <th>Update Warranty Form</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="pr" items="${requestScope.list}">


                                                <tr>
                                                    <td>${pr.requirement.requirementId}</td>

                                                    <td>
                                                        <script>
                                                            function confirmUpdate(form) {
                                                                let confirmAction = confirm("Bạn có chắc muốn cập nhật trạng thái này không?");
                                                                if (confirmAction) {
                                                                    form.submit();
                                                                }
                                                            }
                                                        </script>
                                                        <form action="task" method="post">
                                                            <input type="hidden" name="processingId" value="${pr.processingId}">
                                                            <input type="hidden" name="requirementId" value="${pr.requirement.requirementId}">
                                                            <input type="hidden" name="productId" value="${pr.requirement.product.productId}">
                                                            <fmt:formatDate value="${pr.returnDate}" pattern="yyyy-MM-dd" var="formattedReturnDate" />
                                                            <input type="date" name="returndate" value="${formattedReturnDate}">

                                                            <select name="status" class="form-control" onchange="confirmUpdate(this.form)">
                                                                <option value="Under Inspection" ${pr.status == 'Under Inspection' ? 'selected' : ''}>Under Inspection</option>
                                                                <option value="Start Repair" ${pr.status == 'Start Repair' ? 'selected' : ''}>Start Repair</option>
                                                                <option value="In Repair" ${pr.status == 'In Repair' ? 'selected' : ''}>In Repair</option>
                                                                <option value="Completed" ${pr.status == 'Completed' ? 'selected' : ''}>Completed</option>
                                                            </select>
                                                        </form>
                                                    </td>


                                                    <td><a href="viewproduct?productId=${pr.requirement.product.productId}">View</a></td>


                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${pr.requirement.isPay eq 'Yes'}">
                                                                <c:choose>
                                                                    <c:when test="${pr.requirement.hasInvoice}">
                                                                        <a href="updateinvoice?requirementId=${pr.requirement.requirementId}" class="btn btn-info btn-sm" title="Tạo hóa đơn">
                                                                            Update Invoice
                                                                        </a>                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <a href="createinvoice?requirementId=${pr.requirement.requirementId}" class="btn btn-info btn-sm" title="Tạo hóa đơn">
                                                                            Create Invoice
                                                                        </a>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:otherwise>
                                                                Bảo hành miễn phí
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>

                                                    <td> 

                                                        <a href="viewform?formId=${pr.requirement.form.formId}" class="btn btn-info btn-sm" title="View Details">
                                                            <i class="fa fa-eye"></i>
                                                        </a>
                                                    </td>

                                                    <td> <a href="updateform?formId=${pr.requirement.form.formId}&productid=${pr.requirement.product.productId}&requireId=${pr.requirement.requirementId}&staffId=${pr.staff.staffId}" class="btn btn-warning btn-sm" title="Update Form">
                                                            Update Form
                                                        </a></td>

                                                </tr>
                                            </c:forEach>
                                            <c:if test="${requestScope.msg != null}">
                                            <script>
                                                    alert("invalid date");
                                            </script>
                                            </c:if>
                                        </tbody>
                                    </table>
                                    <br>
                                    
                                </div>
                            </section>


                        </div><!--end col-6 -->


                    </div>

                    <!-- row end -->
                </section><!-- /.content -->

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
