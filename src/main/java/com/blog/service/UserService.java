package com.blog.service;

import com.blog.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
public interface UserService {
    void register(User user);
    int getUserIdByEmailAndPassword(String email, String password);
    User getUserById(int id);
    void updateInfo(int id, String name, Date birthday, String description, boolean sex);
    void updateAvatar(int id, String avatar);
    boolean updatePassword(int id, String oldPassword, String newPassword);
}
