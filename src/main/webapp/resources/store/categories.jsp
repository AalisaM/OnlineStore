<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Categories page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<%@include file='header.jsp'%>

<h1>
	Add Category
</h1>
<c:if test="${(! empty category) && (category.id > 0)}">
	<c:url var="addAction" value="/admin/categories/edit" ></c:url>
</c:if>
<c:if test="${(! empty category) && (category.id == 0)}">
	<c:url var="addAction" value="/admin/categories/add" ></c:url>
</c:if>

<form:form action="${addAction}" commandName="category">
	<table>
		<c:if test="${!empty category.name}">
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
				<form:label path="name">
					<spring:message text="name"/>
				</form:label>
			</td>
			<td>
				<form:input path="name" />
			</td>
		</tr>
        <tr>
            <td>
                <form:label path="parentId">
                    <spring:message text="parentId"/>
                </form:label>
            </td>
            <td>
                <select name="parentId"  path="parentId">
                <c:forEach items="${listCategories}" var="cat">
                    <option value="${cat.id}"> ${cat.name}</option>
                </c:forEach>
             </select>
            </td>
        </tr>
		<tr>
			<td colspan="2">
				<c:if test="${!empty category.name}">
					<input type="submit"
						   value="<spring:message text="Edit category"/>" />
				</c:if>
				<c:if test="${empty category.name}">
					<input type="submit"
						   value="<spring:message text="Add category"/>" />
				</c:if>
			</td>
		</tr>
	</table>
</form:form>
<br>
<h3>Caegories List</h3>
<c:if test="${!empty listCategories}">
	<table class="tg">
	<tr>
		<th width="80">Category ID</th>
		<th width="120">Category Name </th>
        <th width="120">Parent Category </th>
        <th width="120">Product Amount </th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listCategories}" var="category">
		<tr>
			<td>${category.id}</td>
            <td>${category.name}</td>
            <td>${category.parentId}</td>
            <td>${category.products.size()}</td>
			<td><a href="<c:url value='/admin/categories/edit/${category.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/admin/categories/remove/${category.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
