package com.journaldev.spring;

import com.journaldev.spring.service.ShippingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.ShippingType;

@Controller
public class ShippingTypeController {
	
	private ShippingTypeService shippingTypeService;
	
	@Autowired(required=true)
	@Qualifier(value="shippingTypeService")
	public void setShippingTypeService(ShippingTypeService ps){
		this.shippingTypeService = ps;
	}
	
	@RequestMapping(value = "/shipping", method = RequestMethod.GET)
	public String listShippingTypes(Model model) {
		model.addAttribute("shipping", new ShippingType());
		model.addAttribute("listShippingTypes", this.shippingTypeService.listShippingTypes());
		return "shipping";
	}
	
	//For add and update person both
	@RequestMapping(value= "/shipping/add", method = RequestMethod.POST)
	public String addShippingType(@ModelAttribute("shipping") ShippingType p){
		
		if(p.getId() == 0){
			//new person, add it
			this.shippingTypeService.addShippingType(p);
		}else{
			//existing person, call update
			this.shippingTypeService.updateShippingType(p);
		}
		
		return "redirect:/shipping";
		
	}
	
	@RequestMapping("shipping/remove/{id}")
    public String removeShippingType(@PathVariable("id") int id){
		
        this.shippingTypeService.removeShippingType(id);
        return "redirect:/shipping";
    }
 
    @RequestMapping("shipping/edit/{id}")
    public String editShippingType(@PathVariable("id") int id, Model model){
        model.addAttribute("shipping", this.shippingTypeService.getShippingTypeById(id));
        model.addAttribute("listShippingTypes", this.shippingTypeService.listShippingTypes());
        return "shipping";
    }
	
}
