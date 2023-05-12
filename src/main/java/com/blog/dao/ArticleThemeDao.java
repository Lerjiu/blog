package com.blog.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface ArticleThemeDao {
    @Insert("insert into article_theme(article_id, theme_id) values(#{articleId}, #{themeId})")
    void setArticleTheme(int articleId, int themeId);
    @Delete("delete from article_theme where article_id = #{articleId}")
    void deleteArticleTheme(int articleId);
    @Select("select count(*) from article_theme where theme_id = #{themeId}")
    int getThemeArticleNum(int themeId);
    List<Integer> getPageArticleIds(int themeId, int currentNum, int pageSize);
}
