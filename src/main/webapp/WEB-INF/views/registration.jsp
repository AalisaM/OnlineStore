<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

            <h3> Sign Up New User </h3>
            <c:url var="addAction" value="/registration/add" ></c:url>
            <c:if test="${! empty message}">
                ${message.errors}
            </c:if>

                <form:form action="${addAction}" commandName="user">
                    <div class="form-group row">
                        <label for="fullName" class="col-sm-3 col-form-label">Full Name</label>
                        <div class="col">
                            <input type="text" class="form-control" id="fullName" name="fullName"
                                   value="${user.fullName}" placeholder="fullName" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="email" class="col-sm-3 col-form-label">E-mail</label>
                        <div class="col">
                            <input type="text" class="form-control" id="email" name="email"
                                   value="${user.email}" placeholder="email" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="birth" class="col-sm-3 col-form-label">Birth</label>
                        <div class="col">
                            <input type="date" class="form-control" id="birth" name="birth"
                                   value="${user.birth}" placeholder="birth" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="password" class="col-sm-3 col-form-label">Password</label>
                        <div class="col">
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="password" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="passConfirm" class="col-sm-3 col-form-label">Confirm password</label>
                        <div class="col">
                            <input type="password" class="form-control" id="passConfirm" name="passConfirm"
                                   placeholder="passConfirm">
                        </div>
                    </div>
                    <button class="btn-primary btn btn-lg orderbutton" type="submit">Sign Up</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
