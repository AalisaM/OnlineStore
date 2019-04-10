package jschool.controller;

import jschool.dto.ShippingDTO;
import jschool.service.ShippingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminShippingController {

    private ShippingTypeService shippingTypeService;

    @Autowired
    public void setShippingTypeService(ShippingTypeService ps){
        this.shippingTypeService = ps;
    }

    @RequestMapping(value= "/shipping", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String listShippingTypes(Model model) {
        model.addAttribute("shipping", new ShippingDTO());
        model.addAttribute("listShippingTypes", this.shippingTypeService.listDTO());
        return "shipping";
    }

    @RequestMapping(value= "/shipping/add", method = RequestMethod.POST)
    public String addShippingType(@RequestBody ShippingDTO p){
        this.shippingTypeService.add(p);
        return "redirect:/admin/shipping";
    }

    @RequestMapping(value= "/shipping/edit", method = RequestMethod.POST)
    public String editShippingType(@RequestBody ShippingDTO p){
        this.shippingTypeService.update(p);
        return "redirect:/admin/shipping";
    }

    @RequestMapping("shipping/remove/{id}")
    public String removeShippingType(@PathVariable("id") int id){
        this.shippingTypeService.remove(id);
        return "redirect:/admin/shipping";
    }

    @RequestMapping(value= "/shipping/edit/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String editShippingType(@PathVariable("id") int id, Model model){
        model.addAttribute("shipping", this.shippingTypeService.findByIdDTo(id));
        model.addAttribute("listShippingTypes", this.shippingTypeService.listDTO());
        return "shipping";
    }




}
