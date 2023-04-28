package com.blog.dao;

import com.blog.domain.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDao {
    @Insert("insert into comment(user_id, article_id, content, order_num, time) values(#{userId}, #{articleId}, #{content}, #{orderNum}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Comment comment);
    Comment get(int id);
    List<Comment> getArticleComments(int articleId);
    @Delete("delete from comment where id = #{id}")
    void delete(int id);
    @Delete("delete from comment where article_id = #{articleId}")
    void deleteForArticle(int articleId);
    @Select("select exists(select * from comment where id = #{id} and user_id = #{userId})")
    boolean checkUserComment(int userId, int id);
}
