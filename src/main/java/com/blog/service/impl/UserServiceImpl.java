package com.blog.service.impl;

import com.blog.dao.UserDao;
import com.blog.domain.User;
import com.blog.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void register(User user) {
        userDao.add(user);
    }
}
