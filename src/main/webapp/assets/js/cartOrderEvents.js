/**
 * creates cart or gets it from cookie for anonymus
 * */
window.onload = function() {
    if (!$("#loggedUserName").length) {
        var jsonCart = getCartFromCookie();
        $("#lblCartCount").text((isNaN(jsonCart["totalAmount"]) ? 0 : jsonCart["totalAmount"]));
        $("#cartPrice").text(jsonCart["totalPrice"]);
    }
};

/**
 * saves cookie in btoa string for cookie visibility in controller:
 * as i need json,but backend doesnot see stringified json in cookies
 * decided to store it in btoa page
 * */
var createCookie = function(name, value, days) {
    var expires;
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toGMTString();
    }
    else {
        expires = "";
    }
    document.cookie = name + "=" + btoa(value) + expires + "; path=/";
};

/**
 * gets cookie item by name*/
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) {
                c_end = document.cookie.length;
            }
            return atob(unescape(document.cookie.substring(c_start, c_end)));
           // return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";
}

/***
 * sets new cart in cookie
 * */
function setCartForCookie(){
    var jsonCart = {};
    jsonCart["totalAmount"] = parseInt($("#lblCartCount").text().trim(),10);
    var price = parseInt($("#cartPrice").text().trim(),10);
    jsonCart["totalPrice"] = ((isNaN(price) || price  == null) ? 0 : price) ;

    jsonCart["cartItem"] = {}
    jsonCart["cartItemArr"] = [];
    createCookie("cartItem", JSON.stringify(jsonCart), 28)
}

/**
 * increments product item in cart
 * */
function incProductById(id,name,price, jsonCart){
    jsonCart["totalAmount"] +=1;
    jsonCart["totalPrice"] += price;
    var p = jsonCart["cartItem"][id]["price"];
    var a = jsonCart["cartItem"][id]["amount"];
    jsonCart["cartItem"][id] = {"product_id" : id, "product_name" : name, "amount" : a + 1, "price" : p + price};
    jsonCart["cartItemArr"] = [];
    $.each(jsonCart["cartItem"],function(k,v){jsonCart["cartItemArr"].push(v)});
    $("#lblCartCount").text( jsonCart["totalAmount"]);
    $("#cartPrice").text(jsonCart["totalPrice"]);
    return jsonCart;
}

/**
 * decriments product in cart
 * **/
function decProductById(id,name,price, jsonCart){
     console.log(id);

    console.log(name);
    console.log(price);
    console.log(jsonCart);

    var p = jsonCart["cartItem"][id]["price"];
    var a = jsonCart["cartItem"][id]["amount"];
    if (a == 1){
        removeProductAtAll(id,jsonCart);
        //delete jsonCart["cartItem"][id];
        return jsonCart;
    }else{
        jsonCart["totalAmount"] -=1;
        jsonCart["totalPrice"] -= price;

        jsonCart["cartItem"][id] = {"product_id" : id, "product_name" : name, "amount" : a - 1, "price" : p - price};
        jsonCart["cartItemArr"] = [];
        $.each(jsonCart["cartItem"],function(k,v){jsonCart["cartItemArr"].push(v)});
        $("#lblCartCount").text( jsonCart["totalAmount"]);
        $("#cartPrice").text(jsonCart["totalPrice"]);
        console.log(jsonCart);
        return jsonCart;
    }
}

/**
 * removes product from cart
 * */
function removeProductAtAll(id, jsonCart){
    console.log(id);
    console.log(jsonCart);

    var p = jsonCart["cartItem"][id]["price"];
    var a = jsonCart["cartItem"][id]["amount"];
    console.log(p);
    console.log(a);

    jsonCart["totalAmount"] -= a;
    jsonCart["totalPrice"] -= p;
    delete jsonCart["cartItem"][id];
    jsonCart["cartItemArr"] = [];
    $.each(jsonCart["cartItem"],function(k,v){jsonCart["cartItemArr"].push(v)});
    console.log(jsonCart);
    $("#lblCartCount").text( jsonCart["totalAmount"]);
    $("#cartPrice").text(jsonCart["totalPrice"]);
    return jsonCart;

}
/**
 * adds product to cart
 * */
function addNewProduct(id,name,price, jsonCart){
    jsonCart["totalAmount"] +=1;
    jsonCart["totalPrice"] += price;
    jsonCart["cartItem"][id] = {"product_id" : id, "product_name" : name, "amount" : 1, "price" : price};
    jsonCart["cartItemArr"] = [];
    $.each(jsonCart["cartItem"],function(k,v){jsonCart["cartItemArr"].push(v)});
    $("#lblCartCount").text( jsonCart["totalAmount"]);
    $("#cartPrice").text(jsonCart["totalPrice"]);
    return jsonCart;
}

/**
 * returns cart from cookie
 * */
function getCartFromCookie(){
    var cookieRaw = getCookie("cartItem");

    if (cookieRaw.length){
        return JSON.parse(getCookie("cartItem"));
    }else {
        var jsonCart = {};
        jsonCart["totalAmount"] = parseInt($("#lblCartCount").text().trim(),10);
        var price = parseInt($("#cartPrice").text().trim(),10);
        jsonCart["totalPrice"] = ((isNaN(price) || price  == null) ? 0 : price) ;
        jsonCart["cartItem"] = {};
        jsonCart["cartItemArr"] = [];
        createCookie("cartItem", JSON.stringify(jsonCart), 28);
        return jsonCart;
    }
}

/**
 * clears cookie
 * */
function clearecookie(){
    javascript:(function(){document.cookie.split(";").forEach(function(c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); }); })();
}

/**
 * processes increment product event : adds or edit
 * */
function plusAnonProduct(obj){
    var id = $($(obj).closest("div.card-body").find(".productid")[0]).text();
    var pn = $($(obj).closest("div.card-body").find(".productname")[0]).text();
    var pp = parseInt($($(obj).closest("div.card-body").find(".productprice")[0]).text(),10);
    var jsonCart = getCartFromCookie();
    if (jsonCart["cartItem"][id] == null){
        jsonCart = addNewProduct(id,pn,pp,jsonCart);
    }else{
        jsonCart = incProductById(id,pn,pp,jsonCart);
    }

    createCookie("cartItem", JSON.stringify(jsonCart), 28);
}

/**
 * processes decriment product event:
 * */
function minusAnonProduct(obj){
    var id = $($(obj).closest("div.card-body").find(".productid")[0]).text();
    var pn = $($(obj).closest("div.card-body").find(".productname")[0]).text();
    var pp = parseInt($($(obj).closest("div.card-body").find(".productprice")[0]).text(),10);
    var jsonCart = getCartFromCookie();
    if (jsonCart["cartItem"][id] == null){
        return;
    }else{
        jsonCart = decProductById(id,pn,pp,jsonCart);
    }
    createCookie("cartItem", JSON.stringify(jsonCart), 28);
}
/**
 * sends cart html update request
 * */
function sendCartPageRequest(){
    $.ajax({
        url: '/cart/processAnonymousCart',
        type: 'POST',
        data: getCookie("cartItem"),
        contentType0: "application/json; charset=utf-8",
        success: function (response) {
            $("#currentCart").html(response);
        },
        error: function (e) {
            console.log(e)
        }})
}

/**
 * adds product to cart for user
 * */
$("body").on("click",".addToCart", function (e) {
    console.log("add to cart");
    e.preventDefault();
    //todo
    if ($("#loggedUserName").length){
        console.log("logged");

        var data = JSON.stringify({
            "id": $($(this).closest("div.card-body").find(".productid")[0]).text()
        });

        $.ajax({
            url: '/cart/addToCart/',
            type: 'POST',
            data: data,
            accept:"text/html",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                $("#cartTemplate").html(response);
            },
            error: function (e) {
                console.log(e)
            }
        })
    }else{
        console.log("try to add notlogged");
        console.log($(this)[0]);
        plusAnonProduct(this);
    }
});

/**
 * removes product from cart
 * */
$("body").on("click", ".removeFromCart",function (e) {
    e.preventDefault();
    //todo
    if ($("#loggedUserName").length){
        var data = JSON.stringify({
            "id": $($(this).closest("div.card-body").find(".productid")[0]).text(),
            "amount" : 1
        });

        $.ajax({
            url: '/cart/removeFromCart',
            type: 'POST',
            data: data,
            accept:"text/html",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                $("#cartTemplate").html(response);
            },
            error: function (e) {
                console.log(e)
            }
        })
    }else{
        minusAnonProduct(this);
    }
});

/**
 * prccesses dec event in cart page
 * */
$("body").on("click",".cartDecItem",function(){
    if ($("#loggedUserName").length){
        var data = JSON.stringify({
            "id": $($(this).closest("tr").find(".productid")[0]).text(),
            "amount" : 1
        });

        $.ajax({
            url: '/cart/removeFromCart',
            type: 'POST',
            data: data,
            accept:"text/html",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                $("#cartTemplate").html(response);
                sendCartPageRequest();

            },
            error: function (e) {
                console.log(e)
            }
        })
    }
});
/**
 * prccesses inc event in cart page
 * */
$("body").on("click",".cartIncItem",function(){
    if ($("#loggedUserName").length){
        console.log("logged");

        var data = JSON.stringify({
            "id": $($(this).closest("tr").find(".productid")[0]).text()
        });

        $.ajax({
            url: '/cart/addToCart/',
            type: 'POST',
            data: data,
            accept:"text/html",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                $("#cartTemplate").html(response);
                sendCartPageRequest();
            },
            error: function (e) {
                console.log(e)
            }
        })
    }
});

/**
 * prccesses delete event in cart page
 * */

$("body").on("click",".deleteCartItem",function(){
    if ($("#loggedUserName").length){
        var data = JSON.stringify({
            "id": $($(this).closest("tr").find(".productid")[0]).text(),
            "amount" :  $($(this).closest("tr").find("td.amount")[0]).text()
        });
        console.log(data);
        $.ajax({
            url: '/cart/removeFromCart',
            type: 'POST',
            data: data,
            accept:"text/html",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                $("#cartTemplate").html(response);
                sendCartPageRequest();

            },
            error: function (e) {
                console.log(e)
            }
        })
    }else {
        var jsonCart = removeProductAtAll($($(this).closest("tr").find(".productid")[0]).text(), getCartFromCookie());
        createCookie("cartItem", JSON.stringify(jsonCart), 28);
        sendCartPageRequest();
    }
});

/**
 * prccesses dec event in cart page for anon user
 * */

$("body").on("click",".cartDecItemAnon",function(){
    var id = $($(this).closest("tr").find(".productid")[0]).text();
    var pn = $($(this).closest("tr").find("td.productname")[0]).text();
    var amountObj = parseInt($($(this).closest("tr").find("td.amount")[0]).text(),10);
    var pp = parseInt($($(this).closest("tr").find("td.productprice")[0]).text(),10) / amountObj;
    var jsonCart = getCartFromCookie();
    if (jsonCart["cartItem"][id] == null){
        return;
    }else{
        jsonCart = decProductById(id,pn,pp,jsonCart);
    }
    createCookie("cartItem", JSON.stringify(jsonCart), 28);
    sendCartPageRequest();
});

/**
 * prccesses inc event in cart page for anon
 * */
$("body").on("click",".cartIncItemAnon",function(){
    var id = $($(this).closest("tr").find(".productid")[0]).text();
    var pn = $($(this).closest("tr").find("td.productname")[0]).text();
    var amountObj = parseInt($($(this).closest("tr").find("td.amount")[0]).text(),10);
    var pp = parseInt($($(this).closest("tr").find("td.productprice")[0]).text(),10) / amountObj;
    var jsonCart = getCartFromCookie();
    if (jsonCart["cartItem"][id] == null){
        jsonCart = addNewProduct(id,pn,pp,jsonCart);
    }else{
        jsonCart = incProductById(id,pn,pp,jsonCart);
    }
    createCookie("cartItem", JSON.stringify(jsonCart), 28);
    sendCartPageRequest();
});

/**
 * sends make order request with order dto
 * */
$("body").on("click", "#makeOrder",function (e) {

    var data ={
        "id": 0,
        "email": $("#userEmail").text(),
        "address": $("#address").val(),
        "amount" : parseInt($("#lblCartCount").text(),10),
        "paymentType": {
            "id" :  parseInt($("#payment").find("option:selected").val(),10),
            "type" : $("#payment").find("option:selected").text()
        },"paymentStatus": {
            "id": 1,
            "status": "not paid"
        },"shippingType": {
            "id" :  parseInt($("#shipping").find("option:selected").val(),10),
            "type" : $("#shipping").find("option:selected").text()
        },"orderStatus": {
            "id": 1,
            "status": "not paid"
        },"totalPrice":  parseInt($("#cartPrice").text().trim(),10),
        "orderProducts" : []
    };
    $("tr.productItem").each(function() {
        var productid = parseInt($($(this).find(".productid")[0]).text(),10);
        var amount = parseInt($($(this).find(".amount")[0]).text(),10);
        var price = parseInt($($(this).find(".productprice")[0]).text(),10);
        var productName = $($(this).find(".productname")[0]).text();
        var obj = {"productid" : productid, "amount" : amount, "price" : price, "productName" : productName}
        data["orderProducts"].push(obj);
    });

    $.ajax({
        url: '/cart/makeOrder',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (s) {
            $("body").html(s);
           // document.location.reload(true);
        },
        error: function (e) {
            console.log(data);
            console.log(e)
        }
    })
});

/**
 * prccesses cancel order request
 * */
$("body").on("click", "#cancel",function (e) {

    var data ={
        "id": parseInt($("#orderID").text(),10),
        "email": $("#userEmail").text(),
        "address": $("#address").text(),
        "amount" : parseInt($("#lblCartCount").text(),10),
        "paymentType": {
            "id" : parseInt($("#paymentid").text(),10),
            "type" : $("#payment").text()
        },"paymentStatus": {
            "id": parseInt($("#statusid").text(),10),
            "status": $("#status").text()
        },"shippingType": {
            "id" : parseInt($("#shippingid").text(),10),
            "type" : $("#shipping").text()
        },"orderStatus": {
            "id": parseInt($("#orderstatusid").text(),10),
            "status": $("#orderstatus").text()
        },"totalPrice": $("#cartPrice").text(),
        "orderProducts" : []
    };
    $("tr.productItem").each(function( index ) {
        var productid = parseInt($($(this).find(".productid")[0]).text(),10);
        var amount = parseInt($($(this).find(".amount")[0]).text(),10);
        var price = parseInt($($(this).find(".productprice")[0]).text(),10);
        var productName = $($(this).find(".productname")[0]).text();
        var obj = {"productid" : productid, "amount" : amount, "price" : price, "productName" : productName}
        data["orderProducts"].push(obj);
    });

    $.ajax({
        url: '/cart/cancelOrder',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function(data, textStatus) {
            if (data.redirect) {
                window.location.href = data.redirect;
            }else {
                $("body").html(data);
            }
        },
        error: function (e) {
            console.log(e)
        }
    })
});

/**
 * prccesses approve order event in cart page
 * */

$("body").on("click", "#approve",function (e) {

    var data ={
        "id": parseInt($("#orderID").text(),10),
        "email": $("#userEmail").text(),
        "address": $("#address").text(),
        "amount" : parseInt($("#lblCartCountDTO").text(),10),
        "paymentType": {
            "id" : parseInt($("#paymentid").text(),10),
            "type" : $("#payment").text()
        },"paymentStatus": {
            "id": parseInt($("#statusid").text(),10),
            "status": $("#status").text()
        },"shippingType": {
            "id" : parseInt($("#shippingid").text(),10),
            "type" : $("#shipping").text()
        },"orderStatus": {
            "id": 3,
            "status": "waiting for delivery"
        },"totalPrice": $("#cartPriceDTO").text(),
        "orderProducts" : []
    };
    $("tr.productItem").each(function( index ) {
        var productid = parseInt($($(this).find(".productid")[0]).text(),10);
        var amount = parseInt($($(this).find(".amount")[0]).text(),10);
        var price = parseInt($($(this).find(".productprice")[0]).text(),10);
        var productName = $($(this).find(".productname")[0]).text();
        var obj = {"productid" : productid, "amount" : amount, "price" : price, "productName" : productName}
        data["orderProducts"].push(obj);
    });

    $.ajax({
        url: '/cart/editOrder',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function(data, textStatus) {
            if (data.redirect) {
                window.location.href = data.redirect;
            }else {
                $("body").html(data);
            }
        },
        error: function (e) {
            console.log(e)
        }
    })
});
/**
 * prccesses payment event in cart page
 * */
$("body").on("click", "#pay",function (e) {
    var result = confirm("Do you want to pay for this?");
    if (!result){
        return;
    }
    $("#status").text("paid");
    $("#statusid").text(2);
    var data ={
        "id": parseInt($("#orderID").text(),10),
        "email": $("#userEmail").text(),
        "address": $("#address").text(),
        "amount" : parseInt($("#lblCartCountDTO").text(),10),
        "paymentType": {
            "id" : parseInt($("#paymentid").text(),10),
            "type" : $("#payment").text()
        },"paymentStatus": {
            "id": parseInt($("#statusid").text(),10),
            "status": $("#status").text()
        },"shippingType": {
            "id" : parseInt($("#shippingid").text(),10),
            "type" : $("#shipping").text()
        },"orderStatus": {
            "id": 3,
            "status": "waiting for delivery"
        },"totalPrice": $("#cartPriceDTO").text(),
        "orderProducts" : []
    };
    $("tr.productItem").each(function( index ) {
        var productid = parseInt($($(this).find(".productid")[0]).text(),10);
        var amount = parseInt($($(this).find(".amount")[0]).text(),10);
        var price = parseInt($($(this).find(".productprice")[0]).text(),10);
        var productName = $($(this).find(".productname")[0]).text();
        var obj = {"productid" : productid, "amount" : amount, "price" : price, "productName" : productName}
        data["orderProducts"].push(obj);
    });

    $.ajax({
        url: '/cart/editOrder',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function(data, textStatus) {
            if (data.redirect) {
                window.location.href = data.redirect;
            }else {
                $("body").html(data);
            }
        },
        error: function (e) {
            console.log(e)
        }
    })
});

/**
* calls warning on order page
* */
$("body").on("click", "input", function() {
    $("input").click(function () {
        if ($(this).is('[readonly]')) {
            alert("If you want to change account data, please visit account page")
        }
    })
});

    /*﻿var formData = new FormData();

    formData.append("file", $("#file")[0].files[0]);
    formData.append('product', new Blob([JSON.stringify( {"id": $("#id").val(),
                        "name":  $("#name").val(),
                        "description" : $("#description").val(),
                        "price" : $("#price").val(),
                        "weight" : $("#weight").val(),
                        "volume" : $("#volume").val(),
                        "amount" : $("#amount").val(),
                        "minPlayerAmount" : $("#minPlayerAmount").val(),
                        "maxPlayerAmount" : $("#maxPlayerAmount").val(),
                        "imageSource" :$("#imageSource").val(),
                        "category" : {
                            "id" : $("#category").find("option:selected").val(),
                            "name" : $("#category").find("option:selected").text()
                        }
                    })], {
                    type: "application/json"
                }));

     $.ajax({
                url: '/products/addProduct',
                 enctype: 'multipart/form-data',
                type: 'POST',
                processData:false,
                enctype: 'multipart/form-data',
                contentType: false,
                data: formData,
                 cache: false,
                success: function () {
                    document.location.reload(true);
                },
                error :function(e){console.log(e)}
            })
    */