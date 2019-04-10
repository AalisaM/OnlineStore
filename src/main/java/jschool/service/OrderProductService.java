package jschool.service;

import jschool.model.Cart;
import jschool.model.CartItem;
import jschool.model.Order;
import jschool.model.OrderProduct;

import java.util.List;

public interface OrderProductService {
     OrderProduct getById(int id);
     void update(OrderProduct p);
     void add(OrderProduct p);
     void remove(OrderProduct id);
}
