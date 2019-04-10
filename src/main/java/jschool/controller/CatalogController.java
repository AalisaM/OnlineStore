package jschool.controller;
import jschool.service.*;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for  home page and manipulations on it
 * **/
@Controller
@RequestMapping("/")
public class CatalogController {
    private ProductService productService;
    private static final Logger log = Logger.getLogger(UserDetailsServiceImpl.class);
	@Autowired(required=true)
	public void setProductService(ProductService ps) {
		this.productService = ps;
	}

    private CartService cartService;
    @Autowired(required=true)
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping
    public String listProducts(Model model) {
       log.info("list products USER ingo");
        model.addAttribute("curCart", this.cartService.getCurUserCart());
        model.addAttribute("listCategories", this.productService.listCategoryDTO());
        model.addAttribute("listProducts", this.productService.listDTO());
//        return "products";
        return "catalogue";
    }

    @RequestMapping(value= "/filter", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView filterProduct(@RequestBody String json, ModelMap model) {
	    log.info("TRY TO FILTER BY");
	    log.info(json);
        model.addAttribute("listCategories", this.productService.listCategoryDTO());
        try {
            model.addAttribute("listProducts", this.productService.filterDTO(json));
        }catch (Exception e){
            e.printStackTrace();
        }
       // return new ModelAndView("filtered", model);
        return new ModelAndView("filteredProductList", model);
    }

    @RequestMapping("/product/{id}")
    public String showProductProfile(@PathVariable("id") int id, Model model){
        model.addAttribute("listCategories", this.productService.listCategoryDTO());
        model.addAttribute("product", this.productService.findByIdFTO(id));
        return "productProfileTemplate";
    }
}
