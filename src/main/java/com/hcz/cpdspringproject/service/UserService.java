package com.hcz.cpdspringproject.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hcz.cpdspringproject.dao.UserDao;
import com.hcz.cpdspringproject.pojo.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService
 */
@Service
@Transactional
public class UserService {

    public List<User> getAllUsers() {
        UserDao dao = new UserDao();
        return dao.getAllUsers();
    }

    public User getUser(int userId) {
        return new UserDao().getUserById(userId);
    }

    public int registerNewUser(User user) {
        UserDao dao = new UserDao();
        User userFind = dao.getUserByUsername(user.getUsername());
        if (userFind != null) {
            return 0;
        } else {
            return new UserDao().addNewUser(user);
        }

    }

    public int updateDataUser(User user) {
        return new UserDao().updateUser(user);
    }

    public boolean login(String username, String password, HttpServletRequest request) {
        User user = new UserDao().authentication(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("authUser", user);
            return true;
        } else {
            return false;
        }
    }

    public void deleteUser(int userId) {
        new UserDao().deleteUserById(userId);
    }

    public boolean isAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        return user != null ? true : false;
    }

}