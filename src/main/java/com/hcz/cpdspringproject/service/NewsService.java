package com.hcz.cpdspringproject.service;

import java.util.List;

import com.hcz.cpdspringproject.dao.NewsDao;
import com.hcz.cpdspringproject.pojo.News;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * NewsService
 */
@Service
@Transactional
public class NewsService {

    public News getNewsById(int newsId) {
        return new NewsDao().getNewsById(newsId);
    }

    public List<News>getRecentNews() {
        return new NewsDao().getRecentNews();
    }
    
    public List<News> getAllNews() {
        return new NewsDao().getAllNews();
    }

    public int addNews(News news) {
        return new NewsDao().addNews(news);
    }

    public int updateNews(News news) {
        return new NewsDao().updateNews(news);
    }

    public int deleteNews(int newsId) {
        return new NewsDao().deleteNewsById(newsId);
    }

}