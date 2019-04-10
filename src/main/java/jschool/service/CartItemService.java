package jschool.service;

import jschool.model.Cart;
import jschool.model.CartItem;
import jschool.validator.Message;

import java.util.List;

public interface CartItemService {
     Message addProductToCart(jschool.validator.Message m, String json);
     Message removeProductFromCart(jschool.validator.Message m, String json);

}
