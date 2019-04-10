package jschool.dao;

import jschool.model.PaymentStatus;
import jschool.model.PaymentType;
import jschool.model.ShippingType;

import java.util.List;

public interface PaymentTypeDAO {

	 List<PaymentType> list();

	 PaymentType findById(int id);
	 void update(PaymentType p);
	 void add(PaymentType p);
	 void remove(int id);

	 PaymentType findByName(String name);
}
