package com.blog.dao;

import com.blog.domain.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionDao {
    @Insert("insert into collection(user_id, article_id, folder_id) values(#{userId}, #{articleId}, #{folderId})")
    void add(Collection collection);
    @Select("select * from collection where folder_id = #{folderId}")
    List<Collection> getCollections(int folderId);
    @Delete("delete from collection where id = #{id}")
    void delete(int id);
}
