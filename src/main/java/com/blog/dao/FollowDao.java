package com.blog.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FollowDao {
    @Insert("insert into follow(follower, followed) values(#{follower}, #{followed})")
    void add(int follower, int followed);
    @Select("select followed from follow where follower = #{follower}")
    List<Integer> getFollowedList(int follower);
    @Delete("delete from follow where follower = #{follower} and followed = #{followed}")
    void delete(int follower, int followed);
    @Select("select exists(select * from follow where id = #{id} and follower = #{userId})")
    boolean checkUserFollow(int userId, int id);
}
