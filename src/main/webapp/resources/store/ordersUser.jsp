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

<h3>Cur Order</h3>
<c:if  test="${!empty order}">

Total price is: <p id="cartPrice">${order.totalPrice}</p>
Order status is : <p> ${order.orderStatus} </p>
<table class="tg">
    <tr>
        <th width="80">Product id</th>
        <th width="80">Product Name</th>
        <th width="80">Product Amount</th>
        <th width="80">Product Total Price</th>
    </tr>

    <c:forEach items="${order.orderProducts}" var="c">
        <tr>
            <td class="productid" >${c.product.id}</td>
            <td class="productname">${c.product.name}</td>
            <td class="amount">${c.amount}</td>
            <td class="productprice">${c.price}</td>
        </tr>
    </c:forEach>

</table>
</c:if>

<h3>Orders Story</h3>
<c:if  test="${!empty orders}">

<table class="tg">
    <tr>
        <th width="80">Order id</th>
        <th width="80">Total price</th>
        <th width="80">Date</th>
        <th width="80">Status</th>
        <th width="80">Shipping type</th>
        <th width="80">Payment status</th>
    </tr>

    <c:forEach items="${orders}" var="o">
        <tr>
            <td class="id" >${o.id}</td>
            <td class="cost">${o.totalPrice}</td>
            <td class="date">${o.date}</td>
            <td class="status">${o.orderStatus}</td>
            <td class="shipping">${o.shippingType}</td>
            <td class="pay">${o.paymentStatus}</td>
        </tr>
    </c:forEach>

</table>
</c:if>
</html>
