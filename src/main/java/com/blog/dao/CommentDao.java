package com.blog.dao;

import com.blog.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentDao {
    @Insert("insert into comment(user_id, article_id, content, order_num, time) values(#{userId}, #{articleId}, #{content}, #{orderNum}, #{time})")
    void add(Comment comment);
    @Select("select * from comment where article_id = #{articleId}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    List<Comment> getComments(int articleId);
}
