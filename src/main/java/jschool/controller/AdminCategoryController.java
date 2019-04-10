package jschool.controller;

import jschool.dto.CategoryDTO;
import jschool.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminCategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setShippingTypeService(CategoryService ps){
        this.categoryService = ps;
    }


    @RequestMapping(value= "/categories", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String listCategories(Model model) {
        model.addAttribute("category", new CategoryDTO());
        model.addAttribute("listCategories", this.categoryService.list());
        return "categories";
    }

    @RequestMapping(value= "/categories/add", method = RequestMethod.POST)
    public String addCategory(@RequestBody CategoryDTO p){
        this.categoryService.add(p);
        return "redirect:/admin/categories";
    }
    @RequestMapping(value= "/categories/edit", method = RequestMethod.POST)
    public String editCategory(@RequestBody CategoryDTO p){
        this.categoryService.update(p);
        return "redirect:/admin/categories";
    }

    @RequestMapping("categories/remove/{id}")
    public String removeCategory(@PathVariable("id") int id){
        this.categoryService.remove(id);
        return "redirect:/admin/categories";
    }

    @RequestMapping("categories/edit/{id}")
    public String editCategory(@PathVariable("id") int id, Model model){
        model.addAttribute("category", this.categoryService.findById(id));
        model.addAttribute("listCategories", this.categoryService.list());
        return "categories";
    }

}
