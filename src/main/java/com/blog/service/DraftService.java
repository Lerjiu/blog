package com.blog.service;

import com.blog.domain.Draft;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public interface DraftService extends PermissionService {
    void add(Draft draft);
    void delete(int id);
    void update(Draft draft);
    Draft get(int id);
    int getUserDraftNum(int userId);
    List<Draft> getUserPageDrafts(int userId, int currentPage, int pageSize);
    int publishDraft(int id, Date updateTime);
}
