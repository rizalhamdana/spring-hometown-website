package com.hcz.cpdspringproject.controllers;

import java.util.List;

import com.hcz.cpdspringproject.pojo.News;
import com.hcz.cpdspringproject.service.NewsService;

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

    @RequestMapping("/")
    public String index(Model model) {
        List<News> allNews = newsService.getAllNews();
        model.addAttribute("currentNews", allNews);
        return "index";
    }

    @RequestMapping("/admin")
    public String showAdminDashboard(Model model) {
        return "admin/index";
    }
    
    @RequestMapping("/admin/allPosts")
    public String showPostDashboard(Model model) {
        return "admin/postsDashboard";
    }
    
    @RequestMapping("/newsForm")
    public String showNewsForm(Model model) {
        return "admin/forms/newsForm";
    }
    
    @RequestMapping("/travelForm")
    public String showTravelForm(Model model) {
        return "admin/forms/travelForm";
    }

}