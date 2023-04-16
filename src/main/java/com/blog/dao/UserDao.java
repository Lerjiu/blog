package com.blog.dao;

import com.blog.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    @Insert("insert into user(name, password, email) values(#{name}, #{password}, #{email})")
    void add(User user);
}
