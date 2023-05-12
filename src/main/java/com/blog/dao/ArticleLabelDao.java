package com.blog.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleLabelDao {
    @Insert("insert into article_label(article_id, label_id) values(#{articleId}, #{labelId})")
    void add(int articleId, int labelId);
    @Delete("delete from article_label where article_id = #{articleId}")
    void deleteArticleLabel(int articleId);
    @Select("select count(*) from article_label where label_id = #{labelId}")
    int getLabelArticleNum(int labelId);
    List<Integer> getPageArticleIds(int labelId, int currentNum, int pageSize);
}
