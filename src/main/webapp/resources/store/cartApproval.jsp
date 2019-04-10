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

    <p style="margin-left: 25vw"><h3>Please approve next order's data</h3></p>
    <p id="orderID" hidden>${orderdto.id}</p>
    <c:if test="${! empty orderdto}">

        <p> User address:   <span id="address"> ${orderdto.address}</span></p>
        <p> Shipping Type:  <p id="shippingid" hidden>${orderdto.shippingType.id}</p> <span id="shipping">${orderdto.shippingType.type}</span></p>
        <p> Payment type:   <p id="paymentid" hidden>${orderdto.paymentType.id}</p><span id="payment">${orderdto.paymentType.type}</span></p>
        <p> Payment status: <p id="statusid" hidden>${orderdto.paymentStatus.id}</p><span id="status">${orderdto.paymentStatus.status}</span></p>
        <p> Order status:  <p id="orderstatusid" hidden>${orderdto.orderStatus.id}</p> <span id="orderstatus">${orderdto.orderStatus.status}</span></p>
        <Br>
        <p> Total amount of products is: <span id="lblCartCount">${orderdto.amount}</span></p>
        <p> Total price is: <span id="cartPrice">${orderdto.totalPrice}</span></p>

        <table class="tg">
            <tr>
                <th width="80">Product id</th>
                <th width="80">Product Name</th>
                <th width="80">Product Amount</th>
                <th width="80">Product Total Price</th>
            </tr>

            <c:forEach items="${orderdto.orderProducts}" var="c">
                <tr class="productItem">
                    <td class="productid">${c.productid}</td>
                    <td class="productname">${c.productName}</td>
                    <td class="amount">${c.amount}</td>
                    <td class="productprice">${c.price}</td>
                </tr>
            </c:forEach>

        </table>
    </c:if>
    <button id="cancel">Cancel Order</button>
    <c:if test="${orderdto.paymentType.type == 'cash'}">
        <button id="approve">Approve Order</button>
    </c:if>
    <c:if test="${orderdto.paymentType.type != 'cash'}">
        <button id="pay">Pay for order</button>
    </c:if>
</body>

</html>
