<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col content" style="margin-top: 25vh; margin-left: 25vw; width: 60vw;">
    <div class="row justify-content-start product-list">
        <c:forEach items="${listProducts}" var="product">
            <div class="col">
                <div class="card">
                    <a href="/product/${product.id}">
                        <div class="card-body">
                            <h5 class="card-title productname">${product.name}</h5>
                            <div style="text-align: center">
                                <img width="80px" src="/assets/images/product.png"/>
                            </div>
                            <p class="card-text productprice">${product.price}</p>
                            <p hidden class="productid">${product.id}</p>
                            <div style="text-align: center">
                                <button class="removeFromCart btn btn-secondary">-</button>
                                <button class="addToCart btn orderbutton">+</button>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>