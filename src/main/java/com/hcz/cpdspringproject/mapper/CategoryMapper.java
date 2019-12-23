package com.hcz.cpdspringproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hcz.cpdspringproject.pojo.Category;

import org.springframework.jdbc.core.RowMapper;

/**
 * CategoryMapper
 */
public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setCategoryId(rs.getInt("category_id"));
        category.setName(rs.getString("name"));
        return category;
    }

}