<%-- 
    Document   : 404
    Created on : Mar 10, 2025, 7:39:16 PM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Page Not Found</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            min-height: 100vh;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: hidden;
        }

        .container {
            text-align: center;
            padding: 40px;
            max-width: 700px;
            position: relative;
        }

        .error-code {
            font-size: 120px;
            font-weight: 700;
            color: #e63946;
            text-shadow: 3px 3px 0 #ffffff, 6px 6px 0 #1d3557;
            margin-bottom: 20px;
            animation: bounce 2s infinite;
        }

        h1 {
            font-size: 36px;
            color: #1d3557;
            margin-bottom: 15px;
        }

        p {
            font-size: 18px;
            color: #457b9d;
            margin-bottom: 30px;
            line-height: 1.6;
        }

        .btn-home {
            display: inline-block;
            padding: 12px 30px;
            background: linear-gradient(45deg, #e63946, #f4a261);
            color: white;
            text-decoration: none;
            border-radius: 25px;
            font-size: 16px;
            font-weight: 500;
            transition: all 0.3s ease;
            box-shadow: 0 4px 15px rgba(230, 57, 70, 0.3);
        }

        .btn-home:hover {
            transform: translateY(-3px);
            box-shadow: 0 6px 20px rgba(230, 57, 70, 0.4);
            background: linear-gradient(45deg, #f4a261, #e63946);
        }

        /* Animation for 404 text */
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

        /* Decorative elements */
        .container::before {
            content: '';
            position: absolute;
            top: -50%;
            left: -50%;
            width: 200%;
            height: 200%;
            background: radial-gradient(circle, rgba(230, 57, 70, 0.1) 0%, transparent 70%);
            z-index: -1;
            animation: pulse 6s infinite;
        }

        @keyframes pulse {
            0% {
                transform: scale(1);
            }
            50% {
                transform: scale(1.1);
            }
            100% {
                transform: scale(1);
            }
        }

        @media (max-width: 480px) {
            .error-code {
                font-size: 80px;
            }
            
            h1 {
                font-size: 28px;
            }
            
            p {
                font-size: 16px;
            }
            
            .btn-home {
                padding: 10px 25px;
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="error-code">404</div>
        <h1>Oops! Page Not Found</h1>
        <p>It seems you've wandered off the path. The page you're looking for doesn't exist or has been moved.</p>
        <a href="home" class="btn-home">Back to Home</a>
    </div>
</body>
</html>