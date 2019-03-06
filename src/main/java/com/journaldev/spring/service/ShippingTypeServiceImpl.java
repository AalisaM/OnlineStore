package com.journaldev.spring.service;

import java.util.List;

import com.journaldev.spring.model.ShippingType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journaldev.spring.dao.ShippingTypeDAO;

@Service
public class ShippingTypeServiceImpl implements ShippingTypeService {
	
	private ShippingTypeDAO shippingTypeDAO;

	public void setShippingTypeDAO(ShippingTypeDAO shippingTypeDAO) {
		this.shippingTypeDAO = shippingTypeDAO;
	}

	@Override
	@Transactional
	public void addShippingType(ShippingType p) {
		this.shippingTypeDAO.addShippingType(p);
	}

	@Override
	@Transactional
	public void updateShippingType(ShippingType p) {
		this.shippingTypeDAO.updateShippingType(p);
	}

	@Override
	@Transactional
	public List<ShippingType> listShippingTypes() {
		return this.shippingTypeDAO.listShippingTypes();
	}

	@Override
	@Transactional
	public ShippingType getShippingTypeById(int id) {
		return this.shippingTypeDAO.getShippingTypeById(id);
	}

	@Override
	@Transactional
	public void removeShippingType(int id) {
		this.shippingTypeDAO.removeShippingType(id);
	}

}
