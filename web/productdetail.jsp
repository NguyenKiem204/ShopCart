<%-- 
    Document   : homeprofile
    Created on : Oct 6, 2024, 8:23:49 AM
    Author     : nkiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="entity.User" %>
<%@ page import="entity.Product" %>
<%@ page  import="dao.CartItemDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./assets/css/reset.css" />
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous" />

        <!-- Font Awesome for icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="shortcut icon" href="./assets/images/logo/logo-tron.png" type="image/x-icon">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="./assets/css/homeprofile.css"/>

        <style>
            .text-secondary{
                color: #A1A1AA !important;
            }
            .text-second{
                color: #E4E4E7 !important;
            }
            p {
                margin-bottom: 0; /* Hoặc giá trị mà bạn mong muốn */
            }

        </style>
    </head>
    <body>
    <body class="dark">
        <!-- Header top section -->
        <div class="header-top">
            <div class="container-md">
                <div class="row align-items-center">
                    <!-- Left side: Contact info -->
                    <div class="col-md d-flex justify-content-start">
                        <p class="contact-info me-4">Welcome to Our store NgKiem</p>
                        <p class="contact-info">
                            <span><i class="fas fa-phone"></i></span> Call Us: 123 - 456 - 7890
                        </p>
                    </div>

                    <!-- Right side: User links -->
                    <div class="col-md d-flex justify-content-end">
                        <div class="user-links me-5">
                            <a href="#!">
                                <span><i class="fas fa-heart"></i></span> Wishlist
                            </a>
                        </div>
                        <div class="user-links">
                            <a href="#" class="account-link">
                                <span><c:if test="${sessionScope.account==null}">
                                        <i class="fas fa-user"></i>
                                    </c:if><c:if test="${sessionScope.account!=null}">
                                        <img class="avatar" src="${sessionScope.account.imgURL}" alt="Avatar"/>
                                    </c:if>
                                </span> 
                                <c:if test="${sessionScope.account!=null}">
                                    ${sessionScope.account.lastName}
                                </c:if>
                                <c:if test="${sessionScope.account==null}">
                                    My Account
                                </c:if>
                            </a>
                            <div class="dropdown-menu">
                                <c:if test="${sessionScope.account==null}">
                                    <a href="login">Login</a>
                                </c:if>
                                <c:if test="${sessionScope.account!=null}">
                                    <a href="infor">My Profile</a>
                                    <a href="logout">Logout</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="header-low py-3">
            <div class="container-md">
                <div class="row align-items-center justify-content-between">
                    <!-- Logo -->
                    <div class="col-auto d-flex justify-content-center align-items-center">
                        <span style="display: inline-block; font-size: 3.5rem; cursor: pointer; color: #aaa;"
                              class="bars  me-lg-5" on><i class="fa-solid fa-bars"></i></span>
                        <div class="logo">
                            <a href="home">
                                <img src="./assets/images/logo/logo.png" alt="Logo">
                                NgKiemShop
                            </a>
                        </div>
                    </div>


                    <!-- Menu -->
                    <div class="col">
                        <ul class="nav justify-content-center">
                            <li class="nav-item">
                                <a class="nav-link active" href="home">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Deal</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Product</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Shop</a>
                            </li>
                            <li class="nav-item"></li>
                            <a class="nav-link" href="#">Blog</a>
                            </li>
                        </ul>
                    </div>
                    <style>.form-control::placeholder {
                            color: #A1A1AA;

                        }</style>
                    <!-- Search -->
                    <div class="col-auto">
                        <div class="search d-flex">
                            <form action="search" method="get">
                                <div class="input-group">
                                    <input type="search" class="form-control search" value="${requestScope.input}" name="search" placeholder="Search">
                                    <button type="submit" class="btn btn-outline-light">
                                        <i class="fa-solid fa-magnifying-glass"></i>
                                    </button>
                                </div>
                            </form>
                        </div>

                    </div>
                    <%
                        User user = (User) session.getAttribute("account");
                        int number = 0;

                        if (user != null) {
                            CartItemDAO cartItemDAO = new CartItemDAO();
                            number = cartItemDAO.getCartItemCountByUserID(user.getUserID());
                        }
                    %>
                    <!-- Cart -->
                    <div class="col-auto">
                        <div class="cart" style="position: relative">
                            <a href="cart" class="btn btn-outline-light">
                                <i class="fas fa-shopping-cart"></i> Cart                           
                                <c:if test="<%=number!=0%>">
                                    <span style="position: absolute; text-align: center; top: -14px; right: -16px; width: 25px; height: 25px; display: inline-block;
                                          padding: 3px 3px 5px 3px; font-weight: 600; font-size: 13px; border: 1px solid #333; color: red; border-radius: 50%; background: #fff"><%=number%></span>
                                </c:if>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- ==================================Menu Left========================================== -->
        <%
     User account = (User) session.getAttribute("account");
     String firstName = (account != null && account.getFirstName() != null) ? account.getFirstName() : "";
     String lastName = (account != null && account.getLastName() != null) ? account.getLastName() : "";
     String imgURL = (account != null && account.getImgURL() != null) ? account.getImgURL() : "./assets/images/my-account/original.webp";
     String role = (account != null && account.getRole() != null) ? account.getRole() : "";   
         request.setAttribute("role", role);
        %>

        <div class="nav-left">
            <div  class="your-account"><a href="infor">
                    <img src="<%= imgURL%>" alt="My account">
                    <p>
                        <%= !firstName.isEmpty() && !lastName.isEmpty() 
                            ? firstName + " " + lastName 
                            : "User Profile" %>
                    </p>

                </a></div>
            <ul>

                <c:if test="${role != null && role.toLowerCase() == 'admin'}">
                    <li><a href="admin"><i class="fa-solid fa-mobile-screen"></i>Quản Lý Admin</a></li>
                    </c:if>
                    <c:if test="${role != null && role.toLowerCase() == 'user'}">
                    <li><a href="infor"><i class="fa-solid fa-mobile-screen "></i>Quản Lý User</a></li>
                    </c:if>
                    <c:if test="${role != null && role.toLowerCase() == 'seller'}">
                    <li><a href="seller"><i class="fa-solid fa-mobile-screen "></i>Quản Lý Seller</a></li>
                    </c:if>
                <li><a href="#!"><i class="fa-solid fa-laptop"></i>Đơn Hàng</a></li>
                <li><a href="#!"><i class="fa-solid fa-house-laptop"></i>Đang Giao hàng</a></li>
                <li><a href="#!"><i class="fa-solid fa-headphones"></i>Đánh Giá</a></li>
                <li><a href="#!"><i class="fa-regular fa-clock"></i>Blog</a></li>

            </ul>
        </div>
        <c:set value="${requestScope.seller}" var="seller"></c:set>
        <c:set value="${requestScope.product}" var="product"></c:set>
            <div class="container p-4 text-white">
                <div class="row">
                    <!-- Left Section -->
                    <div class="col-md-6">
                        <img alt="${product.name}" class="img-fluid"
                         src="${product.imageURL}" style="width: 560px; height: 560px; object-fit: cover;" />
                    <div class="d-flex mt-4 gap-4 align-items-center">
                        <a class="text-primary" href="#">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                        <a class="text-danger" href="#">
                            <i class="fab fa-pinterest-p"></i>
                        </a>
                        <a class="text-info" href="#">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <div class="d-flex align-items-center ms-4">
                            <i class="fas fa-heart text-danger"></i>
                            <span class="ms-2 text-secondary">Đã thích (2k)</span>
                        </div>
                    </div>
                </div>
                <!-- Right Section -->
                <div class="col-md-6 ps-3">
                    <h1 class="h4 text-danger fw-bolder">Yêu Thích</h1>
                    <h2 style="font-size: 30px; font-weight: 600;" class="h1 text-white fw-bolder"> ${product.name}</h2>
                    <div class="d-flex align-items-center mt-2">
                        <span class="text-danger fs-3">4.6</span>
                        <i class="fas fa-star text-danger ms-1"></i>
                        <span class="ms-2 text-secondary">26,3k Đánh Giá</span>
                    </div>
                    <div style="font-size: 18px" class="bg-danger text-white fw-bold py-2 px-4 mt-4 d-inline-block">FLASH SALE</div>
                    <div class="fs-2 fw-bold text-danger mt-2"> 
                        <fmt:formatNumber value="${product.currentPrice}" type="number" pattern="#,##0" var="formattedCurrentPrice" />
                        ₫${formattedCurrentPrice.replace(",", ".")}</div>
                    <div class="fs-5 text-secondary text-decoration-line-through">
                        <fmt:formatNumber value="${product.originalPrice}" type="number" pattern="#,##0" var="formattedOriginalPrice" />
                        ₫${formattedOriginalPrice.replace(",", ".")}</div>
                    <div class="d-flex align-items-center mt-4">
                        <span class="text-secondary fs-4">Chính Sách Trả Hàng</span>
                        <span class="ms-4 text-secondary fs-4">Trả hàng 15 ngày</span>
                        <span class="ms-4 text-secondary fs-4">Đổi ý miễn phí</span>
                    </div>
                    <div class="d-flex align-items-center mt-4">
                        <span class="text-secondary fs-4">Vận Chuyển</span>
                        <span class="ms-4 text-secondary fs-4">Xử lý đơn hàng bởi Shopee</span>
                        <span class="ms-4 text-secondary fs-4">Miễn phí vận chuyển</span>
                    </div>
                    <div class="d-flex align-items-center mt-4">
                        <span class="text-secondary fs-4">Vận Chuyển Từ</span>
                        <span class="ms-4 text-secondary fs-4">Nước ngoài tới</span>
                        <span class="ms-4 text-secondary fs-4">Huyện Ba Vì</span>
                    </div>
                    <div class="d-flex align-items-center mt-4">
                        <span class="text-secondary fs-4">Phí Vận Chuyển</span>
                        <span class="ms-4 text-secondary">₫0</span>
                    </div>
                    <div class="d-flex align-items-center mt-4 fs-4">
                        <span class="text-secondary fs-4 mr-2" style="margin-right: 5px">Số Lượng</span>
                        <div class="d-flex align-items-center">
                            <button id="decrement" class="btn btn-secondary fs-4"  onclick="changeQuantity(-1)">-</button>
                            <input id="quantity" class="fs-4 form-control text-center border border-secondary mx-2" style="width: 60px;" type="text" value="1" name="quantity"/>
                            <button id="increment" class="btn btn-secondary fs-4" onclick="changeQuantity(1)">+</button>
                        </div>

                    </div>
                    <div class="d-flex mt-4 gap-4">
                        <button onclick="addProductCart('${product.productID}', getQuantity(), <%= account != null %>)" class="btn btn-warning fs-3">Thêm Vào Giỏ Hàng</button>
                        <button onclick="buyProduct('${product.productID}', getQuantity(), <%= account != null %>)" class="btn btn-danger fs-3">Mua Ngay</button>
                    </div>
                </div>
            </div>
            <div class="mt-5 p-4 border-top border-secondary">
                <div class="d-flex align-items-center">
                    <img  alt="${seller.lastName}" style="object-fit: cover; border-radius: 50%; height: 47px; width: 47px;"
                         src="${seller.imgURL}" />
                    <div class="ms-4">
                        <h3 class="h3 fw-bold text-white"> ${seller.lastName}</h3>
                        <p class=" fs-5 text-secondary">Online 3 Phút Trước</p>
                    </div>
                    <div class="ms-auto">
                        <button class="btn btn-danger fs-3">Chat Ngay</button>
                        <button onclick="sellerDetail('${seller.userID}')" class="btn btn-secondary ms-2 fs-3">Xem Shop</button>
                    </div>
                </div>
                    <script type="text/javascript">
                       function  sellerDetail(sellerID){
                            window.location.href = 'sellerdetail?sellerID='+sellerID;
                        }
                    </script>
                <div class="d-flex mt-2 contact">
                    <div class="col">
                        <p class=" fs-4 text-secondary">Đánh Giá</p>
                        <p class="fs-4 fw-bold text-white">3tr</p>
                    </div>
                    <div class="col">
                        <p class="  fs-4 text-secondary">Tỉ Lệ Phản Hồi</p>
                        <p class="fs-4 fw-bold text-white">89%</p>
                    </div>
                    <div class="col">
                        <p class=" fs-4 text-secondary">Sản Phẩm</p>
                        <p class="fs-4 fw-bold text-white">147,3k</p>
                    </div>
                    <div class="col">
                        <p class=" fs-4 text-secondary">Thời Gian Phản Hồi</p>
                        <p class="fs-4 fw-bold text-white">trong vài phút</p>
                    </div>
                    <div class="col">
                        <p class=" fs-4 text-secondary">Tham Gia</p>
                        <p class="fs-4 fw-bold text-white">5 năm trước</p>
                    </div>
                    <div class="col">
                        <p class=" fs-4 text-secondary">Người Theo Dõi</p>
                        <p class="fs-4 fw-bold text-white">3,1tr</p>
                    </div>
                </div>
            </div>
            <div class="mt-2 p-4 border-top border-secondary">
                <h2 class="h2 text-white fw-bold">Mô tả sản phẩm</h2>
                <p class="text-secondary fs-4 mt-4">
                    ${product.description}
                </p>
            </div>
        </div>
        <div id="notification" style="display:none; position:fixed !important; top:50px; right:10px; background-color: green; color: white; padding: 10px; z-index: 1000; border-radius: 5px; width: 330px; position: relative;">
            <span>Sản phẩm đã được thêm vào giỏ hàng!</span>
            <button onclick="closeNotification('notification')" style="background: none; border: none; color: white; font-size: 20px; cursor: pointer; position: absolute; top: 5px; right: 10px;">&times;</button>
            <div id="notificationProgress" style="height: 5px; background: rgba(255, 255, 255, 0.7); border-radius: 2px; margin-top: 10px; width: 100%;"></div>
        </div>

        <div id="errorNotification" style="display:none; position:fixed !important ; top:50px; right:10px; background-color: red; color: white; padding: 10px; z-index: 1000; border-radius: 5px; width: 330px; position: relative;">
            <span>Thêm sản phẩm vào giỏ hàng thất bại!</span>
            <button onclick="closeNotification('errorNotification')" style="background: none; border: none; color: white; font-size: 20px; cursor: pointer; position: absolute; top: 5px; right: 10px;">&times;</button>
            <div id="errorNotificationProgress" style="height: 5px; background: rgba(255, 255, 255, 0.7); border-radius: 2px; margin-top: 10px; width: 100%;"></div>
        </div>


        <!-- ====================Footer===================== -->
        <div class="footer-main-wrap">
            <div class="footer-main">
                <div class="container">
                    <div class="row">
                        <div class="column-left">
                            <div class="footer-main__left">
                                <div class="footer-main__heading">
                                    <h4>Contact Us</h4>
                                    <p>There are many variations of passaes of orem ipsum available, but the majority have
                                        in some form, by ipsum
                                    </p>
                                </div>
                                <div class="footer-main__contact">
                                    <ul>
                                        <li class="footer-contact-item">
                                            <a href="#" class="btn-contact">
                                                <i class="fa-solid fa-phone-flip"></i>
                                            </a>
                                            <div class="contact-item__content">
                                                <p>PHONE NUMBER</p>
                                                <a href="#">
                                                    <p>+84 845 965 785</p>
                                                </a>
                                            </div>
                                        </li>
                                        <li class="footer-contact-item">
                                            <a href="#" class="btn-contact">
                                                <i class="fa-solid fa-envelope"></i>
                                            </a>
                                            <div class="contact-item__content">
                                                <p>EMAIL ADDRESS</p>
                                                <a href="#">
                                                    <p>nguyekiem@support.com</p>
                                                </a>
                                            </div>
                                        </li>
                                        <li class="footer-contact-item">
                                            <a href="#" class="btn-contact">
                                                <i class="fa-solid fa-location-dot"></i>
                                            </a>
                                            <div class="contact-item__content">
                                                <p>ADDRESS</p>
                                                <span>East 40th Street New York, NY, USA</span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="column-right">
                            <div class="footer-main__right">
                                <div class="row">
                                    <div class="column">
                                        <div class="main-right__title">
                                            <h4>Our Info</h4>
                                        </div>
                                        <ul class="main-right__list">
                                            <li><a href="#">About Us</a></li>
                                            <li><a href="#">Our Service</a></li>
                                            <li><a href="#">Our Shop</a></li>
                                            <li><a href="#">Blog</a></li>
                                            <li><a href="#">Contact</a></li>
                                        </ul>
                                    </div>
                                    <div class="column">
                                        <div class="main-right__title">
                                            <h4>Quick Link</h4>
                                        </div>
                                        <ul class="main-right__list">
                                            <li><a href="#">About Us</a></li>
                                            <li><a href="#">Our Service</a></li>
                                            <li><a href="#">Our Shop</a></li>
                                            <li><a href="#">Blog</a></li>
                                            <li><a href="#">Contact</a></li>
                                        </ul>
                                    </div>
                                    <div class="column-subscribe">
                                        <div class="main-right__title">
                                            <h4>Subscribe Us</h4>
                                        </div>
                                        <div class="main-right__input">
                                            <form action="#">
                                                <input type="text" placeholder="Your mail...">
                                                <button class="btn-submit">
                                                    <i class="fa-solid fa-angles-right"></i>
                                                </button>
                                            </form>
                                        </div>
                                        <div class="main-right__social-media">
                                            <ul class="social-media">
                                                <li>
                                                    <a href="#" class="btn-social"><i
                                                            class="fa-brands fa-facebook-f"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#" class="btn-social"><i class="fa-brands fa-twitter"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#" class="btn-social"><i
                                                            class="fa-brands fa-pinterest-p"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#" class="btn-social"><i
                                                            class="fa-brands fa-linkedin-in"></i></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="column-full">
                                        <div class="payment">
                                            <div class="payment__title">
                                                <p>We know that honesty and transparency friendly customer service</p>
                                            </div>
                                            <div class="payment__img">
                                                <img src="./assets/images/others/payment_card.png" alt="img">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer-bottom">
                <div class="container">
                    <div class="row">
                        <div class="column-left-bottom">
                            <div class="footer-copyright">
                                <p>Copyright © 2021. All rights reserved. by Business-theme</p>
                            </div>
                        </div>
                        <div class="column-right-bottom">
                            <button class="btn-scroll-top">
                                <i class="fa-solid fa-angle-up"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

        <script type="text/javascript">
                document.querySelector('.bars').addEventListener('click', function () {
                    const menu = document.querySelector('.nav-left');
                    menu.classList.toggle('active');
                });
        </script>
        <script src="./assets/js/productdetail.js"></script>
        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
    </body>
</body>
</html>
