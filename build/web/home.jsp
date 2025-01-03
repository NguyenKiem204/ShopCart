<%-- 
    Document   : home
    Created on : Oct 6, 2024, 7:39:50 AM
    Author     : nkiem
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.User" %>
<%@ page import="entity.Product" %>
<%@ page  import="dao.CartItemDAO"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Shopping Cart</title>

        <link rel="stylesheet" href="./assets/css/reset.css" />
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous" />

        <!-- Font Awesome for icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="shortcut icon" href="./assets/images/logo/logo-tron.png" type="image/x-icon">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="./assets/css/style.css" />
    </head>

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
                        <span style="display: inline-block; font-size: 3.5rem; cursor: pointer; color: #aaa;" class="bars  me-lg-5" on><i class="fa-solid fa-bars"></i></span>
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

                    <!-- Search -->
                    <div class="col-auto">
                        <div class="search d-flex">
                            <form action="search" method="get">
                                <div class="input-group">
                                    <input type="search" class="form-control search" name="search" placeholder="Search">
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
            <div class="your-account"><a href="infor">
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
                <li><a href="list-order"><i class="fa-solid fa-laptop"></i>Đơn Hàng</a></li>
                <li><a href="#!"><i class="fa-solid fa-house-laptop"></i>Đang Giao hàng</a></li>
                <li><a href="#!"><i class="fa-solid fa-headphones"></i>Đánh Giá</a></li>
                <li><a href="#!"><i class="fa-regular fa-clock"></i>Blog</a></li>

            </ul>
        </div>


        <!-- ===================================Banner==================================== -->
        <div class="ads">
            <div class="container">
                <div id="slide">
                    <div class="item" style="background-image: url(./assets/images/banners/banner1.png)">
                        <div class="content">
                            <div class="name">Nguyen Kiem</div>
                            <div class="desc">
                                Tinh ru anh di chay pho, chua kip chay pho thi anhchay mat tieu
                            </div>
                            <button>See more</button>
                        </div>
                    </div>
                    <div class="item" style="background-image: url(./assets/images/banners/banner2.jpg)">
                        <div class="content">
                            <div class="name">Nguyen Kiem</div>
                            <div class="desc">
                                Tinh ru anh di chay pho, chua kip chay pho thi anhchay mat tieu
                            </div>
                            <button>See more</button>
                        </div>
                    </div>
                    <div class="item" style="background-image: url(./assets/images/banners/banner3.jpg)">
                        <div class="content">
                            <div class="name">Nguyen Kiem</div>
                            <div class="desc">
                                Tinh ru anh di chay pho, chua kip chay pho thi anhchay mat tieu
                            </div>
                            <button>See more</button>
                        </div>
                    </div>
                    <div class="item" style="background-image: url(./assets/images/banners/banner4.jpg)">
                        <div class="content">
                            <div class="name">Nguyen Kiem</div>
                            <div class="desc">
                                Tinh ru anh di chay pho, chua kip chay pho thi anhchay mat tieu
                            </div>
                            <button>See more</button>
                        </div>
                    </div>
                    <div class="item" style="background-image: url(./assets/images/banners/banner5.jpg)">
                        <div class="content">
                            <div class="name">Nguyen Kiem</div>
                            <div class="desc">
                                Tinh ru anh di chay pho, chua kip chay pho thi anhchay mat tieu
                            </div>
                            <button>See more</button>
                        </div>
                    </div>
                    <div class="item" style="background-image: url(./assets/images/banners/slider-banner2.jpg)">
                        <div class="content">
                            <div class="name">Nguyen Kiem</div>
                            <div class="desc">
                                Tinh ru anh di chay pho, chua kip chay pho thi anhchay mat tieu
                            </div>
                            <button>See more</button>
                        </div>
                    </div>
                </div>
                <div class="buttons">
                    <button id="prev"><i class="fa-solid fa-angle-left"></i></button>
                    <button id="next"><i class="fa-solid fa-angle-right"></i></button>
                </div>
            </div>
        </div>
        <div class="best-deal dark">
            <div class="container">
                <div class="flash-sell">
                    <h2 class="sub-title"></h2>
                    <div id="countdown"></div>
                </div>
                <div class="product row scroll">
                    <c:forEach items="${requestScope.listbestsale}" var="product">
                        <div class="item col-md-2"><a href="productdetail?productID=${product.productID}">
                                <img  src="${product.imageURL}" alt="Image Product Sale" loading="lazy">
                                <div class="content">
                                    <h3 class="name">${product.name}</h3>
                                    <div class="star"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i
                                            class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i></div>
                                    <div class="price d-flex">
                                        <p class="new-price"><fmt:formatNumber value="${product.currentPrice}" type="number" pattern="#,##0" var="formattedCurrentPrice" />
                                            ${formattedCurrentPrice.replace(",", ".")} VND</p>
                                        <span class="old-price"><fmt:formatNumber value="${product.originalPrice}" type="number" pattern="#,##0" var="formattedOriginalPrice" />
                                            ${formattedOriginalPrice.replace(",", ".")} VND</span>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>

        <div class="banner" style="will-change: transform;"><a href="#!"></a></div>
        <div class="section-content">
            <div class="container">
                <div class="section">
                    <div class="col-md-1">
                        <div class="item">
                            <a href="menfashion">
                                <img src="./assets/images/nav-items/section-conten1.webp" loading="lazy" alt="Thời trang nam">
                                <h2 class="name">Thời Trang Nam</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="femalefashion">
                                <img src="./assets/images/nav-items/section-content12.webp" loading="lazy" alt="Thời Trang Nữ">
                                <h2 class="name">Thời Trang Nữ</h2>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content2.webp" loading="lazy" alt="Điện Thoại">
                                <h2 class="name">Điện Thoại</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content3.webp" loading="lazy" alt="Máy Tính">
                                <h2 class="name">Máy Tính</h2>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content4.webp" loading="lazy" alt="Camera & Quay Phim">
                                <h2 class="name">Camera & Quay Phim</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content5.webp" loading="lazy" alt="Đồng Hồ">
                                <h2 class="name">Đồng Hồ</h2>
                            </a>
                        </div>
                    </div>

                    <div class="col-md-1">
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content6.webp" loading="lazy" alt="Dày Dép">
                                <h2 class="name">Dày Dép</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content7.webp" loading="lazy" alt="Thể Thao">
                                <h2 class="name">Thể Thao</h2>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content8.webp" loading="lazy" alt="Đồ Chơi">
                                <h2 class="name">Đồ Chơi</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content9.webp" loading="lazy" alt="Túi Xách">
                                <h2 class="name">Túi Xách</h2>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content4.webp" loading="lazy" alt="">
                                <h2 class="name">Sách</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content11.webp" loading="lazy" alt="">
                                <h2 class="name">Thiết Bị Điện Tử</h2>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content12.webp" loading="lazy" alt="Thời trang nam">
                                <h2 class="name">Đồ Gia Dụng</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content13.webp" loading="lazy" alt="">
                                <h2 class="name">Đồ Ăn Động Vật</h2>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content4.webp" loading="lazy" alt="Thời trang nam">
                                <h2 class="name">Hàng Hóa</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content15.webp" loading="lazy" alt="">
                                <h2 class="name">Thể Thao</h2>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content16.webp" loading="lazy" alt="Thời trang nam">
                                <h2 class="name">Thời Trang Trẻ Em</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content17.webp" loading="lazy" alt="">
                                <h2 class="name">Giặt Giũ & Lau Dọn</h2>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content11.webp" loading="lazy" alt="Thời trang nam">
                                <h2 class="name">Túi Ví Nữ</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content12.webp" loading="lazy" alt="">
                                <h2 class="name">Túi Ví Nam</h2>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content12.webp" loading="lazy" alt="Thời trang nam">
                                <h2 class="name">Thiết Bị Gia Dụng</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content13.webp" loading="lazy" alt="">
                                <h2 class="name">Sức Khỏe</h2>
                            </a>
                        </div>
                    </div>

                    <div class="col-md-1">
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content12.webp" loading="lazy" alt="Thời trang nam">
                                <h2 class="name">Trang Sức</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content13.webp" loading="lazy" alt="">
                                <h2 class="name">Mẹ & Bé</h2>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content12.webp" loading="lazy" alt="Thời trang nam">
                                <h2 class="name">Sữa</h2>
                            </a>
                        </div>
                        <div class="item">
                            <a href="#!">
                                <img src="./assets/images/nav-items/section-content13.webp" loading="lazy" alt="">
                                <h2 class="name">Đồ Dùng Học Tập</h2>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="new-product">
            <div class="container">
                <p class="sub-title">New Product</p>
                <ul class="row scroll">
                    <c:forEach items="${requestScope.list20}" var="product">
                        <li><a href="productdetail?productID=${product.productID}">
                                <img src="${product.imageURL}" loading="lazy" alt="Img Product">
                                <h3 class="name">${product.name}</h3>
                                <div class="star"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i
                                        class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i></div>
                                <div class="price">
                                    <p class="new-price"><fmt:formatNumber value="${product.currentPrice}" type="number" pattern="#,##0" var="formattedCurrentPrice" />
                                        ${formattedCurrentPrice.replace(",", ".")} VND</p>
                                    <span class="old-price"><fmt:formatNumber value="${product.originalPrice}" type="number" pattern="#,##0" var="formattedOriginalPrice" />
                                        ${formattedOriginalPrice.replace(",", ".")} VND</span>
                                </div>
                            </a>
                        </li>
                    </c:forEach>

                </ul>
            </div>
        </div>

        <!-- ===================LATOP============================== -->
        <div class="new-product laptop">
            <div class="container">
                <p class="sub-title">Máy Tính</p>
                <ul class="row scroll">
                    <c:forEach items="${requestScope.list20laptop}" var="product">
                        <li><a href="productdetail?productID=${product.productID}">
                                <img src="${product.imageURL}" loading="lazy" alt="Img Product">
                                <h3 class="name">${product.name}</h3>
                                <div class="star"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i
                                        class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i></div>
                                <div class="price">
                                    <p class="new-price"><fmt:formatNumber value="${product.currentPrice}" type="number" pattern="#,##0" var="formattedCurrentPrice" />
                                        ${formattedCurrentPrice.replace(",", ".")} VND</p></p>
                                    <span class="old-price"><fmt:formatNumber value="${product.originalPrice}" type="number" pattern="#,##0" var="formattedOriginalPrice" />
                                        ${formattedOriginalPrice.replace(",", ".")} VND</span>
                                </div>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
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
                                    <p>There are many variations of passaes of orem ipsum available, but the majority have in some form, by ipsum
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
                                                    <a href="#" class="btn-social"><i class="fa-brands fa-facebook-f"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#" class="btn-social"><i class="fa-brands fa-twitter"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#" class="btn-social"><i class="fa-brands fa-pinterest-p"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#" class="btn-social"><i class="fa-brands fa-linkedin-in"></i></a>
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




        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
        <script src="./assets/js/main.js"></script>
    </body>

</html>
