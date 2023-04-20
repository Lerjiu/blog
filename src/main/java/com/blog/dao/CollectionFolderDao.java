package com.blog.dao;

import com.blog.domain.CollectionFolder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionFolderDao {
    @Insert("insert into collection_folder(user_id, name) values(#{userId}, #{name})")
    void add(CollectionFolder collectionFolder);
    @Select("select * from collection_folder where user_id = #{userId}")
    List<CollectionFolder> getCollectionFolders(int userId);
    @Delete("delete from collection_folder where id = #{id}")
    void delete(int id);
}
