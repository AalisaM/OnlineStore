package com.journaldev.spring;

import com.journaldev.spring.model.ShippingType;
import com.journaldev.spring.model.User;
import com.journaldev.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {

    private UserService userService;

    @Autowired(required=true)
    @Qualifier(value="userService")
    public void setUserService(UserService ps){
        this.userService = ps;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.listUsers());
        return "users";
    }

    //For add and update person both
    @RequestMapping(value= "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User p){

        if(p.getId() == 0){
            //new person, add it
            this.userService.addUser(p);
        }else{
            //existing person, call update
            this.userService.updateUser(p);
        }

        return "redirect:/users";

    }

    @RequestMapping("/users/remove/{id}")
    public String removeUser(@PathVariable("id") int id){

        this.userService.removeUser(id);
        return "redirect:/users";
    }

    @RequestMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers());
        return "users";
    }
}
