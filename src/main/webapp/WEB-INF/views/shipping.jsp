<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col content" style="margin-left: 17vw; width: 70vw;">

	<h4>Shipping Type list</h4>
	<table class="table" style="font-size: 18px">
		<thead class="thead-dark">
		<tr>
			<th scope="col">Shipping Type ID</th>
			<th scope="col">Shipping Type</th>
			<th scope="col">Edit</th>
			<th scope="col">Delete</th>
		</tr>
		</thead>
		<c:forEach items="${listShippingTypes}" var="shipping">
			<tr>
				<td class="sid">${shipping.id}</td>
				<td>${shipping.type}</td>
				<td>
					<button class="editShipping btn"><img src="/assets/images/edit.png" alt="edit" style="width:20px"></button>
				</td>
				<td>
					<button class="removeShipping btn"><img src="/assets/images/trash.png" alt="edit" style="width:20px"></button>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<div class="col content" style="margin-top:4vw; margin-left: 17vw; width: 70vw;">
	<h4>Add shipping</h4>

	<c:if test="${(! empty shipping) && (shipping.id > 0)}">
		<c:url var="addAction" value="/admin/shipping/edit" ></c:url>
	</c:if>
	<c:if test="${(! empty shipping) && (shipping.id == 0)}">
		<c:url var="addAction" value="/admin/shipping/add" ></c:url>
	</c:if>
	<form:form action="${addAction}" commandName="shipping">
		<div class="form-group row" style="display: none">
			<label for="id" class="col-sm-3 col-form-label">ID</label>
			<div class="col">
				<input type="text" class="form-control" id="id" name="id"
					   value="${shipping.id}" placeholder="id" required>
			</div>
		</div>
		<div class="form-group row">
			<label for="type" class="col-sm-3 col-form-label">Type</label>
			<div class="col">
				<input type="text" class="form-control" id="type" name="type"
					   value="${shipping.type}" placeholder="type" required>
			</div>
		</div>
		<c:if test="${!empty shipping.type}">
			<button class="updateShipping btn btn-primary btn-lg btn-block orderbutton">Edit shipping</button>
		</c:if>
		<c:if test="${empty shipping.type}">
			<button class="addShipping btn btn-primary btn-lg btn-block orderbutton">Add shipping</button>
		</c:if>
	</form:form>
</div>