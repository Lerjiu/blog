package com.blog.dao;

import com.blog.domain.Favorite;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoriteDao {
    @Insert("insert into favorite(user_id, article_id, folder_id) values(#{userId}, #{articleId}, #{folderId})")
    void add(Favorite collection);
    @Select("select * from favorite where folder_id = #{folderId}")
    List<Favorite> getFavorites(int folderId);
    @Delete("delete from favorite where id = #{id}")
    void delete(int id);
    @Delete("delete from favorite where folder_id = #{folderId}")
    void deleteForFolder(int folderId);
}
