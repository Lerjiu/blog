package com.blog.service.impl;

import com.blog.dao.ArticleThemeDao;
import com.blog.service.ArticleThemeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleThemeServiceImpl implements ArticleThemeService {
    private ArticleThemeDao articleThemeDao;

    public ArticleThemeServiceImpl(ArticleThemeDao articleThemeDao) {
        this.articleThemeDao = articleThemeDao;
    }

    @Override
    public void setArticleTheme(int articleId, int themeId) {
        articleThemeDao.setArticleTheme(articleId, themeId);
    }

    @Override
    public void deleteArticleTheme(int articleId) {
        articleThemeDao.deleteArticleTheme(articleId);
    }

    @Override
    public void updateArticleTheme(int articleId, int themeId) {
        deleteArticleTheme(articleId);
        setArticleTheme(articleId, themeId);
    }

    @Override
    public int getThemeArticleNum(int themeId) {
        return articleThemeDao.getThemeArticleNum(themeId);
    }

    @Override
    public List<Integer> getPageArticleIds(int themeId, int currentPage, int pageSize) {
        return articleThemeDao.getPageArticleIds(themeId, (currentPage - 1) * pageSize, pageSize);
    }
}
