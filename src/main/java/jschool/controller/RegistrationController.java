package jschool.controller;

import jschool.dto.UserDTO;
import jschool.service.UserService;
import jschool.validator.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private UserDetailsService manager;
    private UserService userService;

    @Autowired
    RegistrationController(UserDetailsService userDetailsService, UserService userService){
        this.userService = userService;
        this.manager = userDetailsService;
    }

    @GetMapping
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    //action create account
    @RequestMapping(value= "/add", method = RequestMethod.POST)
    public String createAccount(@ModelAttribute("user") @Valid UserDTO dto, BindingResult result, Model m, HttpServletRequest request, HttpServletResponse response){
        Message message = new Message();
        this.userService.addUser(message, dto);
        if (!message.getErrors().isEmpty()){
            m.addAttribute("user", dto);
            m.addAttribute("message", message);
            return "registration";
        }else{
            message.getConfirms().add("You successfully registered");
            m.addAttribute("message", message);
            return "catalogue";
        }
    }
}