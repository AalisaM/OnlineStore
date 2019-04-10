package jschool.service;

import java.util.List;

import jschool.dto.ShippingDTO;
import jschool.model.ShippingType;
import org.springframework.transaction.annotation.Transactional;

public interface ShippingTypeService {

	void add(ShippingDTO p);
	void update(ShippingDTO p);
	List<ShippingType> list();
    List<ShippingDTO> listDTO();
    ShippingType findById(int id);
	ShippingDTO findByIdDTo(int id);
	void remove(int id);
	
}
