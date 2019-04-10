<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col content" style="margin-left: 17vw; width: 70vw;">
    <h2> Waiting for payment orders</h2>
    <table class="table table-responsive" style="width: 68vw;">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Order id</th>
            <th scope="col">Address</th>
            <th scope="col">Shipping type</th>
            <th scope="col">Payment type</th>
            <th scope="col">Payment status</th>
            <th scope="col">Order status </th>
            <th scope="col">Total price </th>
            <th scope="col">Amount </th>
            <th scope="col">Product list</th>
        </tr>
        </thead>
        <tbody>
        <%--<c:forEach items="${categorizedOrders.unpaidOrders}" var="c">--%>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="col content" style="margin-top:4vw; margin-left: 17vw; width: 70vw;">
    <h2> Orders on the way</h2>
    <table class="table table-responsive" style="width: 68vw;">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Order id</th>
            <th scope="col">Address</th>
            <th scope="col">Shipping type</th>
            <th scope="col">Payment type</th>
            <th scope="col">Payment status</th>
            <th scope="col">Order status </th>
            <th scope="col">Total price </th>
            <th scope="col">Amount </th>
            <th scope="col">Product list</th>
        </tr>
        </thead>
        <tbody>
        <%--<c:forEach items="${categorizedOrders.unpaidOrders}" var="c">--%>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="col content" style="margin-top:4vw; margin-left: 17vw; width: 70vw;">
    <h2> Delivered orders</h2>
    <table class="table table-responsive" style="width: 68vw;">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Order id</th>
            <th scope="col">Address</th>
            <th scope="col">Shipping type</th>
            <th scope="col">Payment type</th>
            <th scope="col">Payment status</th>
            <th scope="col">Order status </th>
            <th scope="col">Total price </th>
            <th scope="col">Amount </th>
            <th scope="col">Product list</th>
        </tr>
        </thead>
        <tbody>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>