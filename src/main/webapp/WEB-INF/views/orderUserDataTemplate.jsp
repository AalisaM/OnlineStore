<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-3 navigation" style="position: fixed;">
    <div class="row filter content">
        <div class="col">
            <div class="form-row">
                <div class="form-group col-6">
                   <input readonly type="text" class="form-control" id="userName"
                           aria-describedby="maxCostHelp" value="${cartExtendedDTO.user.fullName}">
                    <small class="form-text text-muted">Your name</small>
                </div>
                <p id="userEmail" hidden>${cartExtendedDTO.user.email}</p>

                <div class="form-group col-6">
                    <input readonly type="text" class="form-control" id="address"
                           aria-describedby="maxCostHelp" value="${cartExtendedDTO.user.activeAddressId.address}">
                    <small class="form-text text-muted">Your delivery address</small>
                </div>
                <div class="form-group col-6">
                    <select id="shipping" class="form-control">
                        <c:forEach items="${cartExtendedDTO.shippingType}" var="cat">
                            <option value="${cat.id}">${cat.type}</option>
                        </c:forEach>
                    </select>
                    <small class="form-text text-muted">Choose shipping type</small>

                </div>
                <div class="form-group col-6">
                    <select id="payment" class="form-control">
                        <c:forEach items="${cartExtendedDTO.paymentType}" var="cat">
                            <option value="${cat.id}">${cat.type}</option>
                        </c:forEach>
                    </select>
                    <small class="form-text text-muted">Choose payment type</small>
                </div>
                <div class="form-group col-8">
                    <button id="makeOrder" class="btn btn-primary btn-lg btn-block orderbutton">Make Order</button>
                </div>
            </div>
        </div>
    </div>
</div>