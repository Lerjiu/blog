package com.blog.service.impl;

import com.blog.dao.ArticleDao;
import com.blog.domain.Article;
import com.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao;

    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public int getCommentsNum(int id) {
        return articleDao.getCommentsNum(id);
    }

    @Override
    public int getFavoritesNum(int id) {
        return articleDao.getFavoritesNum(id);
    }

    @Override
    public void add(Article article) {
        articleDao.add(article);
    }

    @Override
    public void delete(int id) {
        articleDao.delete(id);
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    @Override
    public Article get(int id) {
        return articleDao.get(id);
    }

    @Override
    public int getArticleNum() {
        return articleDao.getArticleNum();
    }

    @Override
    public List<Article> getPageArticles(int currentPage, int pageSize) {
        return articleDao.getPageArticles((currentPage - 1) * pageSize, pageSize);
    }

    @Override
    public int getUserArticleNum(int userId) {
        return articleDao.getUserArticleNum(userId);
    }

    @Override
    public List<Article> getUserPageArticles(int userId, int currentPage, int pageSize) {
        return articleDao.getUserPageArticles(userId, (currentPage - 1) * pageSize, pageSize);
    }
}
