package com.hcz.cpdspringproject.service;

import java.util.List;

import com.hcz.cpdspringproject.dao.CategoryDao;
import com.hcz.cpdspringproject.pojo.Category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CategoryService
 */
@Service
@Transactional
public class CategoryService {

    public List<Category> getAllCategories() {
        return new CategoryDao().getAllCategories();
    }

    public Category getCategoryById(int categoryId) {
        return new CategoryDao().getCategoryById(categoryId);
    }
}