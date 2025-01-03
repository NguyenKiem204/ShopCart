<%-- 
    Document   : register
    Created on : Oct 22, 2024, 12:32:41 PM
    Author     : nkiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>
            Sign Up
        </title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" />
        <style>
            body {
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                background-color: #f9f9f9;
            }

            .container {
                display: flex;
                background-color: #fff;
                padding: 50px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .form-container {
                margin-right: 50px;
            }

            .form-container h1 {
                font-size: 36px;
                margin-bottom: 20px;
            }

            .form-group {
                margin-bottom: 20px;
                position: relative;
            }

            .form-group input {
                width: 100%;
                padding: 10px 10px 10px 40px;
                border: none;
                border-bottom: 1px solid #ccc;
                font-size: 16px;
            }

            .form-group i {
                position: absolute;
                top: 10px;
                left: 10px;
                color: #ccc;
            }

            .form-group input:focus {
                outline: none;
                border-bottom: 1px solid #000;
            }

            .form-group input::placeholder {
                color: #ccc;
            }

            .form-group .checkbox-container {
                display: flex;
                align-items: center;
            }

            .form-group .checkbox-container input {
                width: auto;
                margin-right: 10px;
            }

            .form-group .checkbox-container label {
                font-size: 14px;
            }

            .form-group .checkbox-container a {
                color: #000;
                text-decoration: none;
            }

            .form-group .checkbox-container a:hover {
                text-decoration: underline;
            }

            .form-group .register-btn {
                width: 100%;
                padding: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
            }

            .form-group .register-btn:hover {
                background-color: #0056b3;
            }

            .already-member {
                text-align: center;
                margin-top: 20px;
            }

            .already-member a {
                color: #000;
                text-decoration: none;
                font-size: 14px;
            }

            .already-member a:hover {
                text-decoration: underline;
            }

            .image-container {
                display: flex;
                align-items: center;
            }

            .image-container img {
                max-width: 100%;
                border-radius: 10px;
            }
        </style>
    </head>

    <body>
        <div class="container">

            <div class="form-container">
                <h1>
                    Sign up
                </h1>
                <form action="register" method="post" onsubmit="return validateForm()">
                    <div class="form-group">
                        <i class="fas fa-user">
                        </i>
                        <input name="firstName" value="${requestScope.firstName}" placeholder="First Name" type="text" />
                    </div>
                    <div class="form-group">
                        <i class="fas fa-user">
                        </i>
                        <input name="lastName" value="${requestScope.lastName}" placeholder="Last Name" type="text" />
                    </div>
                    <div class="form-group">
                        <i class="fas fa-phone">
                        </i>
                        <input name="phone" value="${requestScope.phone}" placeholder="Phone" type="text" />
                    </div>
                    <div class="form-group">
                        <i class="fas fa-envelope">
                        </i>
                        <input name="email" value="${requestScope.email}" onfocus="hideErrorEmail()" placeholder="Your Email" type="email" />
                    </div>
                    <div class="form-group">
                        <i class="fas fa-home">
                        </i>
                        <input name="address" value="${requestScope.address}" placeholder="Address" type="text" />
                    </div>
                    <div class="form-group">
                        <i class="fas fa-lock"></i>
                        <input name="password" id="password" value="${requestScope.password}" onfocus="hideError()" placeholder="Password" type="password" />
                    </div>
                    <div class="form-group">
                        <i class="fas fa-lock"></i>
                        <input name="confirmpassword" value="${requestScope.password}" id="confirmpassword" onfocus="hideError()" placeholder="Repeat your password" type="password" />
                    </div>
                    <div class="form-group">
                        <p id="error-message" style="font-weight: 600; color: red; font-size: 15px;">${requestScope.exist_user}</p>
                        <p id="password-error" style="font-weight: 600; color: red; font-size: 15px; display: none;">Passwords do not match!</p>
                        <button type="submit" class="register-btn">Register</button>
                    </div>


                    <div class="already-member">
                        <a href="#">
                            I am already member
                        </a>
                    </div>
                </form>
            </div>
            <div class="image-container">
                <img alt="Illustration of a desk with a laptop, chair, and plants" height="400"
                     src="https://storage.googleapis.com/a1aa/image/10R9k0OIpnYZFZhmS5UkaWMcTqBZYxquUQknq09Mfm92Rn0JA.jpg"
                     width="400" />
            </div>
        </div>
    </body>
    <script type="text/javascript">
        function validateForm() {
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmpassword").value;
        const errorElement = document.getElementById("password-error");

        if (password !== confirmPassword) {
            errorElement.textContent = "Passwords do not match!";
            errorElement.style.display = "block"; // Đảm bảo thông báo lỗi hiển thị
            return false;
        }
        errorElement.style.display = "none"; // Ẩn thông báo nếu mật khẩu khớp
        return true;
    }

    function hideError() {
        document.getElementById('password-error').style.display = 'none';
    }
    function hideErrorEmail() {
        document.getElementById('error-message').style.display = 'none';
    }

    </script>

</html>
