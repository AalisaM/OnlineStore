<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Catalog</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <link rel="stylesheet" href="${contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/assets/css/local.css">
    <link rel="stylesheet" href="${contextPath}/assets/css/all.css">

</head>
<body>
<div class="container-fluid" >
    <div class="site-content">
        <jsp:include page="header.jsp"/>
        <div class="row site-padding" style="margin-top: 25vh">
            <div style="color:red; width: 100%; margin-bottom: 2vh; float: right; text-align: center"><h3 style="margin-left: 25vw">Please approve next order's data</h3></div>
            <p id="orderID" hidden>${orderdto.id}</p>
            <c:if test="${! empty orderdto}">
            <div class="col-sm-3 navigation" style="position: fixed;">
                <div class="row filter content">
                    <div class="col">
                        <div class="form-row">
                            <p id="userEmail" hidden>${orderdto.email}</p>
                            <div class="form-group col-6">
                                <input readonly type="text" class="form-control" id="address"
                                       aria-describedby="maxCostHelp" value="${orderdto.address}">
                                <small class="form-text text-muted">Your delivery address</small>
                            </div>
                            <div class="form-group col-6">
                                <p id="shippingid" hidden>${orderdto.shippingType.id}</p>
                                <input readonly type="text" class="form-control" id="shipping"
                                       aria-describedby="maxCostHelp" value="${orderdto.shippingType.type}">
                                <small class="form-text text-muted">Chosen shipping type</small>
                            </div>
                            <div class="form-group col-6">
                                <p id="paymentid" hidden>${orderdto.paymentType.id}</p>
                                <input readonly type="text" class="form-control" id="payment"
                                       aria-describedby="maxCostHelp" value="${orderdto.paymentType.type}">
                                <small class="form-text text-muted">Chosen payment type</small>
                            </div>
                            <div class="form-group col-6">
                            <p id="statusid" hidden>${orderdto.paymentStatus.id}</p>
                            <input readonly type="text" class="form-control" id="status"
                                   aria-describedby="maxCostHelp" value="${orderdto.paymentStatus.status}">
                            <small class="form-text text-muted">Payment status for current moment</small>
                        </div>
                            <div class="form-group col-6" hidden>
                                <p id="orderstatusid" hidden>${orderdto.orderStatus.id}</p>
                                <input readonly type="text" class="form-control" id="orderstatus"
                                       aria-describedby="maxCostHelp" value="${orderdto.orderStatus.status}">
                                <small class="form-text text-muted">Order status for current moment</small>
                            </div>
                            <div class="form-group col-6" hidden>
                                <input readonly type="text" class="form-control" id="lblCartCountDTO"
                                       aria-describedby="maxCostHelp" value="${orderdto.amount}">
                                <small class="form-text text-muted">Total quantity</small>
                            </div>
                            <div class="form-group col-6" hidden>
                                <input readonly type="text" class="form-control" id="cartPriceDTO"
                                       aria-describedby="maxCostHelp" value="${orderdto.totalPrice}">
                                <small class="form-text text-muted">Total price</small>
                            </div>
                            <div class="form-group col-6">
                                <button id="cancel" class="btn btn-secondary" >Cancel Order</button>
                            </div>
                            <div class="form-group col-6">
                            <c:if test="${orderdto.paymentType.type == 'cash'}">
                                    <button id="approve" class="btn btn-primary orderbutton" >Approve Order</button>
                                </c:if>
                                <c:if test="${orderdto.paymentType.type != 'cash'}">
                                    <button class="btn btn-primary orderbutton" id="pay">Pay for order</button>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </c:if>
            <div class="col content" id="currentCart" style="margin-left: 28vw; width: 60vw;">
                <table class="table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Total Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orderdto.orderProducts}" var="c">
                        <tr class="productItem">
                            <td class="productid" style="display: none">${c.productid}</td>
                            <td class="productname">${c.productName}</td>
                            <td class="amount">${c.amount}</td>
                            <td class="productprice">${c.price}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>
<script src="/assets/js/jquery.js"></script>
<script>
    window.onbeforeunload = function() {
        return "Data will be lost if you leave the page, are you sure?";
    };
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>
</body>
</html>