package com.hcz.cpdspringproject.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hcz.cpdspringproject.pojo.User;

import com.hcz.cpdspringproject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UserController
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String showLoginPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("authUser");
        if (user != null) {
            return user.getStatus().equalsIgnoreCase("admin") ? "redirect:/admin" : "redirect:/";
        }
        user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authentication(@ModelAttribute("user") User user, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (userService.login(username, password, request)) {
            user = (User) request.getSession().getAttribute("authUser");
            return user.getStatus().equalsIgnoreCase("admin") ? "redirect:/admin" : "redirect:/";
        } else {
            user = new User();
            model.addAttribute("user", user);
            model.addAttribute("error_message", "Invalid username or password");
            return "login";
        }
    }

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

    @RequestMapping(value = "/logout")
    public String logoutAuthUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }

    // User Profile
    @RequestMapping("/user-profile")
    public String userProfile(Model model, @RequestParam("username") String username) {
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        return "userProfile";
    }

    // Admin User Dashboard
    @RequestMapping(value = "/admin/all-users")
    public String getAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "admin/usersDashboard";
    }

    @RequestMapping(value = "/user-profile/update", method = RequestMethod.POST)
    public String updateUser(Model model, @ModelAttribute("user") User user, HttpSession session) {
        int updateUser = userService.updateDataUser(user);
        if (updateUser > 0) {
            User userup = userService.getUser(user.getUsername());
            model.addAttribute("user", userup);
            session.setAttribute("authUser", userup);
            return "redirect:/user-profile?username=" + userup.getUsername();
        } else {
            return "redirect:/";
        }
    }
}