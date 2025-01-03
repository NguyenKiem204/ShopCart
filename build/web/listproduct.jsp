<%-- 
    Document   : admin
    Created on : Oct 4, 2024, 6:03:00 AM
    Author     : nkiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.User" %>

<!DOCTYPE html>
<html lang="en"> <!--begin::Head-->

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Admin Shop</title><!--begin::Primary Meta Tags-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="title" content="AdminLTE v4 | Dashboard">
        <meta name="author" content="ColorlibHQ">
        <meta name="description" content="AdminLTE is a Free Bootstrap 5 Admin Dashboard, 30 example pages using Vanilla JS.">
        <meta name="keywords" content="bootstrap 5, bootstrap, bootstrap 5 admin dashboard, bootstrap 5 dashboard, bootstrap 5 charts, bootstrap 5 calendar, bootstrap 5 datepicker, bootstrap 5 tables, bootstrap 5 datatable, vanilla js datatable, colorlibhq, colorlibhq dashboard, colorlibhq admin dashboard">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="shortcut icon" href="./assets/images/logo-tron.png" type="image/x-icon">
        <!--end::Primary Meta Tags--><!--begin::Fonts-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fontsource/source-sans-3@5.0.12/index.css" integrity="sha256-tXJfXfp6Ewt1ilPzLDtQnJV4hclT9XuaZUKyUvmyr+Q=" crossorigin="anonymous"><!--end::Fonts--><!--begin::Third Party Plugin(OverlayScrollbars)-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/overlayscrollbars@2.3.0/styles/overlayscrollbars.min.css" integrity="sha256-dSokZseQNT08wYEWiz5iLI8QPlKxG+TswNRD8k35cpg=" crossorigin="anonymous"><!--end::Third Party Plugin(OverlayScrollbars)--><!--begin::Third Party Plugin(Bootstrap Icons)-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.min.css" integrity="sha256-Qsx5lrStHZyR9REqhUF8iQt73X06c8LGIUPzpOhwRrI=" crossorigin="anonymous"><!--end::Third Party Plugin(Bootstrap Icons)--><!--begin::Required Plugin(AdminLTE)-->
        <link rel="stylesheet" href="./assets/css/adminlte.css"><!--end::Required Plugin(AdminLTE)--><!-- apexcharts -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/apexcharts@3.37.1/dist/apexcharts.css" integrity="sha256-4MX+61mt9NVvvuPjUWdUdyfZfxSB1/Rf9WtqRHgG5S0=" crossorigin="anonymous"><!-- jsvectormap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/jsvectormap@1.5.3/dist/css/jsvectormap.min.css" integrity="sha256-+uGLJmmTKOqBr+2E6KDYs/NRsHxSkONXFHUL0fy2O/4=" crossorigin="anonymous">
        <link rel="shortcut icon" href="./assets/images/logo/logo-tron.png" type="image/x-icon">
        <link rel="stylesheet" href="./assets/css/admin2.css"/>


    </head> <!--end::Head--> <!--begin::Body-->
    <%
           User account = (User) session.getAttribute("account");
           String firstName = (account != null && account.getFirstName() != null) ? account.getFirstName() : "";
           String lastName = (account != null && account.getLastName() != null) ? account.getLastName() : "";
           String imgURL = (account != null && account.getImgURL() != null) ? account.getImgURL() : "./assets/images/my-account/original.webp";
           String role = (account != null && account.getRole() != null) ? account.getRole() : "";   
               request.setAttribute("role", role);
    %>
    <body class="layout-fixed sidebar-expand-lg bg-body-tertiary"> <!--begin::App Wrapper-->
        <div class="app-wrapper"> <!--begin::Header-->
            <nav class="app-header navbar navbar-expand bg-body"> <!--begin::Container-->
                <div class="container-fluid"> <!--begin::Start Navbar Links-->
                    <ul class="navbar-nav">
                        <li class="nav-item"> <a class="nav-link" data-lte-toggle="sidebar" href="#" role="button"> <i class="bi bi-list"></i> </a> </li>
                        <li class="nav-item d-none d-md-block"> <a href="home" class="nav-link">Home</a> </li>
                        <li class="nav-item d-none d-md-block"> <a href="#" class="nav-link">Contact</a> </li>
                    </ul> <!--end::Start Navbar Links--> <!--begin::End Navbar Links-->
                    <ul class="navbar-nav ms-auto"> <!--begin::Navbar Search-->
                        <li class="nav-item"> <a class="nav-link" data-widget="navbar-search" href="#" role="button"> <i class="bi bi-search"></i> </a> </li> <!--end::Navbar Search--> 
                        <li class="nav-item dropdown user-menu"> <a href="admin" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"> <img src="<%=imgURL%>" class="user-image rounded-circle shadow" alt="User Image"> <span class="d-none d-md-inline">NguyenKiem</span> </a>
                            <ul class="dropdown-menu dropdown-menu-lg dropdown-menu-end"> <!--begin::User Image-->
                                <li class="user-header text-bg-primary"> <img src="<%=imgURL%>" class="rounded-circle shadow" alt="User Image">
                                    <p>
                                        NguyenKiem - Web Developer
                                        <small>Member since Nov. 2024</small>
                                    </p>
                                </li> <!--end::User Image--> <!--begin::Menu Body-->
                                <li class="user-body"> <!--begin::Row-->
                                    <div class="row">
                                        <div class="col-4 text-center"> <a href="#">Followers</a> </div>
                                        <div class="col-4 text-center"> <a href="#">Sales</a> </div>
                                        <div class="col-4 text-center"> <a href="#">Friends</a> </div>
                                    </div> <!--end::Row-->
                                </li> <!--end::Menu Body--> <!--begin::Menu Footer-->
                                <li class="user-footer"> <a href="#" class="btn btn-default btn-flat">Profile</a> <a href="#" class="btn btn-default btn-flat float-end">Sign out</a> </li> <!--end::Menu Footer-->
                            </ul>
                        </li> <!--end::User Menu Dropdown-->
                    </ul> <!--end::End Navbar Links-->
                </div> <!--end::Container-->
            </nav> <!--end::Header--> <!--begin::Sidebar-->
            <aside class="app-sidebar bg-body-secondary shadow" data-bs-theme="dark"> <!--begin::Sidebar Brand-->
                <div class="sidebar-brand"> <!--begin::Brand Link--> <a href="admin" class="brand-link"> <!--begin::Brand Image--> <img src="./assets/images/logo/logo.png" alt="Admin Logo" class="brand-image opacity-75 shadow"> <!--end::Brand Image--> <!--begin::Brand Text--> <span class="brand-text fw-light">Admin Shop</span> <!--end::Brand Text--> </a> <!--end::Brand Link--> </div> <!--end::Sidebar Brand--> <!--begin::Sidebar Wrapper-->
                <div class="sidebar-wrapper">
                    <nav class="mt-2"> <!--begin::Sidebar Menu-->
                        <ul class="nav sidebar-menu flex-column" data-lte-toggle="treeview" role="menu" data-accordion="false">
                            <li class="nav-item"> <a href="admin" class="nav-link"> <i class="nav-icon bi bi-palette"></i>
                                    <p>Thông tin cá nhân</p>
                                </a> </li>
                            <li class="nav-item"> <a href="listuser" class="nav-link"> <i class="fa-solid fa-users"></i>
                                    <p>Quản lý khách hàng</p>
                                </a> </li>
                            <li class="nav-item"> <a href="listproduct" style="color: #fff !important; background-color: rgba(255, 255, 255, 0.1)" class="nav-link"> <i class="fa-solid fa-tree"></i>
                                    <p>Quản lý sản phẩm</p>
                                </a> </li>
                            <li class="nav-item"> <a href="cartchart" class="nav-link"> <i class="fa-solid fa-cart-shopping"></i>
                                    <p>Quản lý đơn hàng</p>
                                </a> </li>

                        </ul> <!--end::Sidebar Menu-->
                    </nav>
                </div>
            </aside> <!--end::Sidebar--> <!--begin::App Main-->
            <main id="content">
                <div class="container mt-2">
                    <table class="table table-striped table-hover table-bordered caption-top table-responsive-md">
                        <caption>Danh Sách Sản Phẩm</caption>
                        <tr class="table-dark">
                            <th>STT</th>
                            <th class="col-2">ProductID</th>
                            <th class="col-1">SellerID</th>
                            <th class="col-1">Name</th>
                            <th class="col">OriginalPrice</th>
                            <th class="col">CurrentPrice</th>
                            <th class="col-2">CategoryID</th>
                            <th class="col-2">ImageURL</th>
                            <th class="col-3">Edit</th>
                        </tr>
                        <c:set var="i" value="0"></c:set>
                        <c:forEach items="${requestScope.data}" var="product">
                            <tr>
                                <td>${i+1}</td>
                                <td>${product.productID}</td> 
                                <td>${product.sellerID}</td>
                                <td>${product.name}</td> 
                                <td>${product.originalPrice}</td>
                                <td>${product.originalPrice}</td>
                                <td>${product.categoryID}</td>
                                <td>${product.imageURL}</td>
                                <td class="btn">
                                    <a onclick="openProductForm(event, '${product.productID}', '${product.sellerID}', '${product.name}', '${product.currentPrice}', '${product.originalPrice}', '${product.description}', '${product.categoryID}', '${product.imageURL}')" href="#!">Update</a>&nbsp;&nbsp;&nbsp;
                                    <a href="#!" onclick="deleteProduct('${product.productID}', '${product.name}')">Delete</a>
                                </td>
                            </tr>
                            <c:set var="i" value="${i + 1}"></c:set>
                        </c:forEach>
                    </table>
                </div>

                <!-- Form Update Product (đưa ra ngoài forEach) -->
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-6">
                            <div class="updateForm">
                                <form action="updateProduct" method="post" enctype="multipart/form-data" onsubmit="return confirmUpdateProduct()">
                                    <input type="hidden" name="id" id="productID">

                                    <div class="form-group">
                                        <label for="name">Product Name</label>
                                        <input type="text" class="form-control" id="name" name="name" placeholder="Enter product name">
                                    </div>
                                    <div class="form-group">
                                        <label for="sellerID">SellerID</label>
                                        <input type="text" class="form-control" id="sellerID" name="sellerID" placeholder="Enter product of SellerID">
                                    </div>
                                    <div class="form-group">
                                        <label for="originalPrice">OriginalPrice</label>
                                        <input type="text" class="form-control" id="originalPrice" name="originalPrice" placeholder="Enter OriginalPrice">
                                    </div>
                                    <div class="form-group">
                                        <label for="price">CurrentPrice</label>
                                        <input type="text" class="form-control" id="currentPrice" name="currentPrice" placeholder="Enter CurrentPrice">
                                    </div>

                                    <div class="form-group">
                                        <label for="description">Description</label>
                                        <input type="text" class="form-control" id="description" name="description" placeholder="Enter description">
                                    </div>
                                    <div class="form-group">
                                        <label for="categoryID">Category ID</label>
                                        <input type="text" class="form-control" id="categoryID" name="categoryID" placeholder="Enter category ID">
                                    </div>
                                    <div class="form-group">
                                        <label for="imgURL">Image URL</label>
                                        <input type="file" class="form-control" id="imgURL" name="imgURL">
                                    </div>

                                    <button id="submit" type="submit" class="btn btn-primary mt-3 submit">Update Product</button>
                                </form>
                                <div id="close" onclick="closeForm()"><i class="fa-solid fa-circle-xmark"></i></div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Overlay để đóng form -->
                <div onclick="closeForm()" class="overlay"></div>
            </main>
            <footer class="app-footer"> <!--begin::To the end-->
                <div class="float-end d-none d-sm-inline">Anything you want</div> <!--end::To the end--> <!--begin::Copyright--> <strong>
                    Copyright &copy; 2014-2024&nbsp;
                    <a href="./index.html" class="text-decoration-none">NgKiemShop</a>.
                </strong>
                All rights reserved.
                <!--end::Copyright-->
            </footer> <!--end::Footer-->
        </div> <!--end::App Wrapper--> <!--begin::Script--> <!--begin::Third Party Plugin(OverlayScrollbars)-->
        <script src="https://cdn.jsdelivr.net/npm/overlayscrollbars@2.3.0/browser/overlayscrollbars.browser.es6.min.js" integrity="sha256-H2VM7BKda+v2Z4+DRy69uknwxjyDRhszjXFhsL4gD3w=" crossorigin="anonymous"></script> <!--end::Third Party Plugin(OverlayScrollbars)--><!--begin::Required Plugin(popperjs for Bootstrap 5)-->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha256-whL0tQWoY1Ku1iskqPFvmZ+CHsvmRWx/PIoEvIeWh4I=" crossorigin="anonymous"></script> <!--end::Required Plugin(popperjs for Bootstrap 5)--><!--begin::Required Plugin(Bootstrap 5)-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha256-YMa+wAM6QkVyz999odX7lPRxkoYAan8suedu4k2Zur8=" crossorigin="anonymous"></script> <!--end::Required Plugin(Bootstrap 5)--><!--begin::Required Plugin(AdminLTE)-->
        <script src="./assets/js/adminlte.js"></script> <!--end::Required Plugin(AdminLTE)--><!--begin::OverlayScrollbars Configure-->
        <script src="./assets/js/admin2.js"></script>

    </body><!--end::Body-->

</html>
