<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <div class="row site-padding catalogue">
            <div class="col-sm-2 navigation" style="position: fixed; overflow: auto; margin-top: 25vh">
                <div class="row filter content">
                    <div class="col">
                        <img width="100px" src="/assets/images/product.png">
                    </div>
                </div>
            </div>
            <div id="productDescription">
                <div class="col content" style="margin-top: 25vh; margin-left: 20vw; width: 60vw;">
                    <div class="form-group col-6">
                        <input readonly type="text" class="form-control" id="name"
                               value="${product.name}">
                        <small class="form-text text-muted">Product name</small>
                    </div>
                    <div class="form-group col-6">
                        <input readonly type="text" class="form-control" id="desc"
                               value="${product.description}">
                        <small class="form-text text-muted">Product description</small>
                    </div>
                    <div class="form-group col-6">
                        <input readonly type="text" class="form-control" id="title"
                               value="${product.category.title}">
                        <small class="form-text text-muted">Category</small>
                    </div>
                    <div class="form-group col-6">
                        <input readonly type="text" class="form-control" id="volume"
                               value="${product.volume}">
                        <small class="form-text text-muted">Volume</small>
                    </div>
                    <div class="form-group col-6">
                        <input readonly type="text" class="form-control" id="wight"
                               value="${product.weight}">
                        <small class="form-text text-muted">Weight</small>
                    </div>
                    <div class="form-group col-6">
                        <input readonly type="text" class="form-control" id="players"
                               value="${product.minPlayerAmount} - ${product.maxPlayerAmount}">

                        <small class="form-text text-muted">Players amount</small>
                    </div>
                    <div class="form-group col-6">
                        <input readonly type="text" class="form-control" id="price"
                               value="${product.price}">
                        <small class="form-text text-muted">Price</small>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/cart.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>
<script src="/assets/js/cartOrderEvents.js"></script>
</body>
</html>