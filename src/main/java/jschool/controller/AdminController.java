package jschool.controller;

import jschool.dto.OrderDTO;
import jschool.model.OrderStatus;
import jschool.service.OrderService;
import jschool.service.UserService;
import jschool.validator.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller is responsible for admin page some specific actions
 * */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private OrderService orderService;
    private UserService userService;

    @Autowired
    AdminController(UserService userService, OrderService orderService){
        this.userService = userService;
        this.orderService = orderService;
    }
    @GetMapping
    public String account(Model model){
        return "admin";
    }

    /**
     * This method changes status of Order from admin panel and redirects to admin page
     * */
    @RequestMapping(value= "/editOrder", method = RequestMethod.POST)
    public String editOrder(@RequestBody OrderDTO p, Model model, RedirectAttributes redir){
        Message message = this.orderService.updateOrder(p);
        redir.addFlashAttribute("message", message);
        return "redirect:/admin/";
    }


}
