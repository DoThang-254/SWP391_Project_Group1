<%-- 
    Document   : AccessDenied
    Created on : Jan 17, 2025, 2:59:41 PM
    Author     : thang
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Denied</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(135deg, #ff4b2b, #ff416c);
                color: #fff;
                text-align: center;
                padding-top: 20%;
                margin: 0;
            }
            h1 {
                font-size: 4em;
                margin: 0;
                animation: bounce 1s ease infinite;
            }
            @keyframes bounce {
                0%, 20%, 50%, 80%, 100% {
                    transform: translateY(0);
                }
                40% {
                    transform: translateY(-20px);
                }
                60% {
                    transform: translateY(-10px);
                }
            }
            a {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #333;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }
            a:hover {
                background-color: #555;
            }
        </style>
    </head>
    <body>
        <h1>Access Denied</h1>
        <a href="login">Return to Login</a>
    </body>
</html>
