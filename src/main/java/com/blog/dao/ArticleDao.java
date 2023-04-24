package com.blog.dao;

import com.blog.domain.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ArticleDao {
    @Select("select comments_num from article where id = #{id}")
    int getCommentsNum(int id);
    @Update("update article set comments_num = comments_num + 1 where id = #{id}")
    void addCommentsNum(int id);
    @Update("update article set comments_num = comments_num - 1 where id = #{id}")
    void subCommentsNum(int id);
    @Select("select comment_order_num from article where id = #{id}")
    int getCommentOrderNum(int id);
    @Update("update article set comment_order_num = comment_order_num + 1 where id = #{id}")
    void addCommentOrderNum(int id);
    @Select("select favorites_num from article where id = #{id}")
    int getFavoritesNum(int id);
    @Update("update article set favorites_num = favorites_num + 1 where id = #{id}")
    void addFavoritesNum(int id);
    @Update("update article set favorites_num = favorites_num - 1 where id = #{id}")
    void subFavoritesNum(int id);
    @Insert("insert into article(title, description, content, author, update_time) values(#{title}, #{description}, #{content}, #{author}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Article article);
    @Delete("delete from article where id = #{id}")
    void delete(int id);
    @Update("update article set title = #{title}, description = #{description}, content = #{content}, update_time = #{updateTime} where id = #{id}")
    void update(Article article);
    @Select("select * from article where id = #{id}")
    Article get(int id);
    @Select("select count(*) from article")
    int getArticleNum();
    @Select("select id, title, description, comments_num, favorites_num, author, update_time from article order by id desc limit #{currentNum}, #{pageSize}")
    List<Article> getPageArticles(int currentNum, int pageSize);
    @Select("select count(*) from article where author = #{userId}")
    int getUserArticleNum(int userId);
    @Select("select * from (select id, title, description, content, comments_num, favorites_num, author, update_time from article where author = #{userId}) as user_article order by id desc limit #{currentNum}, #{pageSize}")
    List<Article> getUserPageArticles(int userId, int currentNum, int pageSize);

}
