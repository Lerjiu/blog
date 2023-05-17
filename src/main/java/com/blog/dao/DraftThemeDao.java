package com.blog.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DraftThemeDao {
    @Insert("insert into draft_theme(draft_id, theme_id) values(#{draftId}, #{themeId})")
    void setDraftTheme(int draftId, int themeId);
    @Delete("delete from draft_theme where draft_id = #{draftId}")
    void deleteDraftTheme(int draftId);
    @Select("select theme_id from draft_theme where draft_id = #{draftId}")
    int getDraftThemeId(int draftId);
}
