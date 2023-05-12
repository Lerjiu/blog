package com.blog.dao;

import com.blog.domain.FavoriteFolder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoriteFolderDao {
    @Insert("insert into favorite_folder(user_id, name) values(#{userId}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(FavoriteFolder favoriteFolder);
    List<FavoriteFolder> getFavoriteFolders(int userId);
    @Delete("delete from favorite_folder where id = #{id}")
    void delete(int id);
    @Update("update favorite_folder set favorites_num = favorites_num + 1 where id = #{id}")
    void addFavoritesNum(int id);
    @Update("update favorite_folder set favorites_num = favorites_num - 1 where id = #{id}")
    void subFavoritesNum(int id);
    @Select("select exists(select * from favorite_folder where id = #{id} and user_id = #{userId})")
    boolean checkUserFavoriteFolder(int userId, int id);
}
