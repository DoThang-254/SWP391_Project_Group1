<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        
    </head>
    <body>
        <div class="main-content">
            <form action="login" method="get">
                <h2>Login</h2>
                <label for="userName">Username</label>
                <input type="text" id="userName" name="userName" value="${not empty param.userName ? param.userName : (not empty cookie.username.value ? cookie.username.value : '')}" />
                
                <label for="passWord">Password</label>
                <input type="password" id="passWord" name="passWord" value="${not empty param.password ? param.password : (not empty cookie.password.value ? cookie.password.value : '')}" />
                
                <label>
                    <input type="checkbox" name="rememberMe" /> Remember me
                </label>
                
                <c:if test="${requestScope.msg != null}">
                    <p style="color: red"> ${requestScope.msg}</p>
                </c:if>
                
                <input type="submit" value="Login" />
                
                <!-- Google Login Button -->
                <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:9999/SWP391_Group1/login&response_type=code&client_id=474945012613-ulids3c3kvqf9tmpbac7n9oh4cn68860.apps.googleusercontent.com&approval_prompt=force" class="google-login">
                    <img src="https://developers.google.com/identity/images/g-logo.png" alt="Google Logo"> Login with Google
                </a>
            </form>
            <p>Don't have an account? <a href="register">Register now</a></p>
            <p><a href="requestforget">Forgot Password</a></p>
        </div>
    </body>
</html>
<style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                background-color: #f4f4f4;
            }
            .main-content {
                width: 350px;
                background: #fff;
                padding: 30px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
                text-align: center;
            }
            h2 {
                margin-bottom: 20px;
                color: #333;
            }
            form label {
                display: block;
                text-align: left;
                font-weight: bold;
                margin-top: 10px;
            }
            form input[type="text"], form input[type="password"] {
                width: calc(100% - 24px);
                padding: 12px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 14px;
                display: block;
            }
            form input[type="submit"], .google-login {
                width: 100%;
                padding: 12px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                font-size: 16px;
                font-weight: bold;
                transition: background 0.3s;
                display: flex;
                align-items: center;
                justify-content: center;
                margin-top: 10px;
            }
            form input[type="submit"] {
                background-color: #d52b1e;
                color: white;
            }
            form input[type="submit"]:hover {
                background-color: #b32418;
            }
            .google-login {
                background-color: #db4437;
                color: white;
            }
            .google-login img {
                width: 20px;
                height: 20px;
                margin-right: 10px;
            }
            .google-login:hover {
                background-color: #c1351d;
            }
            p a {
                color: #d52b1e;
                text-decoration: none;
            }
            p a:hover {
                text-decoration: underline;
            }
        </style>