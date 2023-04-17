package com.blog.service.impl;

import com.blog.dao.UserDao;
import com.blog.domain.User;
import com.blog.service.RedisService;
import com.blog.service.UserService;
import org.springframework.stereotype.Service;

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

}
