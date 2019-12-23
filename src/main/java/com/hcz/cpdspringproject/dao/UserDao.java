package com.hcz.cpdspringproject.dao;

import java.util.List;

import com.hcz.cpdspringproject.mapper.UserMapper;
import com.hcz.cpdspringproject.pojo.User;
import com.hcz.cpdspringproject.utils.GeneralUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * UserDao
 */
public class UserDao {

    private static JdbcTemplate template = (JdbcTemplate) GeneralUtils.getContext().getBean("jdbcTemplate");

    public User authentication(String username, String password) {
        User user = null;
        String sql = "select * from user where username = ? and password = ?";
        user = template.queryForObject(sql, new UserMapper(), username, password);
        return user;
    }

    public User getUserById(int userId) {
        User user = null;
        String sql = "select * from user where user_id = ?";
        user = template.queryForObject(sql, new UserMapper(), userId);
        return user;
    }

    public User getUserByUsername(String username) {
        User user = null;
        String sql = "select * from user where username = ?";
        user = template.queryForObject(sql, new UserMapper(), username);
        return user;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = null;
        String sql = "select * from user";
        allUsers = template.query(sql, new UserMapper());
        return allUsers;
    }

    public int addNewUser(User user) {
        String sql = "insert into user values (0,?,?,?,?,?,?,?)";
        int isSuccess = template.update(sql, user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getPassword(), user.getEmail(), user.getStatus(), "default.jpg");
        return isSuccess;
    }

    public int deleteUserById(int userId) {
        String sql = "delete from user where user_id = ?";
        int isSuccess = template.update(sql, userId);
        return isSuccess;
    }

    public int updateUser(User user) {
        String sql = "update user set username=?, first_name=?, last_name=?, password=?, email=?, status=?, profile_pic=? where user_id = "
                + user.getUserId();
        int isSuccess = template.update(sql, user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getPassword(), user.getEmail(), user.getStatus(), user.getProfilePic());
        return isSuccess;
    }

}
