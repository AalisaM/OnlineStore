package jschool.controller;

import jschool.dto.ProductDTO;
import jschool.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService ps) {
        this.productService = ps;
    }

    @RequestMapping(value= "/products", method = RequestMethod.GET)
    public String createProductPage(Model model){
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("productList", this.productService.listDTO());
        model.addAttribute("listCategories", this.productService.listCategoryDTO());
        return "adminproducts";
    }

    @RequestMapping(value= "/products/add", method = RequestMethod.POST)
    public String addProduct(@RequestBody ProductDTO p){
        this.productService.add(p);
        return "redirect:/admin/products";
    }

    @RequestMapping(value= "/products/edit", method = RequestMethod.POST)
    public String editProduct(@RequestBody ProductDTO p){
        this.productService.update(p);
        return "redirect:/admin/products";
    }

    @RequestMapping("products/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("productList", this.productService.listDTO());
        model.addAttribute("listCategories", this.productService.listCategoryDTO());
        model.addAttribute("product", this.productService.findByIdFTO(id));
        return "adminproducts";
    }

    @RequestMapping(value= "/products/addProduct", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public String addProduct(@RequestPart("product") ProductDTO p, @RequestPart("file") MultipartFile file){
        this.productService.addWithImage(p, file);
        return "redirect:/admin/products";
    }

    @RequestMapping("products/remove/{id}")
    public String removeProduct(@PathVariable("id") int id){
        this.productService.remove(id);
        return "redirect:/admin/products";
    }


}
