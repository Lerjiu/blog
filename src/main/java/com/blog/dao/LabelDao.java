package com.blog.dao;

import com.blog.domain.Label;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface LabelDao {
    @Insert("insert into label(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Label label);
    @Select("select * from label where name = #{name}")
    Label get(String name);
}
