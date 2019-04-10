<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Users Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<%@include file='header.jsp'%>

<h3>Users List</h3>
<c:if test="${!empty listUsers}">
    <table class="tg">
        <tr>
            <th width="80">User ID</th>
            <th width="80">User Name </th>
            <th width="80">User Email</th>
            <th width="80">User Active Address</th>
            <th width="80">Possible Addresses</th>

            <th width="80">User Birth</th>
            <th width="80">Admin</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listUsers}" var="u">
            <tr>
                <td>${u.id}</td>
                <td>${u.fullName}</td>
                <td>${u.email}</td>
                <td>${u.activeAddressId.address}</td>
                <td>
                    <select name="addressesSelect">
                        <c:forEach items="${u.addresses}" var="addr">
                            <option value="${addr.address}"> ${addr.address}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>${u.birth}</td>
                <td><input  onclick="return false;" type="checkbox" name="isAdmin" id="isAdmin" value="${u.admin}" ${u.admin ? 'checked' : ''}>
                </td>
                <td><a href="<c:url value='/users/remove/${u.id}' />" >Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
