package com.hcz.cpdspringproject.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcz.cpdspringproject.pojo.Category;

import com.hcz.cpdspringproject.pojo.News;
import com.hcz.cpdspringproject.pojo.User;
import com.hcz.cpdspringproject.service.NewsService;

/**
 * NewsController
 */
@Controller
public class NewsController {

    @Autowired
    NewsService newsService;

    @GetMapping("/news")
    public String getAllNews(Model model) {
        List<News> allNews = newsService.getAllNews();
        model.addAttribute("allNews", allNews);
        return "news";
    }

    @RequestMapping("/news/details")
    public String newsDetail(@RequestParam("news_id") int newsId, Model model) {
        News news = newsService.getNewsById(newsId);
        List<News>recentNews = newsService.getRecentNews();
        if (news != null) {
            model.addAttribute("news", news);
            model.addAttribute("recentNews", recentNews);
            return "newsDetail";
        } else {
            return "error_404";
        }
    }

    @RequestMapping("/admin/all-news")
    public String adminGetAllNews(Model model) {
        List<News> allNews = newsService.getAllNews();
        if (allNews != null) {
            model.addAttribute("allNews", allNews);
            return "admin/newsDashboard";
        } else {
            return "error_404";
        }
    }

    @RequestMapping("/admin/add-news-form")
    public String showAddNewsForm(Model model) {
        News news = new News();
        news.setTitle("test");
        model.addAttribute("news", news);
        return "admin/forms/newsForm";
    }

    @RequestMapping("/admin/edit-news-form")
    public String showEditNewsForm(Model model, @RequestParam("news_id") int newsId) {
        News editNews = newsService.getNewsById(newsId);
        model.addAttribute("editNews", editNews);
        return "admin/forms/newsFormEdit";
    }

    @RequestMapping(value = "/admin/news/update", method = RequestMethod.POST)
    public String adminUpdateNews(@ModelAttribute("editNews") News news) {
        int updateNews = newsService.updateNews(news);
        if (updateNews > 0) {
            return "redirect:/admin/all-news";
        } else {
            return "error_400";
        }
    }

    @RequestMapping(value = "/admin/news", method = RequestMethod.POST)
    public String adminInsertNews(@ModelAttribute("news") News news, HttpServletRequest request) {
        System.out.println(news.getTitle());
        String thumbnail = "default.png";
        news.setThumbnail(thumbnail);
        news.setCategory(new Category(1, "News"));
        news.setDateCreated(new Date());
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("authUser");
        System.out.println(news.getContents());
        news.setUser(user.getUserId());
        int insertNews = newsService.addNews(news);
        if (insertNews > 0) {
            return "redirect:/admin/all-news";
        } else {
            return "error_400";
        }
    }

    @RequestMapping(value = "/admin/news/delete", method = RequestMethod.GET)
    public String adminDeleteNews(@RequestParam("news_id") int newsId) {
        int deletedNews = newsService.deleteNews(newsId);
        if (deletedNews > 0) {
            return "redirect:/admin/all-news";
        } else {
            return "error_400";
        }
    }

}