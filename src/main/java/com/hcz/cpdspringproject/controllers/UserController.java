package com.hcz.cpdspringproject.controllers;

import com.hcz.cpdspringproject.pojo.User;

import com.hcz.cpdspringproject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * UserController
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String showLoginPage(Model model) {
        User formUser = new User();
        model.addAttribute("formUser", formUser);
        return "login";
    }

    // @RequestMapping(value = "/login", method = RequestMethod.POST)
    // public String authentication(@ModelAttribute("formUser") User userForm) {
    // String username = userForm.getUsername();
    // String password = userForm.getPassword();
    // User user = userService.login(username, password);
    // if (user != null) {
    // session.setAttribute("authUser", user);
    // return "admin_dashboard";
    // } else {

    // return "login";
    // }
    // }

    @RequestMapping("/register")
    public String showRegisterPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerNewUser(@ModelAttribute("user") User user) {
        user.setStatus("member");
        int register = userService.registerNewUser(user);
        if (register == 0) {
            return "register";
        }
        return "redirect:/";
    }
}