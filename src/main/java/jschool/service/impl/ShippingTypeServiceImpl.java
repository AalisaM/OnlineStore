package jschool.service.impl;

import java.util.List;

import jschool.dto.ShippingDTO;
import jschool.model.ShippingType;
import jschool.service.DTOConverterService;
import jschool.service.ShippingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jschool.dao.ShippingTypeDAO;

@Service
public class ShippingTypeServiceImpl implements ShippingTypeService {

	private final ShippingTypeDAO shippingTypeDAO;
	private final DTOConverterService dtoConverterService;

	@Autowired
	public ShippingTypeServiceImpl(DTOConverterService dtoConverterService, ShippingTypeDAO shippingTypeDAO) {
		this.dtoConverterService = dtoConverterService;
		this.shippingTypeDAO = shippingTypeDAO;
	}


	@Override
	@Transactional
	public void add(ShippingDTO p) {
		System.out.println("INN ADD");
		System.out.println(p.getType());
		System.out.println(p.getId());

		ShippingType newObj = new ShippingType();
		newObj.setType(p.getType());
		this.shippingTypeDAO.add(newObj);
	}

	@Override
	@Transactional
	public void update(ShippingDTO p) {
		ShippingType obj = this.shippingTypeDAO.findById(p.getId());
		obj.setType(p.getType());
		this.shippingTypeDAO.update(obj);
	}

	@Override
	public List<ShippingType> list() {
		return this.shippingTypeDAO.list();
	}


	@Override
	@Transactional
	public List<ShippingDTO> listDTO(){
		return this.dtoConverterService.getShippingDTOList(this.list());
	}

	@Override
	@Transactional
	public ShippingType findById(int id) {
		return this.shippingTypeDAO.findById(id);
	}

	@Override
	@Transactional
	public ShippingDTO findByIdDTo(int id){
		return this.dtoConverterService.getShippingDTO(findById(id));
	}
	@Override
	@Transactional
	public void remove(int id) {
		this.shippingTypeDAO.remove(id);
	}

}
