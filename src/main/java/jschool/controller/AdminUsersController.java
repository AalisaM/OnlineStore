package jschool.controller;

import jschool.dto.UserDTO;
import jschool.service.UserService;
import jschool.validator.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * This controller is reponsible for users page in admin.
 * Allow some manipulations with users for admin
 * Eg. make someone manager role.
 * */
@Controller
@RequestMapping("/admin/users")
public class AdminUsersController {

    private UserService userService;

    @Autowired(required=true)
    public void setUserService(UserService ps){
        this.userService = ps;
    }

    //admin
    @GetMapping
    public ModelAndView listUsers(ModelMap model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("listUsers", this.userService.listUsersDTO());
        return new ModelAndView("users", model);
    }

    //admin remove by id
    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){
        this.userService.removeUser(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/makeUserAdmin", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String updateUserPassword(@RequestBody String json, ModelMap m, RedirectAttributes redir){
        Message message = this.userService.changeAdminStatus(new Message(), json);
        redir.addFlashAttribute("message", message);
        return "redirect:/admin/users";

    }
}
