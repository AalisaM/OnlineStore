package jschool.controller;

import jschool.dto.UserDTO;
import jschool.service.UserService;
import jschool.validator.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This controller is responsible for account manipulations
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    private UserService userService;
    @Autowired(required=true)
    public void setUserService(UserService ps){
        this.userService = ps;
    }

    //action edit account
    @RequestMapping(value= "/edit", method = RequestMethod.POST)
    public String editAccount(@ModelAttribute("user") UserDTO p, Model m){
        System.out.println(p.getBirth());
        this.userService.updateUser(p);
        return "redirect:/account/";
    }

    @GetMapping
    public String account(Model model){
        model.addAttribute("user", this.userService.getCurUserDTO());
        return "account";
    }

    @RequestMapping(value = "/addAddress", method=RequestMethod.POST)
    /*{user_id: , addr_id : , addr_str: }*/
    public ResponseEntity addAddress(@RequestBody String json){
        Message message = new Message();
        this.userService.addAddress(message, json);
        if (message.getErrors().isEmpty()) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/setActiveAddress", method=RequestMethod.POST)
    /*{user_id: , addr_id :}*/
    public ResponseEntity setActiveAddress(@RequestBody String json){
        Message message = new Message();
        this.userService.setActiveAddress(message, json);
        if (message.getErrors().isEmpty()) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/removeAddress", method=RequestMethod.POST)
    /*{user_id: , addr_id : , addr_str: }*/
    public ResponseEntity removeAddress(@RequestBody String json){
        Message message = new Message();
        this.userService.removeAddress(message, json);
        if (message.getErrors().isEmpty()) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/editAddress", method=RequestMethod.POST)
    //{address_id : "a", address_str : "as"}
    public ResponseEntity editAddress(@RequestBody String json){
        Message message = new Message();
        this.userService.editAddress(message, json);
        if (message.getErrors().isEmpty()) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String updateUserPassword(@RequestBody String json, ModelMap m, RedirectAttributes redir) {
        Message message = new Message();
        this.userService.changeUserPassword(message,json);
        if (!message.getErrors().isEmpty()) {
            m.addAttribute("message", message);
            redir.addFlashAttribute("message", message);
            return "redirect:/account/changePassword";
        }
        redir.addFlashAttribute("message", message);
        return "redirect:/account";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView changeUserPassword(@RequestBody String json, ModelMap m)
    {
        return new ModelAndView("changePasswordTemplate", m);
    }
}
