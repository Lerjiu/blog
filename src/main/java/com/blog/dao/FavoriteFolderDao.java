package com.blog.dao;

import com.blog.domain.FavoriteFolder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoriteFolderDao {
    @Insert("insert into favorite_folder(user_id, name) values(#{userId}, #{name})")
    void add(FavoriteFolder favoriteFolder);
    @Select("select * from favorite_folder where user_id = #{userId}")
    List<FavoriteFolder> getFavoriteFolders(int userId);
    @Delete("delete from favorite_folder where id = #{id}")
    void delete(int id);
}
