<a href="/cart">
<i class="fas fa-shopping-cart"></i>
<span id="lblCartCount" class="badge badge-primary">
    ${ ! empty curCart ? curCart.totalAmount + "" : ( ! empty cartExtendedDTO  ?
        (! empty cartExtendedDTO.curCart ? cartExtendedDTO.curCart.totalAmount + "" :
            (! empty cartExtendedDTO.cartAnon ? cartExtendedDTO.cartAnon.totalAmount + "" : "")) : "" )}
</span>
<span id="cartPrice">
     ${ ! empty curCart ? curCart.totalPrice :
             (! empty cartExtendedDTO.curCart ? cartExtendedDTO.curCart.totalPrice :
                     (! empty cartExtendedDTO.cartAnon ? cartExtendedDTO.cartAnon.totalPrice : "")
                     )}
</span>
</a>