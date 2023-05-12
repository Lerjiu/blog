package com.blog.dao;

import com.blog.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserDao {
    @Insert("insert into user(name, password, email) values(#{name}, #{password}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(User user);
    @Select("select id from user where email = #{email} and password = #{password}")
    int getUserIdByEmailAndPassword(String email, String password);
    User getUserById(int id);
    List<User> getUserByIds(List<Integer> ids);
    @Update("update user set name = #{name}, birthday = #{birthday}, description = #{description}, sex = #{sex} where id = #{id}")
    void updateInfo(int id, String name, Date birthday, String description, boolean sex);
    @Update("update user set avatar = #{avatar} where id = #{id}")
    void updateAvatar(int id, String avatar);
    @Update("update user set password = #{password} where id = #{id}")
    void updatePassword(int id, String password);
    @Update("update user set default_favorite_folder = #{favoriteFolderId} where id = #{id}")
    void addDefaultFavoriteFolder(int id, int favoriteFolderId);
    @Update("update user set article_num = article_num + 1 where id = #{id}")
    void addArticleNum(int id);
    @Update("update user set article_num = article_num - 1 where id = #{id}")
    void subArticleNum(int id);
    @Update("update user set follower_num = follower_num + 1 where id = #{id}")
    void addFollowerNum(int id);
    @Update("update user set follower_num = follower_num - 1 where id = #{id}")
    void subFollowerNum(int id);
    @Update("update user set followed_num = followed_num + 1 where id = #{id}")
    void addFollowedNum(int id);
    @Update("update user set followed_num = followed_num - 1 where id = #{id}")
    void subFollowedNum(int id);
}
