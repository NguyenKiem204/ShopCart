<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Access Denied</title>
    <link rel="shortcut icon" href="./assets/images/logo/logo-tron.png" type="image/x-icon">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #000000; /* Nền đen */
            color: #ffffff; /* Màu chữ trắng */
            font-family: 'Arial', sans-serif;
        }

        .container {
            text-align: center;
            padding: 50px;
            background-color: #1c1c1e; /* Màu nền container xám đen */
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.6); /* Đổ bóng đậm hơn */
            border-radius: 12px; /* Bo góc mềm mại hơn */
        }

        h1 {
            font-size: 4rem; /* Kích thước lớn hơn */
            color: #ff4c4c; /* Màu đỏ nhấn mạnh */
            margin-bottom: 10px;
            letter-spacing: 2px; /* Khoảng cách giữa các chữ */
        }

        h2 {
            font-size: 2rem;
            color: #ffffff;
            margin-bottom: 20px;
        }

        p {
            font-size: 1.25rem;
            color: #d3d3d3;
            margin-bottom: 30px;
        }

        .btn-back {
            margin-top: 20px;
        }

        .btn-back a {
            padding: 12px 30px;
            text-decoration: none;
            color: #ffffff; /* Màu chữ trắng */
            background-color: #333333; /* Màu nền của nút */
            border-radius: 5px;
            transition: background-color 0.3s ease; /* Hiệu ứng hover mượt mà */
        }

        .btn-back a:hover {
            background-color: #444444; /* Màu nền khi hover */
        }
    </style>
</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-12">
                
                <h1>403</h1>
                <h2>Access Denied</h2>
                <p>Sorry, you don't have permission to access this page.</p>
                <div class="btn-back">
                    <a href="home" class="btn">Go Back to Home</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
