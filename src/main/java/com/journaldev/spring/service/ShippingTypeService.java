package com.journaldev.spring.service;

import java.util.List;

import com.journaldev.spring.model.ShippingType;

public interface ShippingTypeService {

	public void addShippingType(ShippingType p);
	public void updateShippingType(ShippingType p);
	public List<ShippingType> listShippingTypes();
	public ShippingType getShippingTypeById(int id);
	public void removeShippingType(int id);
	
}
