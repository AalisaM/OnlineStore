<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${message.errors.size() != 0}">
    <c:forEach items="${message.errors}" var="error">
        <p class="error">${error}</p>
    </c:forEach>
</c:if>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Product</th>
        <th scope="col">Price</th>
        <th scope="col">Quantity</th>
        <th scope="col">Total</th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${!empty cartExtendedDTO.curCart}">
        <c:forEach items="${cartExtendedDTO.curCart.cartItem}" var="c">
            <tr class="productItem">
                <td class="productid" style="display:none;">${c.product.id}</td>
                <td class="productname"> ${c.product.name}</td>
                <td class="productprice">${c.price}</td>
                <td class="amount">${c.amount}</td>
                <td class="total">${(c.amount * c.price)}</td>
                <td><button class="cartIncItem">Inc</button></td>
                <td><button class="cartDecItem">Dec</button></td>
                <td><button class="deleteCartItem">Delete</button></td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${!empty cartExtendedDTO.cartAnon}">
        <c:forEach items="${cartExtendedDTO.cartAnon.cartItem}" var="c">
            <tr>
                <td class="productid" style="display:none;">${c.product_id}</td>
                <td class="productname">${c.product_name}</td>
                <td class="productprice">${c.price}</td>
                <td class="amount">${c.amount}</td>
                <td class="total">${(c.amount * c.price)}</td>
                <td><button class="cartIncItemAnon">Inc</button></td>
                <td><button class="cartDecItemAnon">Dec</button></td>
                <td><button class="deleteCartItem">Delete</button></td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
