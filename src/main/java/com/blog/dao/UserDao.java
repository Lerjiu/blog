package com.blog.dao;

import com.blog.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;

@Mapper
public interface UserDao {
    @Insert("insert into user(name, password, email) values(#{name}, #{password}, #{email})")
    void add(User user);
    @Select("select id from user where email = #{email} and password = #{password}")
    int getUserIdByEmailAndPassword(String email, String password);
    @Select("select * from user where id = #{id}")
    User getUserById(int id);
    @Update("update user set name = #{name}, birthday = #{birthday}, description = #{description}, sex = #{sex} where id = #{id}")
    void updateInfo(int id, String name, Date birthday, String description, boolean sex);
    @Update("update user set avatar = #{avatar} where id = #{id}")
    void updateAvatar(int id, String avatar);
    @Update("update user set password = #{password} where id = #{id}")
    void updatePassword(int id, String password);
}
