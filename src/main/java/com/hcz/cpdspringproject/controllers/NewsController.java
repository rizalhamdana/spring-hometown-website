package com.hcz.cpdspringproject.controllers;

import java.util.List;

import com.hcz.cpdspringproject.pojo.News;
import com.hcz.cpdspringproject.service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * NewsController
 */
@Controller
public class NewsController {
    @Autowired
    NewsService newsService;

    @RequestMapping("/news")
    public String getAllNews(Model model) {
        List<News> allNews = newsService.getAllNews();
        model.addAttribute("allNews", allNews);
        return "news";
    }

    @RequestMapping("/news/details")
    public String newsDetail(@RequestParam("news_id") int newsId, Model model) {
        News news = newsService.getNewsById(newsId);
        if (news != null) {
            model.addAttribute("news", news);
            return "news_detail";
        } else {
            return "error_404";
        }
    }

    @RequestMapping("/admin/all-news")
    public String adminGetAllNews(Model model) {
        List<News> allNews = newsService.getAllNews();
        if (allNews != null) {
            model.addAttribute("allNews", allNews);
            return "admin_news";
        } else {
            return "error_404";
        }
    }

    @RequestMapping("/admin/add-news")
    public String showAddNewsForm(Model model) {
        News news = new News();
        model.addAttribute("news", news);
        return "admin_add_news_form";
    }

    @RequestMapping(value = "/admin/news", method = RequestMethod.GET)
    public String adminDetailNews(@RequestParam("news_id") int newsId, Model model) {
        News news = newsService.getNewsById(newsId);
        if (news != null) {
            model.addAttribute("news", news);
            return "admin_update_news_form";
        } else {
            return "error_404";
        }
    }

    @RequestMapping(value = "/admin/news", method = RequestMethod.PUT)
    public String adminUpdateNews(@ModelAttribute("news") News news) {
        int updateNews = newsService.updateNews(news);
        if (updateNews > 0) {
            return "redirect:/admin/all-news";
        } else {
            return "error_400";
        }
    }

    @RequestMapping(value = "/admin/news", method = RequestMethod.POST)
    public String adminInsertNews(@ModelAttribute("news") News news) {
        int insertNews = newsService.addNews(news);
        if (insertNews > 0) {
            return "redirect:admin/news";
        } else {
            return "error_400";
        }
    }

    @RequestMapping(value = "/admin/news", method = RequestMethod.DELETE)
    public String adminDeleteNews(@RequestParam("news_id") int newsId) {
        int deletedNews = newsService.deleteNews(newsId);
        if (deletedNews > 0) {
            return "redirect:admin/news";
        } else {
            return "error_400";
        }
    }
}