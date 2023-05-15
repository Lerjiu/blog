package com.blog.dao;

import com.blog.domain.Label;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LabelDao {
    @Insert("insert into label(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Label label);
    @Update("update label set article_num = article_num + 1 where id = #{id}")
    void addArticleNum(int id);
    @Update("update label set article_num = article_num - 1 where id = #{id}")
    void subArticleNum(int id);
    Label getById(int id);
    Label getByName(String name);
    List<Label> getHotLabels();
}
