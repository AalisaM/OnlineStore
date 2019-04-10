<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col content" style="margin-left: 17vw; width: 70vw;">

    <h4>Products list</h4>
    <table class="table table-responsive" style="width: 68vw;">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Product ID</th>
            <th scope="col">Product Name </th>
            <th scope="col">Product Description</th>
            <th scope="col">Product Price</th>
            <th scope="col">Product Amount</th>

            <th scope="col">Product Weight</th>
            <th scope="col">Product Volume</th>
            <th scope="col">Players</th>
            <th scope="col">Image</th>
            <th scope="col">Category</th>
            <th scope="col"></th>
            <%--<th scope="col"></th>--%>
        </tr>
        </thead>
        <c:forEach items="${productList}" var="p">
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
                <td><img width="40px" src="/assets/images/product.png"></img></td>
                <td>${p.category.title}</td>
                <td><button class="editProduct btn"><img src="/assets/images/edit.png" alt="edit" style="width:20px"></button>
                </td>
                <%--<td><a href="<c:url value='/admin/products/remove/${p.id}' />" >Delete</a></td>--%>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="col content" style="margin-top:4vw; margin-left: 17vw; width: 70vw;">
    <h4>Add product</h4>

    <c:if test="${(! empty product) && (product.id > 0)}">
        <c:url var="addAction" value="/admin/products/edit"></c:url>
    </c:if>
    <c:if test="${(! empty product) && (product.id == 0)}">
        <c:url var="addAction" value="/admin/products/add"></c:url>
    </c:if>
    <form:form action="${addAction}" commandName="product" enctype="multipart/form-data"  id="productForm">
        <div class="form-group row" style="display: none">
            <label for="id" class="col-sm-3 col-form-label">ID</label>
            <div class="col">
                <input type="text" class="form-control" id="id" name="id"
                       value="${product.id}" placeholder="id" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="name" class="col-sm-3 col-form-label">Title</label>
            <div class="col">
                <input type="text" class="form-control" id="name" name="name"
                       value="${product.name}" placeholder="type" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="description" class="col-sm-3 col-form-label">Description</label>
            <div class="col">
                <input type="text" class="form-control" id="description" name="description"
                       value="${product.description}" placeholder="type" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="price" class="col-sm-3 col-form-label">Price</label>
            <div class="col">
                <input type="number" class="form-control" id="price" name="price"
                       value="${product.price}" placeholder="type" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="weight" class="col-sm-3 col-form-label">Weight</label>
            <div class="col">
                <input type="number" class="form-control" id="weight" name="weight"
                       value="${product.weight}" placeholder="type" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="volume" class="col-sm-3 col-form-label">Volume</label>
            <div class="col">
                <input type="number" class="form-control" id="volume" name="volume"
                       value="${product.volume}" placeholder="type" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="amount" class="col-sm-3 col-form-label">Amount</label>
            <div class="col">
                <input type="number" class="form-control" id="amount" name="amount"
                       value="${product.volume}" placeholder="type" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="minPlayerAmount" class="col-sm-3 col-form-label">Min player amount</label>
            <div class="col">
                <input type="number" class="form-control" id="minPlayerAmount" name="minPlayerAmount"
                       value="${product.volume}" placeholder="type" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="maxPlayerAmount" class="col-sm-3 col-form-label">Max player amount</label>
            <div class="col">
                <input type="number" class="form-control" id="maxPlayerAmount" name="maxPlayerAmount"
                       value="${product.volume}" placeholder="type" required>
            </div>
        </div>
        <div class="form-group row" readonly="true">
            <label for="imageSource" class="col-sm-3 col-form-label">Image source</label>
            <div class="col">
                <input type="text" class="form-control" id="imageSource" name="imageSource"
                       value="${product.imageSource}" placeholder="type" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="file" class="col-sm-3 col-form-label">Upload image</label>
            <div class="col">
                <input type="file" class="form-control" id="file" name="file"
                       value="${product.imageSource}" placeholder="type" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="category" class="col-sm-3 col-form-label">Category</label>
            <select id="category" class="form-control">
                <c:forEach items="${listCategories}" var="cat">
                    <option value="${cat.id}">${cat.title}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group row">
        <c:if test="${product.id != 0}">
            <button id="editProduct" class="addProduct btn btn-primary btn-lg btn-block orderbutton">Edit product</button>
        </c:if>
        <c:if test="${product.id == 0}">
            <button  id="addProduct" class="editProduct btn btn-primary btn-lg btn-block orderbutton">Add product</button>
        </c:if>
        </div>
    </form:form>
</div>