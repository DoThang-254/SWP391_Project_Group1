<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                min-height: 100vh;
                background-color: #f4f4f4;
            }
            .sidebar {
                background-color: #d52b1e;
                color: white;
                width: 250px;
                padding: 20px;
            }
            .sidebar h2 {
                margin: 0;
            }
            .sidebar a {
                color: white;
                text-decoration: none;
                display: block;
                margin: 15px 0;
            }
            .sidebar a:hover {
                text-decoration: underline;
            }
            .main-content {
                flex-grow: 1;
                padding: 30px;
            }
            form {
                background: #fff;
                padding: 20px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                border-radius: 5px;
            }
            form input[type="text"], form input[type="password"], form select {
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            form input[type="submit"] {
                background-color: #d52b1e;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 4px;
                cursor: pointer;
            }
            form input[type="submit"]:hover {
                background-color: #b32418;
            }
            p a {
                color: #d52b1e;
                text-decoration: none;
            }
            p a:hover {
                text-decoration: underline;
            }
            .notifications {
                margin-top: 20px;
            }
            .notification {
                padding: 15px;
                border-radius: 4px;
                margin-bottom: 10px;
            }
            .error {
                background-color: #f8d7da;
                color: #721c24;
            }
            .success {
                background-color: #d4edda;
                color: #155724;
            }
            .info {
                background-color: #d1ecf1;
                color: #0c5460;
            }
            .warning {
                background-color: #fff3cd;
                color: #856404;
            }
        </style>
    </head>
    <body>
       
        <div class="main-content">
            <form action="login" method="POST">
                <h2>Login</h2>
                <label>Username</label>
                <input type="text" name="userName" value="${cookie.username.value}" />
                <label>Password</label>
                <input type="password" name="passWord" value="${cookie.password.value}" />
                
                <label>Remember me</label> 
                <input type="checkbox" name="rememberMe" />
                <br>
                <br>
                <c:if test="${requestScope.msg != null}">
                    <p style="color: red"> ${requestScope.msg}</p>
                </c:if>
                <input type="submit" value="Login" />
            </form>
            <p>Don't have an account? <a href="register">Register now</a></p>
            <p><a href="requestPassword">Forgot Password</a></p>
        </div>
    </body>
</html>
