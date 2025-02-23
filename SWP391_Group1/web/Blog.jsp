<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog Page</title>
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
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }
            .blog-container {
                width: 80%;
                margin: 40px auto;
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
                gap: 20px;
            }
            .blog-item {
                background: white;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                overflow: hidden;
                transition: transform 0.3s;
            }
            .blog-item:hover {
                transform: translateY(-5px);
            }
            .blog-item img {
                width: 100%;
                height: 200px;
                object-fit: cover;
                border-top-left-radius: 10px;
                border-top-right-radius: 10px;
            }
            .blog-content {
                padding: 15px;
                text-align: left;
            }
            .blog-item h2 {
                margin: 10px 0;
                font-size: 1.5em;
                color: #333;
            }
            .blog-item p {
                color: #666;
                font-size: 1em;
            }
            .read-more {
                display: inline-block;
                margin-top: 10px;
                padding: 8px 15px;
                background: red ;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                transition: background 0.3s;
            }
            .read-more:hover {
                background: #0056b3;
            }
        </style>
    </head>
    <body>


        <div class="blog-container">

            <c:forEach var="blog" items="${list}">
                <div class="blog-item">
                    <img src="img/photo23.jpg" alt="Blog Image">
                    <div class="blog-content">
                        <h2>${blog.title}</h2>
                        <p>${blog.description}</p>
                        <a href="blogDetails.jsp?id=${blog.blogId}" class="read-more">Read More</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
