package com.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleDao {
    @Select("select comments_num from article where id = #{id}")
    int getCommentsNum(int id);
}
