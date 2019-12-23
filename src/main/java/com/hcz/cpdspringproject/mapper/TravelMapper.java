package com.hcz.cpdspringproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hcz.cpdspringproject.pojo.Category;
import com.hcz.cpdspringproject.pojo.Travel;

import org.springframework.jdbc.core.RowMapper;

/**
 * TravelMapper
 */
public class TravelMapper implements RowMapper<Travel> {

    @Override
    public Travel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Travel travel = new Travel();
        travel.setTravelId(rs.getInt("travel_id"));
        travel.setTitle(rs.getString("title"));
        travel.setContents(rs.getString("contents"));
        travel.setUser(rs.getInt("user_id"));
        Category category = new Category();
        category.setCategoryId(rs.getInt("category_id"));
        category.setName(rs.getString("name"));
        travel.setCategory(category);
        return travel;
    }

}