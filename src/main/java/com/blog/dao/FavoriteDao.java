package com.blog.dao;

import com.blog.domain.Favorite;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoriteDao {
    @Insert("insert into favorite(user_id, article_id, folder_id) values(#{userId}, #{articleId}, #{folderId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Favorite collection);
    Favorite get(int id);
    List<Favorite> getFolderFavorites(int folderId);
    List<Favorite> getPageFolderFavorites(int folderId, int currentNum, int pageSize);
    @Delete("delete from favorite where id = #{id}")
    void delete(int id);
    @Delete("delete from favorite where folder_id = #{folderId}")
    void deleteForFolder(int folderId);
    @Delete("delete from favorite where article_id = #{articleId}")
    void deleteForArticle(int articleId);
    @Select("select id from favorite where folder_id = #{folderId} and article_id = #{articleId}")
    Integer checkArticleInFolder(int articleId, int folderId);
    @Select("select exists(select * from favorite where id = #{id} and user_id = #{userId})")
    boolean checkUserFavorite(int userId, int id);
}
