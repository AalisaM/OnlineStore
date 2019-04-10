<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col content" style="margin-left: 17vw; width: 72vw;">
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
    <table class="table table-responsive" style="width: 68vw;">
        <thead class="thead-dark">
        <tr>
            <th scope="col">User ID</th>
            <th scope="col">User Name </th>
            <th scope="col">User Email</th>
            <th scope="col">User Active Address</th>
            <th scope="col">Possible Addresses</th>

            <th scope="col">User Birth</th>
            <th scope="col">Is Admin ?</th>
        </tr>
        </thead>
        <tbody>
        <%--<c:forEach items="${categorizedOrders.unpaidOrders}" var="c">--%>
        <c:forEach items="${listUsers}" var="u">
            <tr>
                <td class="userid">${u.id}</td>
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
                <td><input type="checkbox" name="isAdmin" class="isAdmin" value="${u.admin}" ${u.admin ? 'checked' : ''}>
                </td>
                <%--<td><a href="<c:url value='/users/remove/${u.id}' />" >Delete</a></td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
