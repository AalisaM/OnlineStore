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
            <div class="panel-body">
                <form action="/login" method="post">
                    <fieldset>
                        <legend>Please sign in</legend>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">
                                Bad cridentials
                                <br/>
                            </div>
                        </c:if>
                        <div class="form-group row">
                            <label for="username" class="col-sm-3 col-form-label">E-mail</label>
                            <input class="form-control form:input-large" placeholder="User Name"
                                   name='username' type="text" id="username">
                        </div>
                        <div class="form-group row">
                            <label for="password" class="col-sm-3 col-form-label">Password</label>
                            <input class="form-control form:input-large" placeholder="Password"
                                   name='password' type="password" value="" id="password">
                        </div>
                        <input class="btn btn-lg btn-primary orderbutton" type="submit"
                               value="Login">
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

