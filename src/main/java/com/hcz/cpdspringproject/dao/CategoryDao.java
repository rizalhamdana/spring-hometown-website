package com.hcz.cpdspringproject.dao;

import java.util.List;

import com.hcz.cpdspringproject.mapper.CategoryMapper;
import com.hcz.cpdspringproject.pojo.Category;
import com.hcz.cpdspringproject.utils.GeneralUtils;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * CategoryDao
 */
public class CategoryDao {
    private static JdbcTemplate template = (JdbcTemplate) GeneralUtils.getContext().getBean("jdbcTemplate");

    public int getNumberOfCategories() {
        String sql = "select count(*) from category";
        int number = template.queryForObject(sql, Integer.class);
        return number;
    }

    public Category getCategoryById(int categoryId) {
        Category category = null;
        String sql = "select * from category where category_id=?";
        category = template.queryForObject(sql, new CategoryMapper(), categoryId);
        return category;
    }

    public List<Category> getAllCategories() {
        List<Category> allCategories = null;
        String sql = "select * from category where category_id != 1";
        allCategories = template.query(sql, new CategoryMapper());
        return allCategories;
    }

}
