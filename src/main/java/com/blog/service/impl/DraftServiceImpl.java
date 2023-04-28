package com.blog.service.impl;

import com.blog.dao.ArticleDao;
import com.blog.dao.DraftDao;
import com.blog.domain.Article;
import com.blog.domain.Draft;
import com.blog.service.DraftService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DraftServiceImpl implements DraftService {
    private DraftDao draftDao;
    private ArticleDao articleDao;

    public DraftServiceImpl(DraftDao draftDao, ArticleDao articleDao) {
        this.draftDao = draftDao;
        this.articleDao = articleDao;
    }

    @Override
    public void add(Draft draft) {
        draftDao.add(draft);
    }

    @Override
    public void delete(int id) {
        draftDao.delete(id);
    }

    @Override
    public void update(Draft draft) {
        draftDao.update(draft);
    }

    @Override
    public Draft get(int id) {
        return draftDao.get(id);
    }

    @Override
    public int getUserDraftNum(int userId) {
        return draftDao.getUserDraftNum(userId);
    }

    @Override
    public List<Draft> getUserPageDrafts(int userId, int currentPage, int pageSize) {
        return draftDao.getUserPageDrafts(userId, (currentPage - 1) * pageSize, pageSize);
    }

    @Override
    public void publishDraft(int id, Date updateTime) {
        Draft draft = draftDao.get(id);
        Article article = Article.getArticleFromDraft(draft);
        article.setUpdateTime(updateTime);
        articleDao.add(article);
        draftDao.delete(id);
    }

    @Override
    public boolean checkPermission(int userId, int id) {
        return draftDao.checkUserDraft(userId, id);
    }
}
