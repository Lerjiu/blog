package com.blog.service.impl;

import com.blog.dao.ArticleThemeDao;
import com.blog.dao.ThemeDao;
import com.blog.domain.Theme;
import com.blog.service.ArticleThemeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleThemeServiceImpl implements ArticleThemeService {
    private ArticleThemeDao articleThemeDao;
    private ThemeDao themeDao;

    public ArticleThemeServiceImpl(ArticleThemeDao articleThemeDao, ThemeDao themeDao) {
        this.articleThemeDao = articleThemeDao;
        this.themeDao = themeDao;
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
    public Theme getArticleTheme(int articleId) {
        int articleThemeId = articleThemeDao.getArticleThemeId(articleId);
        return themeDao.get(articleThemeId);
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
