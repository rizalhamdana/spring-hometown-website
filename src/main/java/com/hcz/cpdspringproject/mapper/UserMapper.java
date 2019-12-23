package com.hcz.cpdspringproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hcz.cpdspringproject.pojo.User;

import org.springframework.jdbc.core.RowMapper;

/**
 * UserMapper
 */
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setStatus(rs.getString("status"));
        user.setProfilePic(rs.getString("profile_pic"));
        return user;
    }

}