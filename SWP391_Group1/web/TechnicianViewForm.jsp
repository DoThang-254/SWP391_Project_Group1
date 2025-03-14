<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Tin Phi?u B?o Hành</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: linear-gradient(135deg, #f5e8e9 0%, #fce4e6 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
            padding: 20px;
        }

        .container {
            width: 100%;
            max-width: 600px;
            background: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(114, 28, 36, 0.1);
            border: none;
            position: relative;
            overflow: hidden;
        }

        .container::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 5px;
            background: linear-gradient(to right, #dc3545, #ff7581);
        }

        h1 {
            color: #dc3545;
            font-size: 28px;
            margin: 0 0 25px;
            padding-bottom: 10px;
            border-bottom: 2px solid #ffe5e7;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .info {
            padding: 0;
        }

        .info p {
            margin: 15px 0;
            padding: 10px;
            background: #fafafa;
            border-radius: 8px;
            transition: all 0.3s ease;
        }

        .info p:hover {
            background: #ffe5e7;
            transform: translateX(5px);
        }

        .info label {
            font-weight: 600;
            color: #721c24;
            min-width: 120px;
            display: inline-block;
        }

        .info img {
            max-width: 100%;
            height: auto;
            border-radius: 10px;
            border: 3px solid #ffe5e7;
            margin: 15px 0;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
            transition: transform 0.3s ease;
        }

        .info img:hover {
            transform: scale(1.02);
        }

        .button {
            background: linear-gradient(45deg, #dc3545, #ff7581);
            color: white;
            border: none;
            padding: 12px 30px;
            cursor: pointer;
            border-radius: 25px;
            font-size: 16px;
            font-weight: 500;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-top: 20px;
            transition: all 0.3s ease;
            box-shadow: 0 2px 8px rgba(220, 53, 69, 0.3);
        }

        .button:hover {
            background: linear-gradient(45deg, #a71d2a, #dc3545);
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(220, 53, 69, 0.4);
        }

        .button:active {
            transform: translateY(0);
            box-shadow: 0 2px 8px rgba(220, 53, 69, 0.3);
        }

        @media (max-width: 480px) {
            .container {
                padding: 20px;
            }
            
            h1 {
                font-size: 24px;
            }
            
            .info label {
                display: block;
                margin-bottom: 5px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Warranty Form Information</h1>
        <div class="info">
            <p><label>Form Id: </label> ${wf.formId}</p>
            <p><label>Product Id: </label> ${wf.product.productId}</p>
            <p><label>Start Date: </label> ${wf.startDate}</p>
            <p><label>End Date: </label> ${wf.endDate}</p>
            <p><label>Form Status: </label> ${wf.status}</p>
            <p><label>Fault Type: </label> ${wf.faultType}</p>
            <p><label>Img: </label></p>
            <img src="${wf.imgUrl}" alt="img">
        </div>
        <button class="button" onclick="window.history.back()">Back</button>
    </div>
</body>
</html>