<%--
  Created by IntelliJ IDEA.
  User: Алиса
  Date: 27.03.2019
  Time: 1:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div id="errormsg" style="display:none"></div>
<div>
    ${(empty message.errors) ? "" : message.errors}
    <input id="oldpass" name="oldpassword" type="password" />
    <input id="pass" name="password" type="password" />
    <input id="passConfirm" type="password" />
    <span id="error" style="display:none">Password mismatch</span>

    <button type="submit" onclick="savePass()">Change Password</button>
</div>

<script src="/assets/js/jquery.js"></script>
<script type="text/javascript">
     function savePass(){
            var pass = $("#pass").val();
            var valid = pass == $("#passConfirm").val();
            $.ajax({
                url: '/account/updatePassword',
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({"password": pass, "oldPassword": $("#oldpass").val()}),
                success: function(data, textStatus) {
                    //  document.location.reload(true);
                },
                error: function (e) {
                    console.log(e)
                }});
        }
</script>
</body>
</html>