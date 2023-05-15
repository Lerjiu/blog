package com.blog.dao;

import com.blog.domain.Theme;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ThemeDao {
    @Select("select * from theme where id = #{id}")
    Theme get(int id);
}
