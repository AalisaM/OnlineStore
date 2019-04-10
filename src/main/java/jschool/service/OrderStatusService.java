package jschool.service;

import jschool.model.OrderStatus;
import jschool.model.PaymentType;
import jschool.model.ShippingType;

import java.util.List;

public interface OrderStatusService {
	 void update(OrderStatus p);
	 void add(OrderStatus p);
	 void remove(int id);
}
