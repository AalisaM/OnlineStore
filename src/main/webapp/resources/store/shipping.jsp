<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Shipping Type Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<%@include file='header.jsp'%>

Add Shipping Type
</h1>

<c:if test="${(! empty shipping) && (shipping.id > 0)}">
	<c:url var="addAction" value="/admin/shipping/edit" ></c:url>
</c:if>
<c:if test="${(! empty shipping) && (shipping.id == 0)}">
	<c:url var="addAction" value="/admin/shipping/add" ></c:url>
</c:if>
<form:form action="${addAction}" commandName="shipping">
	<table>
		<c:if test="${!empty shipping.type}">
			<tr>
				<td>
					<form:label path="id">
						<spring:message text="ID"/>
					</form:label>
				</td>
				<td>
					<form:input path="id" readonly="true" size="8"  disabled="true" />
					<form:hidden path="id" />
				</td>
			</tr>
		</c:if>
		<tr>
			<td>
				<form:label path="type">
					<spring:message text="type"/>
				</form:label>
			</td>
			<td>
				<form:input path="type" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:if test="${!empty shipping.type}">
					<input type="submit"
						   value="<spring:message text="Edit shipping"/>" />
				</c:if>
				<c:if test="${empty shipping.type}">
					<input type="submit"
						   value="<spring:message text="Add shipping"/>" />
				</c:if>
			</td>
		</tr>
	</table>
</form:form>

<br>
<h3>Shipping Type List</h3>
<c:if test="${!empty listShippingTypes}">
	<table class="tg">
	<tr>
		<th width="80">Shipping Type ID</th>
		<th width="120">Shipping Type </th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listShippingTypes}" var="shipping">
		<tr>
			<td>${shipping.id}</td>
			<td>${shipping.type}</td>
			<td><a href="<c:url value='/admin/shipping/edit/${shipping.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/admin/shipping/remove/${shipping.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
