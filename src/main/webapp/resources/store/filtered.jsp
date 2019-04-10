<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:if test="${!empty listProducts}">
    <table class="tg">
        <tr>
            <th width="80">Product ID</th>
            <th width="80">Product Name </th>
            <th width="80">Product Description</th>
            <th width="80">Product Price</th>
            <th width="80">Product Amount</th>

            <th width="80">Product Weight</th>
            <th width="80">Product Volume</th>
            <th width="80">Players</th>
            <th width="80">Image</th>
            <th width="80">Category</th>

            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listProducts}" var="p">
            <tr>
                <td class="productid">${p.id}</td>
                <td class="productname">${p.name}</td>
                <td>${p.description}</td>
                <td class="productprice">${p.price}</td>
                <td>${p.amount}</td>
                <td>${p.weight}</td>
                <td>${p.volume}</td>
                <td>${p.minPlayerAmount} - ${p.maxPlayerAmount}</td>
                    <%--<td>${p.imageSource}</td>--%>
                <td><img width="100px" src="/assets/images/product.png"></img></td>
                <td>${p.category.title}</td>
                <td><a href="<c:url value='/admin/products/edit/${p.id}' />" >Edit</a></td>
                <td><a href="<c:url value='/admin/products/remove/${p.id}' />" >Delete</a></td>
                <td><button class="addToCart">Add</button></td>
                <td><button class="removeFromCart">Remove</button></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
