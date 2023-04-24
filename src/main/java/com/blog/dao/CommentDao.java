package com.blog.dao;

import com.blog.domain.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDao {
    @Insert("insert into comment(user_id, article_id, content, order_num, time) values(#{userId}, #{articleId}, #{content}, #{orderNum}, #{time})")
    void add(Comment comment);
    @Select("select * from comment where article_id = #{articleId}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    List<Comment> getComments(int articleId);
    @Delete("delete from comment where id = #{id}")
    void delete(int id);
}
