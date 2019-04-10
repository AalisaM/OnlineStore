<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Products Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
    <script src="/assets/js/jquery.js"></script>
    <script src="/assets/js/cartOrderEvents.js"></script>
</head>
<body>
<%@include file='header.jsp'%>
<h3> Add Product </h3>
<c:if test="${(! empty product) && (product.id > 0)}">
    <c:url var="addAction" value="/admin/products/edit"></c:url>
</c:if>
<c:if test="${(! empty product) && (product.id == 0)}">
    <c:url var="addAction" value="/admin/products/add"></c:url>
</c:if>
<form:form action="${addAction}" commandName="product" enctype="multipart/form-data"  id="productForm">
    <table>
        <c:if test="${!empty product.name}">
            <tr>
                <td>
                    <form:label path="id"> <spring:message text="ID"/> </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8"  disabled="true" /><form:hidden path="id" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="name"> <spring:message text="name"/></form:label>
            </td>
            <td>
                <form:input path="name" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="description"> <spring:message text="description"/></form:label>
            </td>
            <td>
                <form:input path="description" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="price"> <spring:message text="price"/></form:label>
            </td>
            <td>
                <form:input path="price" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="weight"><spring:message text="weight"/></form:label>
            </td>
            <td>
                <form:input path="weight" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="volume"> <spring:message text="volume"/></form:label>
            </td>
            <td>
                <form:input path="volume" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="amount"> <spring:message text="amount"/></form:label>
            </td>
            <td>
                <form:input path="amount" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="minPlayerAmount"> <spring:message text="minPlayerAmount"/></form:label>
            </td>
            <td>
                <form:input path="minPlayerAmount" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="maxPlayerAmount"> <spring:message text="maxPlayerAmount"/></form:label>
            </td>
            <td>
                <form:input path="maxPlayerAmount" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="imageSource"> <spring:message text="imageSource"/></form:label>
            </td>
            <td>
                <form:input path="imageSource" />
            </td>
        </tr>
        <tr>
            <input type="file" name="file" id="file"/><br/><br/>
        </tr>
        <tr>
            <td>
                <form:label path="category">
                    <spring:message text="category"/>
                </form:label>
            </td>
            <td>
                <form:select path="category" items="${listCategories}" multiple="false" itemValue="id"
                             itemLabel="name" class="form-control input-sm" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button id="addProduct">Add product</button>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
