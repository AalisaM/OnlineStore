/**
 * adds address adding form on page
 * */
$("body").on("click","#add", function () {
    $("#addAddress").attr("hidden", false);
    $("#newAddr").attr("hidden", false);
    $("#cancelAddAddress").attr("hidden", false);
  //  $("input").trigger("focus");
});

/**
 * hides address form
 * **/
$("body").on("click","#cancelAddAddress", function(){
    $("#addAddress").attr("hidden", true);
    $("#newAddr").attr("hidden", true);
    $("#cancelAddAddress").attr("hidden", true);
    $("#country").val();
    $("#index").val();
    $("#city").val();
    $("#house").val();
    $("#flat").val();
});

/**
 * Get data from form and send it to controller
 * */
$("body").on("click","#addAddress", function () {
    var fullAddr = $("#country").val() + ", " +
        $("#city").val() + ", " +
        $("#index").val() + ", " +
        $("#street").val() + ", " +
        $("#house").val() + ", " +
        $("#flat").val();
    var userId = $($("form").find("#id")[0]).val();
    $.ajax({
        url: '/account/addAddress',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({"user_id": userId, "address_id": 0, "address_str": fullAddr}),
        success: function () {
            document.location.reload(true);
        }
    })
    $("#addAddress").attr("hidden", true);
    $("#newAddr").attr("hidden", true);
});

/**
 * Removes address for user.
 * */
$("body").on("click", ".removeAddress", function () {
    var addressId = $($($(this).closest("tr")).find(".idAddr")[0]).text();
    var userId = $($("form").find("#id")[0]).val();
    $.ajax({
        url: '/account/removeAddress',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({"user_id": userId, "address_id": addressId}),
        success: function () {
            document.location.reload(true);
        }
    })
});

/**
 * Calls editing form for address
 * */
$("body").on("click",".editAddress", function () {
    $($($(this).closest("tr")).find(".addrNameValue")[0]).attr("hidden", true)
    $($($(this).closest("tr")).find(".addrNameEdit")[0]).attr("hidden", false)
    $($($(this).closest("tr")).find(".editAddress")[0]).attr("hidden", true)
    $($($(this).closest("tr")).find(".applyEditAddress")[0]).attr("hidden", false)
    $($($(this).closest("tr")).find(".removeAddress")[0]).attr("hidden", true)
    $($($(this).closest("tr")).find(".cancelEditAddress")[0]).attr("hidden", false)

    var fullAddr = $($($(this).closest("tr")).find(".addrNameValue")[0]).text().split(",");
    console.log(fullAddr);
    $($($(this).closest("tr")).find(".countryEd")[0]).val(fullAddr[0]);
    $($($(this).closest("tr")).find(".cityEd")[0]).val(fullAddr[1]);
    $($($(this).closest("tr")).find(".indexEd")[0]).val(fullAddr[2]);
    $($($(this).closest("tr")).find(".streetEd")[0]).val(fullAddr[3]);
    $($($(this).closest("tr")).find(".houseEd")[0]).val(fullAddr[4]);
    $($($(this).closest("tr")).find(".flatEd")[0]).val(fullAddr[5]);

});

/***
 * Sends update address request
 * */
$("body").on("click",".applyEditAddress", function () {
    var addressId = $($($(this).closest("tr")).find(".idAddr")[0]).text();
    var userId = $($("form").find("#id")[0]).val();
    var fullAddr =  $($($(this).closest("tr")).find(".countryEd")[0]).val() + ", " +
        $($($(this).closest("tr")).find(".cityEd")[0]).val() + ", " +
        $($($(this).closest("tr")).find(".indexEd")[0]).val() + ", " +
        $($($(this).closest("tr")).find(".streetEd")[0]).val() + ", " +
        $($($(this).closest("tr")).find(".houseEd")[0]).val() + ", " +
        $($($(this).closest("tr")).find(".flatEd")[0]).val();

    $.ajax({
        url: '/account/editAddress',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({"user_id": userId, "address_id": addressId, "address_str": fullAddr}),
        success: function () {
            document.location.reload(true);
        },error : function (e) {
            console.log(e)
        }
    });	//check if active set active
    $($($(this).closest("tr")).find(".addrNameValue")[0]).attr("hidden", false);
    $($($(this).closest("tr")).find(".addrNameEdit")[0]).attr("hidden", true);

    $($($(this).closest("tr")).find(".cityEd")[0]).val("");
    $($($(this).closest("tr")).find(".indexEd")[0]).val("");
    $($($(this).closest("tr")).find(".streetEd")[0]).val("");
    $($($(this).closest("tr")).find(".houseEd")[0]).val("");
    $($($(this).closest("tr")).find(".flatEd")[0]).val("");

    $($($(this).closest("tr")).find(".addrNameValue")[0]).text(fullAddr);
    $($($(this).closest("tr")).find(".editAddress")[0]).attr("hidden", false);
    $($($(this).closest("tr")).find(".applyEditAddress")[0]).attr("hidden", true);
    $($($(this).closest("tr")).find(".removeAddress")[0]).attr("hidden", false);
    $($($(this).closest("tr")).find(".cancelEditAddress")[0]).attr("hidden", true);

});

/**allows only one checkbox per page in case it not drugs*/
$("body").on("change", ".activeAddr", function () {
    if ((this.checked)) {
        $(".activeAddr").not(this).prop("checked", false);
        $.ajax({
            url: '/account/setActiveAddress',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            success: function () {
                document.location.reload(true);
            },
            data: JSON.stringify({
                "user_id": $($("form").find("#id")[0]).val(),
                "address_id": $($($(this).closest("tr")).find(".idAddr")[0]).text()
            })
        });
    }
});

/**
 * hides address editing form
 * **/
$("body").on("click", ".cancelEditAddress", function () {

    $($($(this).closest("tr")).find(".addrNameValue")[0]).attr("hidden", false);
    $($($(this).closest("tr")).find(".addrNameEdit")[0]).attr("hidden", true);
    $($($(this).closest("tr")).find(".editAddress")[0]).attr("hidden", false);
    $($($(this).closest("tr")).find(".applyEditAddress")[0]).attr("hidden", true);
    $($($(this).closest("tr")).find(".removeAddress")[0]).attr("hidden", false);
    $($($(this).closest("tr")).find(".cancelEditAddress")[0]).attr("hidden", true);
    $("#countryEd").val("");
    $("#cityEd").val("");
    $("#indexEd").val("");
    $("#streetEd").val("");
    $("#houseEd").val("");
    $("#flatEd").val("");
    $($($(this).closest("tr")).find(".addrNameValue")[0]).attr("hidden", false);
    $($($(this).closest("tr")).find(".addrNameEdit")[0]).attr("hidden", true);
    ;
});

/**
 * Calls change password page
 * */
$("body").on("click","#cpswd" , function(){
    $.ajax({
        url: '/account/changePassword',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(""),
        success: function (s) {
            $("#curModule").html(s);
        },
        error: function (e) {
            console.log(data);
            console.log(e)
        }
    })
});

/**
 * Tries to update password data
 * **/
$("body").on("click","#updatePswd" , function(){
    var pass = $("#pass").val();
    var valid = (pass == $("#passConfirm").val());
    if (valid) {
        $.ajax({
            url: '/account/updatePassword',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({"password": pass, "oldPassword": $("#oldpass").val()}),
            success: function (s) {
                $("body").html(s);
            },
            error: function (e) {
                console.log(e)
            }
        });
    }else {
       $("#error").show();
    }
});

/**
 * Calls user order history for account
 * */
$("body").on("click","#localOH" , function(){
    $.ajax({
        url: '/userOrder',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(""),
        success: function (s) {
            $("#curModule").html(s);
        },
        error: function (e) {
            console.log(data);
            console.log(e)
        }
    })
});