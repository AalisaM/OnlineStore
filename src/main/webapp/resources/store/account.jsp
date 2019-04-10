<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Account</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
    <script src="/assets/js/jquery.js"></script>
    <script src="/assets/js/account.js"></script>
</head>
<body>
<c:if test="${! empty message}">
    ${message.confirms}
</c:if>

<div id="cartTemplate"><jsp:include page="../../WEB-INF/views/cartIcon.jsp"></jsp:include></div>

<h3> User profile </h3>
<c:url var="addAction" value="/account/edit" ></c:url>

<form:form action="${addAction}" commandName="user">
    <table>
            <tr>
                <td>
                    <form:label path="id"> <spring:message text="ID"/> </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8"  disabled="true" /><form:hidden path="id" />
                </td>
            </tr>
        <tr>
            <td>
                <form:label path="fullName"> <spring:message text="fullName"/></form:label>
            </td>
            <td>
                <form:input path="fullName" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email"> <spring:message text="email"/></form:label>
            </td>
            <td>
                <form:input path="email" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="birth"> <spring:message text="birth"/></form:label>
            </td>
            <td>
                <form:input path="birth" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password"> <spring:message text="password"/></form:label>
            </td>
            <td>
                <form:input path="password" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                    <input type="submit" id="editUser" value="<spring:message text="Edit User"/>" />
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
