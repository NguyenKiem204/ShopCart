<%-- 
    Document   : admin
    Created on : Oct 4, 2024, 6:03:00 AM
    Author     : nkiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.User" %>
<!DOCTYPE html>
<html lang="en"> <!--begin::Head-->

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Seller Shop</title><!--begin::Primary Meta Tags-->
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
        <style>
            #myChart {
                width: 300px;
                height: 450px;
            }
        </style>
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
                <div class="sidebar-brand"> <!--begin::Brand Link--> <a href="./index.html" class="brand-link"> <!--begin::Brand Image--> <img src="./assets/images/logo/logo.png" alt="AdminLTE Logo" class="brand-image opacity-75 shadow"> <!--end::Brand Image--> <!--begin::Brand Text--> <span class="brand-text fw-light">Seller Shop</span> <!--end::Brand Text--> </a> <!--end::Brand Link--> </div> <!--end::Sidebar Brand--> <!--begin::Sidebar Wrapper-->
                <div class="sidebar-wrapper">
                    <nav class="mt-2"> <!--begin::Sidebar Menu-->
                        <ul class="nav sidebar-menu flex-column" data-lte-toggle="treeview" role="menu" data-accordion="false">
                            <li class="nav-item"> <a href="seller"  class="nav-link"> <i class="nav-icon bi bi-palette"></i>
                                    <p>Thông tin cá nhân</p>
                                </a> </li>
                            <li class="nav-item"> <a href="listproductofseller?userID=<%=account.getUserID()%>" class="nav-link"> <i class="fa-solid fa-tree"></i>
                                    <p>Quản lý sản phẩm</p>
                                </a></li>
                            <li class="nav-item"> <a href="cartchartofseller" style="color: #fff !important; background-color: rgba(255, 255, 255, 0.1)" class="nav-link"> <i class="fa-solid fa-cart-shopping"></i>
                                    <p>Quản lý đơn hàng</p>
                                </a> </li>

                        </ul> <!--end::Sidebar Menu-->
                    </nav>
                </div>
            </aside> <!--end::Sidebar--> <!--begin::App Main-->
            <main id="content">
                <canvas id="myChart"></canvas>
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
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script type="text/javascript">
            const labels = ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"];
            const data = {
                labels: labels,
                datasets: [
                    {
                        label: "Số lượng đơn",
                        data: [65, 59, 80, 81, 56, 55, 40, 60, 30, 20, 100, 80],
                        fill: false,
                        borderColor: "rgb(75, 192, 192)",
                        tension: 0.1,
                    },
                ],
            };

            const config = {
                type: "line",
                data: data,
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                },
            };

            const ctx = document.getElementById("myChart").getContext("2d");
            new Chart(ctx, config);
        </script>
        <script src="https://cdn.jsdelivr.net/npm/overlayscrollbars@2.3.0/browser/overlayscrollbars.browser.es6.min.js" integrity="sha256-H2VM7BKda+v2Z4+DRy69uknwxjyDRhszjXFhsL4gD3w=" crossorigin="anonymous"></script> <!--end::Third Party Plugin(OverlayScrollbars)--><!--begin::Required Plugin(popperjs for Bootstrap 5)-->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha256-whL0tQWoY1Ku1iskqPFvmZ+CHsvmRWx/PIoEvIeWh4I=" crossorigin="anonymous"></script> <!--end::Required Plugin(popperjs for Bootstrap 5)--><!--begin::Required Plugin(Bootstrap 5)-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha256-YMa+wAM6QkVyz999odX7lPRxkoYAan8suedu4k2Zur8=" crossorigin="anonymous"></script> <!--end::Required Plugin(Bootstrap 5)--><!--begin::Required Plugin(AdminLTE)-->
        <script src="./assets/js/adminlte.js"></script> <!--end::Required Plugin(AdminLTE)--><!--begin::OverlayScrollbars Configure-->
        <script src="./assets/js/admin2.js"></script>

    </body><!--end::Body-->

</html>
