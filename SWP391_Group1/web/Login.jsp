<%-- 
    Document   : Login
    Created on : Jan 25, 2025, 7:40:48 PM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                background: linear-gradient(135deg, #74ebd5, #ACB6E5);
            }

            .login-container {
                background: #ffffff;
                padding: 20px 30px;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                width: 300px;
                text-align: center;
            }

            .login-container h1 {
                margin-bottom: 20px;
                font-size: 24px;
                color: #333333;
            }

            .login-container input {
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ddd;
                border-radius: 5px;
                outline: none;
                transition: border-color 0.3s;
            }

            .login-container input:focus {
                border-color: #74ebd5;
            }

            .login-container button {
                width: 100%;
                padding: 10px;
                border: none;
                border-radius: 5px;
                background-color: #74ebd5;
                color: white;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s, transform 0.2s;
            }

            .login-container button:hover {
                background-color: #67d8c4;
                transform: scale(1.05);
            }

            .login-container button:active {
                background-color: #5ac7b2;
            }

            .login-container a {
                display: block;
                margin-top: 10px;
                color: #74ebd5;
                text-decoration: none;
                transition: color 0.3s;
            }

            .login-container a:hover {
                color: #67d8c4;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <h1>Login</h1>
            <form action="login" method="post">
                <input type="text" name="username" placeholder="Username" required>
                <input type="password" name="password" placeholder="Password" required>
                <button type="submit">Login</button>
            </form>
            <a href="#">Forgot Password?</a>
        </div>
    </body>
</html>
