<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Алиса
  Date: 21.03.2019
  Time: 2:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if  test="${!empty cartExtendedDTO.cartAnon}">
    <c:if  test="${!empty cartExtendedDTO.cartAnon.cartItem}">


    Total amount of products is: <p id="lblCartCount">${cartExtendedDTO.cartAnon.totalAmount}</p>
    Total price is: <p id="cartPrice">${cartExtendedDTO.cartAnon.totalPrice}</p>

    <table class="tg">
        <tr>
            <th width="80">Product id</th>
            <th width="80">Product Name</th>
            <th width="80">Product Amount</th>
            <th width="80">Product Total Price</th>
            <th width="60">Inc</th>
            <th width="60">Dec</th>
            <th width="60">Delete</th>
        </tr>

            <c:forEach items="${cartExtendedDTO.cartAnon.cartItem}" var="c">
                <tr>
                    <td class="productid">${c.product_id}</td>
                    <td class="productname">${c.product_name}</td>
                    <td class="amount">${c.amount}</td>
                    <td class="productprice">${c.price}</td>
                    <td><button class="cartIncItemAnon">Inc</button></td>
                    <td><button class="cartDecItemAnon">Dec</button></td>
                    <td><button class="deleteCartItem">Delete</button></td>
                </tr>
            </c:forEach>
    </table>
    </c:if>
</c:if>
