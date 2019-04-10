package jschool.service;

import jschool.model.PaymentStatus;
import jschool.model.ShippingType;

import java.util.List;

public interface PaymentStatusService {
	 void update(PaymentStatus p);
	 void add(PaymentStatus p);
	 void remove(int id);
	
}
