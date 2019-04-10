<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col content" style="margin-left: 17vw; width: 70vw;">

	<h4>Categories list</h4>
	<table class="table" style="font-size: 18px">
		<thead class="thead-dark">
		<tr>
			<th scope="col">Category ID</th>
			<th scope="col">Category Name</th>
			<th scope="col">Parent Category</th>
			<th scope="col">Product Amount</th>
			<th scope="col">Edit</th>
			<th scope="col">Delete</th>
		</tr>
		</thead>
		<c:forEach items="${listCategories}" var="category">
			<tr>
				<td class="cid">${category.id}</td>
				<td>${category.name}</td>
				<td>${category.parentId}</td>
				<td>${category.products.size()}</td>
				<td>
					<button class="editCategory btn"><img src="/assets/images/edit.png" alt="edit" style="width:20px"></button>
				</td>
				<td>
					<button class="removeCategory btn"><img src="/assets/images/trash.png" alt="edit" style="width:20px"></button>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<div class="col content" style="margin-top:4vw; margin-left: 17vw; width: 70vw;">
	<h4>Add category</h4>

	<c:if test="${(! empty category) && (category.id > 0)}">
		<c:url var="addAction" value="/admin/categories/edit" ></c:url>
	</c:if>
	<c:if test="${(! empty category) && (category.id == 0)}">
		<c:url var="addAction" value="/admin/categories/add" ></c:url>
	</c:if>
	<form:form action="${addAction}" commandName="category">
		<div class="form-group row" style="display: none">
			<label for="id" class="col-sm-3 col-form-label">ID</label>
			<div class="col">
				<input type="text" class="form-control" id="id" name="id"
					   value="${category.id}" placeholder="id" required>
			</div>
		</div>

		<div class="form-group row">
			<label for="name" class="col-sm-3 col-form-label">Name</label>
			<div class="col">
				<input type="text" class="form-control" id="name" name="name"
					   value="${category.name}" placeholder="name" required>
			</div>
		</div>

		<div class="form-group row">
			<label for="parentId" class="col-sm-3 col-form-label">Parent Category</label>
			<div class="col">
				<select id="parentId" class="form-control" name="parentId">
					<c:forEach items="${listCategories}" var="cat">
						<option value="${cat.id}">${cat.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<c:if test="${!empty category.name}">
			<button class="updateCategory btn btn-primary btn-lg btn-block orderbutton">Edit Category</button>
		</c:if>
		<c:if test="${empty category.name}">
			<button class="addCategory btn btn-primary btn-lg btn-block orderbutton">Add Category</button>
		</c:if>
	</form:form>
</div>