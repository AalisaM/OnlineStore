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
            <sec:authorize access="isAuthenticated()">
                <jsp:include page="orderUserDataTemplate.jsp"/>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <div style="color:red; width: 100%">Please login if you want to make order.</div>
            </sec:authorize>
            <div class="col content" id="currentCart" style=" ${empty cartExtendedDTO.curCart ? "" :"margin-left: 28vw; width: 60vw;"}">
                <jsp:include page="cartTemplate.jsp"/>
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