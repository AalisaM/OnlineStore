<%@ page import="com.fasterxml.jackson.databind.JsonNode" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="java.util.Base64" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Carts Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<%@include file='header.jsp'%>
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/cart.js"></script>
<script src="/assets/js/cartOrderEvents.js"></script>

<h3>Cur Cart</h3>
<c:if test="${! empty message}">
    <c:if test="${! empty message.errors}">

    <c:forEach items="${message.errors}" var="cat">
       <p>${cat}</p>
    </c:forEach>
    </c:if>
    <c:if test="${! empty message.warnings}">

    <c:forEach items="${message.warnings}" var="cat">
        <p>${cat}</p>
    </c:forEach>
    </c:if>
    <c:if test="${! empty message.confirms}">

    <c:forEach items="${message.confirms}" var="cat">
        <p>${cat}</p>
    </c:forEach>
    </c:if>
</c:if>
<div id="anon">
    <%@include file='anon.jsp'%>
</div>
<div id="notanon">
    <%@include file='notanon.jsp'%>
</div>

<c:if test="${! empty cartExtendedDTO.user}">
    <p id="userName">${cartExtendedDTO.user.fullName}</p>
    <p id="userEmail" hidden>${cartExtendedDTO.user.email}</p>
    <p id="address">${cartExtendedDTO.user.activeAddressId.address}</p>
    <p> Choose shipping type : <select id="shipping">
        <c:forEach items="${cartExtendedDTO.shippingType}" var="cat">
            <option value="${cat.id}">${cat.type}</option>
        </c:forEach>
    </select>
    </p>
    <p> Choose payment type: <select id="payment">
        <c:forEach items="${cartExtendedDTO.paymentType}" var="cat">
            <option value="${cat.id}">${cat.type}</option>
        </c:forEach>
    </select>
    </p>
</c:if>
</body>
<button id="makeOrder">Make Order</button>
</html>
