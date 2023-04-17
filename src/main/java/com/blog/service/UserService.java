package com.blog.service;

import com.blog.domain.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
    void register(User user);
    int getUserIdByEmailAndPassword(String email, String password);
}
