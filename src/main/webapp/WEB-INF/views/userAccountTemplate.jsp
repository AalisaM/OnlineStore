<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col content" style="margin-left: 17vw; width: 70vw;">

<c:url var="addAction" value="/account/edit" ></c:url>
<form:form action="${addAction}" commandName="user">
    <div class="form-group row" style="display: none">
        <label for="id" class="col-sm-3 col-form-label">ID</label>
        <div class="col">
            <input type="text" class="form-control" id="id" name="id"
                   value="${user.id}" placeholder="id" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="fullName" class="col-sm-3 col-form-label">Full Name</label>
        <div class="col">
            <input type="text" class="form-control" id="fullName" name="fullName"
                   value="${user.fullName}" placeholder="name" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="email" class="col-sm-3 col-form-label">E-mail</label>
        <div class="col">
            <input type="text" class="form-control" id="email" name="email"
                   value="${user.email}" placeholder="email" readonly>
        </div>
    </div>
    <div class="form-group row">
        <label for="birth" class="col-sm-3 col-form-label">Birth</label>
        <div class="col">
            <input type="date" class="form-control" id="birth" name="birth"
                   value="${user.birth}" placeholder="birth" required>
        </div>
    </div>
    <button id="updateInfoBtn" type="submit" class="btn btn-primary btn-lg btn-block orderbutton">Save</button>
</form:form>
</div>
<div class="col content" style="margin-top:4vw; margin-left: 17vw; width: 70vw;">
<h1>User's addresses info</h1>
<table class="tg" style="width: 100%;">
    <thead class="thead-dark">
    <tr>
        <th width="80">Address</th>
        <th width="80">Active</th>
        <th width="80">Edit</th>
        <th width="80">Delete</th>
    </tr>
    </thead>
    <c:forEach items="${user.addresses}" var="address">
        <tr>
            <td class="idAddr" style="display: none;">${address.id}</td>
            <td class="addrName"><p class="addrNameValue"> ${address.address}</p>
                <p class="addrNameEdit" hidden>
                    <input type="text"  class="countryEd"  placeholder="Country">
                    <input type="text"  class="cityEd"  placeholder="City" >
                    <input type="text"  class="indexEd"  placeholder="Index" >
                    <input type="text"  class="streetEd" placeholder="Street"  >
                    <input type="text"  class="houseEd" placeholder="House"  >
                    <input type="text"  class="flatEd"  placeholder="Flat">
                </p>
            <td><input type="checkbox" name="active" class="activeAddr" value="${user.activeAddressId.id}" ${user.activeAddressId.id == address.id ? 'checked' : ''}>
            </td>
            <td><button class="editAddress btn "><img src="/assets/images/edit.png" alt="edit" style="width:20px"></button>
                <button class="applyEditAddress btn orderbutton" hidden>Apply</button></td>
            <td><button class="removeAddress btn"><img src="/assets/images/trash.png" alt="edit" style="width:20px"></button>
                <button class="cancelEditAddress btn btn-secondary" hidden>Cancel</button></td>
        </tr>
    </c:forEach>
</table>

<button id="add" class="btn btn-lg"><img src="/assets/images/add.png" alt="edit" style="width:50px"> Add new delivery address</button>
<div id="newAddr" hidden="hidden" style="margin-top: 2vh">
    <div class="form-group row" style="display: none">
        <label for="country" class="col-sm-3 col-form-label">Country</label>
        <div class="col">
            <input type="text" class="form-control" id="country" name="country"
                   placeholder="country" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="city" class="col-sm-3 col-form-label">City</label>
        <div class="col">
            <input type="text" class="form-control" id="city" name="city"
                   placeholder="city" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="index" class="col-sm-3 col-form-label">Index</label>
        <div class="col">
            <input type="text" class="form-control" id="index" name="index"
                  placeholder="index" required>
        </div>
    </div>

    <div class="form-group row">
        <label for="street" class="col-sm-3 col-form-label">Street</label>
        <div class="col">
            <input type="text" class="form-control" id="street" name="street"
                   placeholder="street" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="house" class="col-sm-3 col-form-label">House</label>
        <div class="col">
            <input type="text" class="form-control" id="house" name="house"
                   placeholder="house" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="flat" class="col-sm-3 col-form-label">Flat</label>
        <div class="col">
            <input type="text" class="form-control" id="flat" name="flat"
                   placeholder="flat" required>
        </div>
    </div>
</div>

<button id="addAddress" hidden class="btn btn-primary btn-lg orderbutton">Add</button>
<button id="cancelAddAddress" hidden class="btn btn-secondary btn-lg">Cancel</button>
</div>
