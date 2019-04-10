<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="currentPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<div class="row header navbar-fixed-top fixed-top">
    <div class="col" style="max-height: 1.5vh">
        <div class="row header-menu site-padding ">
            <div>
                <a href="/"><img src="/assets/images/logo2.png" alt="logo" style="width:50px" align="left">
                    <p style="font-size: 2rem; width: 13vw; margin-top: 1.5vw">T-Store</p></a>
            </div>
            <div class="col">
                <ul class="nav justify-content-end ">
                    <sec:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                    <li class="nav-item">
                        <a class="nav-link ${fn:contains(currentPath, 'admin') ? 'active' : ''}"
                           href="/admin">Admin</a>
                    </li>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link ${fn:contains(currentPath, 'login') ? 'active' : ''}" href="/login">Log in</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${fn:contains(currentPath, 'register') ? 'active' : ''}"
                               href="/registration">Sign up</a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link ${fn:contains(currentPath, 'profile') ? 'active' : ''}"
                               href="/account">Account</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">Log out</a>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
        <div class="row align-items-center header-content site-padding">
            <div class="cart col-md-12">
                <sec:authorize access="isAuthenticated()">
                    <p id="loggedUserName" class="text-left" style="float: left">Your login: <sec:authentication property="principal.username" />
                    <c:if test="${message.errors.size() != 0}">
                        <c:forEach items="${message.errors}" var="error">
                            <p class="error" style="float: left"> ${error}</p>
                        </c:forEach>
                    </c:if>
                    <c:if test="${message.confirms.size() != 0}">
                        <c:forEach items="${message.confirms}" var="error">
                            <p class="confirm" style="float: left"> ${error}</p>
                        </c:forEach>
                    </c:if>
                    </p>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <p class="text-left" style="float: left">
                    <c:if test="${message.errors.size() != 0}">
                        <c:forEach items="${message.errors}" var="error">
                            <p class="error" style="float: left">${error}</p>
                        </c:forEach>
                    </c:if>
                    <c:if test="${message.confirms.size() != 0}">
                        <c:forEach items="${message.confirms}" var="error">
                            <p class="confirm" style="float: left">${error}</p>
                        </c:forEach>
                    </c:if>
                    </p>
                </sec:authorize>
                <p class="text-right" id="cartTemplate">
                    <jsp:include page="cartIcon.jsp"/>
                </p>
            </div>

        </div>
    </div>
</div>
