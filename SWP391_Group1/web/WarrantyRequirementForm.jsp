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
                                    Register Warranty 

                                </header>
                                <div class="form-container">
                                    <h2>Warranty Requirement Form</h2>
                                    <form action="warrantyrequest" method="post">
                                        <div class="form-group">
                                            <label for="productId">Product ID:</label>
                                            <input type="text" id="productId" name="productId" value="${requestScope.productid}" readonly>
                                        </div>

                                        <div class="form-group">
                                            <label for="productName">Product Name:</label>
                                            <input type="text" id="productName" name="productName" value="${requestScope.product.productName}" readonly>
                                        </div>

                                        <div class="form-group">
                                            <label for="productBrand">Brand :</label>
                                            <input type="text" id="productBrand" name="productBrand" value="${requestScope.product.brand}" readonly>
                                        </div>

                                        <input type="hidden" id="status" name="status" value="Pending">
                                        <input type="hidden" id="ispay" name="ispay" value="${requestScope.ispay}">

                                        <div class="form-group">
                                            <label for="description">Description:</label>
                                            <textarea id="description" name="description"></textarea>
                                        </div>

                                        <div class="form-group text-danger">
                                            ${requestScope.errorMessage}
                                            ${requestScope.successMessage}
                                        </div>

                                        <div class="button-group">
                                            <button type="submit">Submit</button>
                                            <a href="searchinformation">Back</a>
                                        </div>
                                    </form>
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
    .form-container {
        max-width: 500px;
        margin: auto;
        background: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .form-container h2 {
        text-align: center;
        margin-bottom: 20px;
        font-weight: bold;
    }

    .form-group {
        margin-bottom: 15px;
    }

    .form-group label {
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }

    .form-group input,
    .form-group textarea {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .form-group textarea {
        resize: vertical;
    }

    .button-group {
        text-align: center;
        margin-top: 15px;
    }

    .button-group button {
        padding: 10px 15px;
        border: none;
        border-radius: 5px;
        background-color: red;
        color: white;
        font-size: 16px;
        cursor: pointer;
        transition: 0.3s;
    }

    .button-group button:hover {
        background-color: #0056b3;
    }

    .button-group a {
        margin-left: 10px;
        text-decoration: none;
        color: #007bff;
    }


</style>