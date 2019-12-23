package com.hcz.cpdspringproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.hcz.cpdspringproject.pojo.Category;
import com.hcz.cpdspringproject.pojo.News;

import org.springframework.jdbc.core.RowMapper;

/**
 * NewsMapper
 */
public class NewsMapper implements RowMapper<News> {

    @Override
    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        News news = new News();
        news.setNewsId(rs.getInt("news_id"));
        news.setTitle(rs.getString("title"));
        news.setContents(rs.getString("contents"));
        news.setThumbnail(rs.getString("thumbnail"));
        Date dateCreated = rs.getDate("date_created");
        news.setDateCreated(dateCreated);
        news.setUser(rs.getInt("user"));
        Category category = new Category();
        category.setCategoryId(rs.getInt("category"));
        category.setName(rs.getString("name"));
        news.setCategory(category);
        return news;
    }

}