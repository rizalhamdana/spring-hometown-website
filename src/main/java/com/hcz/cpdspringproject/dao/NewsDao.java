package com.hcz.cpdspringproject.dao;

import java.util.List;

import com.hcz.cpdspringproject.mapper.NewsMapper;
import com.hcz.cpdspringproject.pojo.News;
import com.hcz.cpdspringproject.utils.GeneralUtils;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * NewsDao
 */
public class NewsDao {
    private static JdbcTemplate template = (JdbcTemplate) GeneralUtils.getContext().getBean("jdbcTemplate");

    public News getNewsById(final int newsId) {
        final String sql = "select * from news join category on news.category = category.category_id order by news.news_id desc where news_id = ? ";
        return template.queryForObject(sql, new NewsMapper(), newsId);
    }

    public List<News> getAllNews() {
        String sql = "select * from news join category on news.category = category.category_id order by news.news_id desc";
        return template.query(sql, new NewsMapper());
    }

    public int addNews(News news) {
        String sql = "insert into news values(null, ?, ?, ?, ?, ?, ?)";
        return template.update(sql, news.getTitle(), news.getContents(), news.getThumbnail(),
                new java.sql.Date(news.getDateCreated().getTime()), news.getUser(), news.getCategory().getCategoryId());
    }

    public int updateNews(News news) {
        String sql = "update news set title = ?, contents = ?, thumbnail = ?, date_created = ?, user = ?, category = ? where news_id = "
                + news.getNewsId();
        return template.update(sql, news.getTitle(), news.getContents(), news.getThumbnail(),
                new java.sql.Date(news.getDateCreated().getTime()), news.getUser(), news.getCategory().getCategoryId());
    }

    public int deleteNewsById(int newsId) {
        String sql = "delete from news where news_id = ?";
        return template.update(sql, newsId);
    }

}
