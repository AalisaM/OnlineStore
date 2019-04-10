<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col content" style="margin-left: 17vw; width: 70vw;">
    <span id="error" style="display:none;" class="error">Password mismatch</span>
    <c:if test="${! empty message.errors}">
        <span class="error">${message.errors[0]}</span>
    </c:if>
    <div class="form-group row">
        <label for="oldpass" class="col-sm-3 col-form-label">Old password</label>
        <div class="col">
            <input type="password" class="form-control" id="oldpass" name="oldpassword"
                    placeholder="oldpass" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="pass" class="col-sm-3 col-form-label">New password</label>
        <div class="col">
            <input type="password" class="form-control" id="pass" name="password"
                    placeholder="password" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="passConfirm" class="col-sm-3 col-form-label">Confirm new password</label>
        <div class="col">
            <input type="password" class="form-control" id="passConfirm" name="passConfirm"
                   placeholder="passConfirm">
        </div>
    </div>
    <button id="updatePswd" type="submit" class="btn btn-primary btn-lg btn-block orderbutton">Update password</button>
</div>
