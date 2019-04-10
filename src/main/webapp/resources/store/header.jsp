<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
  Created by IntelliJ IDEA.
  User: Алиса
  Date: 15.03.2019
  Time: 5:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    .header{background:rgb(0, 178, 255);color:#fff;}
    #lblCartCount {
        font-size: 12px;
        background: #ff0000;
        color: #fff;
        padding: 0 5px;
        vertical-align: top;
        margin-left: -10px;
    }
    .badge {
        padding-left: 9px;
        padding-right: 9px;
        -webkit-border-radius: 9px;
        -moz-border-radius: 9px;
        border-radius: 9px;
    }

    .label-warning[href],
    .badge-warning[href] {
        background-color: #c67605;
    }
</style>
<body>

<sec:authorize access="isAuthenticated()">
    <p id="loggedUserName">Ваш логин: <sec:authentication property="principal.username" /></p>
</sec:authorize>
<div class="menu-bar">
    <w:highlightCurrentPage styleClass='highlight'>
        <c:url value="/" var="homeURL"/>
        <A href='${homeURL}'>Products</a>
        <a href="<c:url value="/cart" />" > CART</a>
        <c:url value="/users" var="showRsvpURL"/>
        <A href='${showRsvpURL}'>Users</a>
        <c:url value="/admin/categories" var="logoffURL"/>
        <A href='${logoffURL}'>Categories</a>
        <c:url value="/admin/shipping" var="ship"/>
        <A href='${ship}'>Shipping Type</a>
        <sec:authorize access="isAnonymous()">
            <a href="<c:url value="/login" />" > Login</a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a href="<c:url value="/logout" />" > Logout</a>
        </sec:authorize>
        <%--<a href="<c:url value="/logout" />">Logout</a>--%>
    </w:highlightCurrentPage>
</div>
</body>
</html>
