package com.blog.dao;

import com.blog.domain.Draft;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DraftDao {
    @Insert("insert into draft(title, description, content, author, update_time) values(#{title}, #{description}, #{content}, #{author}, #{updateTime})")
    void add(Draft draft);
    @Delete("delete from draft where id = #{id}")
    void delete(int id);
    @Update("update draft set title = #{title}, description = #{description}, content = #{content}, update_time = #{updateTime} where id = #{id}")
    void update(Draft draft);
    Draft get(int id);
    @Select("select count(*) from draft where author = #{userId}")
    int getUserDraftNum(int userId);
    List<Draft> getUserPageDrafts(int userId, int currentNum, int pageSize);
    @Select("select exists(select * from draft where id = #{id} and author = #{userId})")
    boolean checkUserDraft(int userId, int id);
}
