<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Products Page</title>
    <div id="cartTemplate"><jsp:include page="../../WEB-INF/views/cartIcon.jsp"></jsp:include></div>
    <%@include file='header.jsp'%>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
    <script src="/assets/js/jquery.js"></script>
    <script src="/assets/js/cart.js"></script>
    <script src="/assets/js/cartOrderEvents.js"></script>
</head>
<body>

<h3> Filter </h3>

<form action="/filter" id="filterForm" >
    <table>
        <tr>
            <td>
                <label > <spring:message text="max price"/></label>
            </td>
            <td>
                <input  id="priceFilter"/>
            </td>
        </tr>
        <tr>
            <td>
                <label ><spring:message text="min player amount"/></label>
            </td>
            <td>
                <input  id="minPlayerAmountFilter"/>
            </td>
        </tr>
        <tr>
            <td>
                <label> <spring:message text="max player amount"/></label>
            </td>
            <td>
                <input path="maxPlayerAmount" id="maxPlayerAmountFilter"/>
            </td>
        </tr>
        <tr>
            <td>
                <label>
                    <spring:message text="category"/>
                </label>
            </td>
            <td>
                <select id="categoryFilter">
                    <c:forEach items="${listCategories}" var="cat">
                        <option value="${cat.id}">${cat.title}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button id="applyFilter">Apply filter</button>
            </td>
        </tr>
    </table>
</form>

    <h3>Product List</h3>
    <div id="productListDiv">
        <jsp:include page="filtered.jsp"></jsp:include>
    </div>
</body>
</html>
