package com.blog.service.impl;

import com.blog.dao.UserDao;
import com.blog.domain.User;
import com.blog.service.RedisService;
import com.blog.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private RedisService redisService;

    public UserServiceImpl(UserDao userDao, RedisService redisService) {
        this.userDao = userDao;
        this.redisService = redisService;
    }

    @Override
    public void register(User user) {
        userDao.add(user);
    }

    @Override
    public int getUserIdByEmailAndPassword(String email, String password) {
        return userDao.getUserIdByEmailAndPassword(email, password);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public void updateInfo(int id, String name, Date birthday, String description, boolean sex) {
        userDao.updateInfo(id, name, birthday, description, sex);
    }

    @Override
    public void updateAvatar(int id, String avatar) {
        userDao.updateAvatar(id, avatar);
    }

    @Override
    public boolean updatePassword(int id, String oldPassword, String newPassword) {
        String password = userDao.getUserById(id).getPassword();
        if (oldPassword.equals(password)) {
            userDao.updatePassword(id, newPassword);
            return true;
        } else {
            return false;
        }
    }

}
