package jschool.dao;

import jschool.model.OrderStatus;
import jschool.model.PaymentStatus;
import jschool.model.ShippingType;

import java.util.List;

public interface OrderStatusDAO {

	 List<OrderStatus> list();

	 OrderStatus findById(int id);
	 void update(OrderStatus p);
	 void add(OrderStatus p);
	 void remove(int id);

	 OrderStatus getByStatus(String orderStatus);

}
