package jschool.service;

import jschool.model.PaymentType;
import jschool.model.ShippingType;

import java.util.List;

public interface PaymentTypeService {

	 void update(PaymentType p);
	 void add(PaymentType p);
	 void remove(int id);
}
