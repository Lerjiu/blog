package com.blog.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DraftLabelDao {
    @Insert("insert into draft_label(draft_id, label_id) values(#{draftId}, #{labelId})")
    void add(int draftId, int labelId);
    @Delete("delete from draft_label where draft_id = #{draftId}")
    void deleteDraftLabel(int draftId);
    @Select("select label_id from draft_label where draft_id = #{draftId};")
    List<Integer> getDraftLabelIds(int draftId);
}
