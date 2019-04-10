/**
 * Calls updaid orders page for admin
 * */

$("body").on("click","#paid" , function(){
    $.ajax({
        url: '/paidorders',
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
 * Calls paid orders page for admin
 * */

$("body").on("click","#unpaid" , function(){
    $.ajax({
        url: '/unpaidorders',
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
 * Calls delivered orders page for admin
 * */
$("body").on("click","#delivered" , function(){
    $.ajax({
        url: '/deliveredorders',
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
 * Calls shipping page for admin
 * */
$("body").on("click","#shippingAdmin" , function(){
    $.ajax({
        url: '/admin/shipping',
        type: 'GET',
        contentType: "application/json; charset=utf-8",
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
 * Sends remove shipping type request to server
 * */
$("body").on("click",".removeShipping" , function(){
    var id = parseInt($($(this).closest("tr").find(".sid")[0]).text(),10);
    $.ajax({
        url:  "/admin/shipping/remove/"+id,
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);

        },
        error: function (e) {
            console.log(e)
        }
    })
});


/**
 * Calls edit shipping form request
 * */
$("body").on("click",".editShipping" , function(){
    var id = parseInt($($(this).closest("tr").find(".sid")[0]).text(),10);
    $.ajax({
        url: "/admin/shipping/edit/"+id,
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);
            window.location.hash = '#type';
        },
        error: function (e) {
            console.log(e)
        }
    })
});


/**
 * Sends add shipping request
 * */
$("body").on("click",".addShipping" , function(e){
    e.preventDefault();

    var id = parseInt($("#id").val(),10);
    var type = $("#type").val();

    var data = JSON.stringify({"id" : id, "type" : type});
    $.ajax({
        url: "/admin/shipping/add/",
        type: 'POST',
        data: data,
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);
        },
        error: function (e) {
            console.log(e);
            console.log(data);
        }
    })
});

/**
 * Sends shipping update request
 * */
$("body").on("click",".updateShipping" , function(e){
    e.preventDefault();
    var id = parseInt($("#id").val(),10);
    var type = $("#type").val();

    var data = JSON.stringify({"id" : id, "type" : type});

    $.ajax({
        url: "/admin/shipping/edit/",
        type: 'POST',
        data: data,
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);
        },
        error: function (e) {
            console.log(e);
            console.log(data);
        }
    })
});


/**
 * Categories block of code
 * */
$("body").on("click","#categoriesAdmin" , function(){
    $.ajax({
        url: '/admin/categories',
        type: 'GET',
        contentType: "application/json; charset=utf-8",
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
 * removes category request
 * */
$("body").on("click",".removeCategory" , function(){
    var id = parseInt($($(this).closest("tr").find(".cid")[0]).text(),10);
    $.ajax({
        url:  "/admin/categories/remove/"+id,
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);

        },
        error: function (e) {
            console.log(e)
        }
    })
});

/**
 * edit category get form request
 * */
$("body").on("click",".editCategory" , function(){
    var id = parseInt($($(this).closest("tr").find(".cid")[0]).text(),10);
    $.ajax({
        url: "/admin/categories/edit/"+id,
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);
            window.location.hash = '#name';
        },
        error: function (e) {
            console.log(e)
        }
    })
});

/**
 * add category request
 * */
$("body").on("click",".addCategory" , function(e){
    e.preventDefault();

    var id = parseInt($("#id").val(),10);
    var name = $("#name").val();
    var parentId = parseInt($("#parentId").find("option:selected").val(),10);

    var data = JSON.stringify({"id" : id, "name" : name, "parentId" : parentId});
    $.ajax({
        url: "/admin/categories/add/",
        type: 'POST',
        data: data,
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);
        },
        error: function (e) {
            console.log(e);
            console.log(data);
        }
    })
});


/**
 * sends update category request
 * */
$("body").on("click",".updateCategory" , function(e){
    e.preventDefault();
    var id = parseInt($("#id").val(),10);
    var name = $("#name").val();
    var parentId = parseInt($("#parentId").find("option:selected").val(),10);

    var data = JSON.stringify({"id" : id, "name" : name, "parentId" : parentId});

    $.ajax({
        url: "/admin/categories/edit/",
        type: 'POST',
        data: data,
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);
        },
        error: function (e) {
            console.log(e);
            console.log(data);
        }
    })
});


/**
 * Users panel in admin page
 * */
$("body").on("click","#users" , function(){
    $.ajax({
        url: '/admin/users',
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);
        },
        error: function (e) {
            console.log(e)
        }
    })
});


/**
 * Products panel in admin page
 * */
$("body").on("click","#productsAdminList" , function(){
    $.ajax({
        url: '/admin/products',
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);
        },
        error: function (e) {
            console.log(e)
        }
    })
});

/**
 * remove product request
* */
$("body").on("click",".removeProduct" , function(){
    var id = parseInt($($(this).closest("tr").find(".cid")[0]).text(),10);
    $.ajax({
        url:  "/admin/products/remove/"+id,
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);

        },
        error: function (e) {
            console.log(e)
        }
    })
});

/**
 * edit product request
 * */
$("body").on("click",".editProduct" , function(e){
    e.preventDefault();
    var id = parseInt($($(this).closest("tr").find(".productid")[0]).text(),10);
    $.ajax({
        url: "/admin/products/edit/"+id,
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);
            window.location.hash = '#name';
        },
        error: function (e) {
            console.log(e)
        }
    })
});


/**
 * get statistics page
 * */
$("body").on("click","#statistics" , function(){
    $1.ajax({
        url: 'admin/statistics',
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        success: function (s) {
            $("#curModule").html(s);
        },
        error: function (e) {
            console.log(e)
        }
    })
});


/**
 * User admin status
 * */

$("body").on("change", ".isAdmin", function () {
        var result = confirm("Do you want to change admin status for user?");
        if (!result){
            return;
        }
        data =  JSON.stringify({
            "admin": $(this)[0].checked,
            "id": parseInt($($(this).closest("tr").find(".userid")[0]).text(),10)
        });console.log(data);
    $.ajax({
            url: "admin/users/makeUserAdmin",
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            success: function (s) {
                $("#curModule").html(s);
            },
            data: data
        });
});


/**
 * sends add product request
 * */
$("body").on("click", "#addProduct",function (e) {
    e.preventDefault();
    var formData = new FormData();

    formData.append("file", $("#file")[0].files[0]);
    formData.append('product', new Blob([JSON.stringify({
        "id": $("#id").val(),
        "name": $("#name").val(),
        "description": $("#description").val(),
        "price": $("#price").val(),
        "weight": $("#weight").val(),
        "volume": $("#volume").val(),
        "amount": $("#amount").val(),
        "minPlayerAmount": $("#minPlayerAmount").val(),
        "maxPlayerAmount": $("#maxPlayerAmount").val(),
        "imageSource": $("#imageSource").val(),
        "category": {
            "id": $("#category").find("option:selected").val(),
            "name": $("#category").find("option:selected").text()
        }
    })], {
        type: "application/json"
    }));

    $.ajax({
        url: '/admin/products/addProduct',
        enctype: 'multipart/form-data',
        type: 'POST',
        processData: false,
        enctype: 'multipart/form-data',
        contentType: false,
        data: formData,
        cache: false,
        success: function () {
            document.location.reload(true);
        },
        error: function (e) {
            console.log(e)
        }
    })
});

/**
 * sends edit product request
 * */
$("body").on("click", "#editProduct", function (e) {
    e.preventDefault();
    var data = JSON.stringify({
        "id": $("#id").val(),
        "name": $("#name").val(),
        "description": $("#description").val(),
        "price": $("#price").val(),
        "weight": $("#weight").val(),
        "volume": $("#volume").val(),
        "amount": $("#amount").val(),
        "minPlayerAmount": $("#minPlayerAmount").val(),
        "maxPlayerAmount": $("#maxPlayerAmount").val(),
        "imageSource": $("#imageSource").val(),
        "category": {
            "id": $("#category").find("option:selected").val(),
            "name": $("#category").find("option:selected").text()
        }
    });

    $.ajax({
        url: '/admin/products/edit',
        type: 'POST',
        data: data,
        contentType: "application/json; charset=utf-8",
        success: function () {
            document.location.reload(true);
        },
        error: function (e) {
            console.log(e)
        }
    })
});

/**
 * sends filter products request
 * */
$("body").on("click", "#applyFilter",function (e) {
    e.preventDefault();
    var data = JSON.stringify({
        "price": $("#priceFilter").val(),
        "minPlayer": $("#minPlayerAmountFilter").val(),
        "maxPlayer": $("#maxPlayerAmountFilter").val(),
        "category_id":  $("#categoryFilter").find("option:selected").val()
    });

    $.ajax({
        url: '/filter',
        type: 'POST',
        data: data,
        accept:"text/html",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            $("#productListDiv").html(response);
        },
        error: function (e) {
            console.log(e)
        }
    })
});

/**
 * changes order status in admin page
 * */
$('body').on("change", ".orderStatusSelect", function(e) {
    var lastRole = $(this).data('lastValue');
    var newRole = $(this).val();
    var newText = $(this).find("option:selected").text();
    if (confirm("Change order's status to " + newText + "?")) {
        $(this).data('lastValue', newRole);
        editStatusOrder(this,newText,newRole);
    }
    else {
        $(this).val(lastRole);
    }
});

function editStatusOrder(obj,sttus,idstatus){

    var id = parseInt($($(obj).closest("tr").find(".orderid")[0]).text(),10);
    console.log(id);
    var statusid = parseInt($($(obj).closest("tr").find(".paymentstatusid")[0]).text(),10);
    var statusVal = $($(obj).closest("tr").find(".paymentstatuss")[0]).text().trim();

    var paymentid = parseInt($($(obj).closest("tr").find(".orderststusid")[0]).text(),10);
    var paymentVal =$($(obj).closest("tr").find(".orderststuss")[0]).text().trim();

    var data ={
        "id": id,
        "paymentStatus": {
            "id": statusid,
            "status": statusVal
        },"orderStatus": {
            "id": idstatus,
            "status": sttus.trim()
        },
        "email": $("#loggedUserEmail").text(),
        "address": $($(obj).closest("tr").find(".address")[0]).text(),
        "amount" : parseInt($($(obj).closest("tr").find(".amount")[0]).text(),10),
        "paymentType": {
            "id" : parseInt($($(obj).closest("tr").find(".paymentTypeid")[0]).text(),10),
            "type" :$($(obj).closest("tr").find(".paymentTypes")[0]).text().trim()
        },"shippingType": {
            "id" : parseInt($($(obj).closest("tr").find(".shippingTypeid")[0]).text(),10),
            "type" : $($(obj).closest("tr").find(".shippingTypes")[0]).text().trim()
        },"totalPrice":  parseInt($($(obj).closest("tr").find(".totalprice")[0]).text(),10),
        "orderProducts" : []
    };

    console.log(data);
    $.ajax({
        url: '/admin/editOrder',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function(data) {
            $("body").html(data);
        },
        error: function (e) {
            console.log(e)
        }
    })

}