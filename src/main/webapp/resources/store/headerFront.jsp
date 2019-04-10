<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Алиса
  Date: 30.03.2019
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="/assets/css/all.css">
<link rel="stylesheet" href="/assets/css/local.css">
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/cart.js"></script>
<script src="/assets/js/cartOrderEvents.js"></script>
<script src="/assets/css/js/bootstrap.min.js"></script>
<div class="container-fluid">
    <div class="site-content">
        <div class="row header navbar-fixed-top fixed-top">
            <div class="col">
                <div class="row header-menu site-padding ">
                    <div>
                        <a href="/">
                            <img src="/assets/images/logo2.png" alt="logo" style="width:50px" align="left">
                            <p style="font-size: 2rem; width: 13vw; margin-top: 1.5vw">T-Store</p>
                        </a>
                    </div>
                    <div class="col">
                        <ul class="nav justify-content-end ">
                            <sec:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                            <li class="nav-item">
                                <a class="nav-link ${fn:contains(currentPath, 'admin') ? 'active' : ''}"
                                   href="/admin">Admin</a>
                            </li>
                            </sec:authorize>
                        </ul>
                        <sec:authorize access="!isAuthenticated()">
                            <li class="nav-item">
                                <a class="nav-link ${fn:contains(currentPath, 'login') ? 'active' : ''}" href="/login">Log in</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link ${fn:contains(currentPath, 'register') ? 'active' : ''}"
                                   href="/registration">Sign up</a>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <li class="nav-item">
                                <a class="nav-link ${fn:contains(currentPath, 'profile') ? 'active' : ''}"
                                   href="/account">Account</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/logout">Log out</a>
                            </li>
                        </sec:authorize>
                    </div>
                </div>
                <div class="row align-items-center header-content site-padding">
                    <div class="cart col-md-12 text-right">
                        <a href="/cart">
                            <i class="fas fa-shopping-cart">&#xf07a;</i>
                            <span id="lblCartCount" class="badge badge-primary">${! empty curCart ? curCart.totalAmount : 0}</span>
                            <p id="cartPrice"> ${curCart.totalPrice} </p>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>