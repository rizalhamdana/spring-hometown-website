package com.hcz.cpdspringproject.controllers;

import java.util.List;

import com.hcz.cpdspringproject.pojo.News;
import com.hcz.cpdspringproject.service.NewsService;
import com.hcz.cpdspringproject.service.TravelService;
import com.hcz.cpdspringproject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AppController
 */

@Controller
public class AppController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @Autowired
    private TravelService travelService;

    @RequestMapping("/")
    public String index(Model model) {
        List<News> allNews = newsService.getAllNews();
        model.addAttribute("currentNews", allNews);
        return "index";
    }

    @RequestMapping("/admin")
    public String showAdminDashboard(Model model) {
        int numberOfUsers = userService.getAllUsers().size();
        int numberOfNews = newsService.getAllNews().size();
        int numberOfTravels = travelService.getAllTravels().size();

        model.addAttribute("numUsers", numberOfUsers);
        model.addAttribute("numNews", numberOfNews);
        model.addAttribute("numTravels", numberOfTravels);
        return "admin/index";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }

}