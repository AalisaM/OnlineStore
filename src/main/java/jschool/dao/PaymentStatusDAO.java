package jschool.dao;

import jschool.model.PaymentStatus;
import jschool.model.ShippingType;

import java.util.List;

public interface PaymentStatusDAO {

	 PaymentStatus findById(int id);
	 void update(PaymentStatus p);
	 void add(PaymentStatus p);
	 void remove(int id);

	 PaymentStatus getByStatus(String status);

}
