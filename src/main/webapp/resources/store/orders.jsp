<%@ page import="com.fasterxml.jackson.databind.JsonNode" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="java.util.Base64" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
    <head>
        <title>${categorizedOrders.userRoleId > 0 ? "Orders list" : "All users order list"}</title>
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

    <h1>${categorizedOrders.userRoleId > 0 ? "Orders list" : "All users order list"}</h1>
    Orders waiting for payment
    <c:if test="${! empty categorizedOrders.unpaidOrders}">
        <h2>Orders waiting for payment</h2>
        <table class="tg">
            <tr>
                <th width="80">Order id</th>
                <th width="80">Address</th>
                <th width="80">Shipping type</th>
                <th width="80">Payment type</th>
                <th width="60">Payment status</th>
                <th width="60">Order status </th>
                <th width="60">Total price </th>
                <th width="60">Amount </th>
                <th width="60">Product list</th>
                <th width="60"></th>
                <th width="60"></th>
                <c:if test="${categorizedOrders.userRoleId < 0}">
                    <th width="60"></th>
                </c:if>
            </tr>

            <c:forEach items="${categorizedOrders.unpaidOrders}" var="c">
                <tr>
                    <td class="orderid">${c.id}</td>
                    <td class="address">${c.address}</td>
                    <td class="shippingType">
                        <p class="shippingTypeid" hidden> ${c.shippingType.id}</p>
                        <p class="shippingTypes"> ${c.shippingType.type}</p
                    </td>
                    <td class="paymentType">
                        <p class="paymentTypeid" hidden> ${c.paymentType.id}</p>
                        <p class="paymentTypes"> ${c.paymentType.type}</p></td>
                    <td class="paymentstatus">
                        <p class="paymentstatusid" hidden> ${c.paymentStatus.id}</p>
                        <p class="paymentstatuss"> ${c.paymentStatus.status}</p>
                    </td>
                    <td class="orderstatus">
                        <p class="orderststusid" hidden>${c.orderStatus.id}</p>
                        <p class="orderststuss"> ${c.orderStatus.status}</p>
                    </td>
                    <td class="totalprice">${c.totalPrice}</td>
                    <td class="amount">${c.amount}</td>
                    <td class="">
                        <c:forEach items="${c.orderProducts}" var="op">
                            <p>${op.productName}   X   ${op.amount}  =   ${op.price}</p>
                        </c:forEach>
                    </td>
                    <td><button class="showOrder">Show Details</button></td>
                    <c:if test="${categorizedOrders.userRoleId > 0}">
                        <td><button class="Pay">Pay</button></td>
                    </c:if>
                    <c:if test="${categorizedOrders.userRoleId < 0}">
                        <td>
                            <select class="orderStatusSelect">
                                <c:forEach items="${nextOrderStatus[c.orderStatus]}" var="cat">
                                    <option value="${cat.id}" ${cat.status == c.orderStatus.status ? 'selected' : ''}>${cat.status}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${! empty categorizedOrders.paidOrders}">
        <h2>Orders still on the way</h2>
        <table class="tg">
            <tr>
                <th width="80">Order id</th>
                <th width="80">Address</th>
                <th width="80">Shipping type</th>
                <th width="80">Payment type</th>
                <th width="60">Payment status</th>
                <th width="60">Order status </th>
                <th width="60">Total price </th>
                <th width="60">Amount </th>
                <th width="60">Product list</th>
                <th width="60"></th>
                <c:if test="${categorizedOrders.userRoleId < 0}">
                    <th width="60"></th>
                    <th width="60"></th>
                </c:if>
            </tr>

            <c:forEach items="${categorizedOrders.paidOrders}" var="c">
                <tr>
                    <td class="orderid">${c.id}</td>
                    <td class="address">${c.address}</td>
                    <td class="shippingType">
                        <p class="shippingTypeid" hidden> ${c.shippingType.id}</p>
                        <p class="shippingTypes"> ${c.shippingType.type}</p
                    </td>
                    <td class="paymentType">
                        <p class="paymentTypeid" hidden> ${c.paymentType.id}</p>
                        <p class="paymentTypes"> ${c.paymentType.type}</p></td>
                    <td class="paymentstatus">
                        <p class="paymentstatusid" hidden> ${c.paymentStatus.id}</p>
                        <p class="paymentstatuss"> ${c.paymentStatus.status}</p>
                    </td>
                    <td class="orderstatus">
                        <p class="orderststusid" hidden>${c.orderStatus.id}</p>
                        <p class="orderststuss"> ${c.orderStatus.status}</p>
                    </td>
                    <td class="totalprice">${c.totalPrice}</td>
                    <td class="amount">${c.amount}</td>
                    <td class="">
                        <c:forEach items="${c.orderProducts}" var="op">
                            <p>${op.productName}   X   ${op.amount}  =   ${op.price}</p>
                        </c:forEach>
                    </td>
                    <td><button class="showOrder">Show Details</button></td>
                    <c:if test="${categorizedOrders.userRoleId < 0}">
                        <td>
                                <select class="orderStatusSelect">
                                    <c:forEach items="${nextOrderStatus[c.orderStatus]}" var="cat">
                                        <option value="${cat}" ${cat.status == c.orderStatus.status ? 'selected' : ''}>${cat.status}</option>
                                    </c:forEach>
                                </select>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${! empty categorizedOrders.processedOrders}">
        <h2>Order History</h2>
        <table class="tg">
            <tr>
                <th width="80">Order id</th>
                <th width="80">Address</th>
                <th width="80">Shipping type</th>
                <th width="80">Payment type</th>
                <th width="60">Payment status</th>
                <th width="60">Order status </th>
                <th width="60">Total price </th>
                <th width="60">Amount </th>
                <th width="60">Product list</th>
                <th width="60"></th>
            </tr>

            <c:forEach items="${categorizedOrders.processedOrders}" var="c">
                <tr>
                    <td class="">${c.id}</td>
                    <td class="">${c.address}</td>
                    <td class="">${c.shippingType.type}</td>
                    <td class="">${c.paymentType.type}</td>
                    <td class="">${c.paymentStatus.status}</td>
                    <td class="">${c.orderStatus.status}</td>
                    <td class="">${c.totalPrice}</td>
                    <td class="">${c.amount}</td>
                    <td class="">
                        <c:forEach items="${c.orderProducts}" var="op">
                            <p>${op.productName}   X   ${op.amount}  =   ${op.price}</p>
                        </c:forEach>
                    </td>
                    <td><button class="showOrder">Show Details</button></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>

</html>
