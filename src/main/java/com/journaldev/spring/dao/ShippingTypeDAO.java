package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.ShippingType;

public interface ShippingTypeDAO {

	public List<ShippingType> listShippingTypes();

	public ShippingType getShippingTypeById(int id);
	public void updateShippingType(ShippingType p);
	public void addShippingType(ShippingType p);
	public void removeShippingType(int id);
}
