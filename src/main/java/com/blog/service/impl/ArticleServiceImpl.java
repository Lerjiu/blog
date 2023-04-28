package com.blog.service.impl;

import com.blog.dao.ArticleDao;
import com.blog.dao.CommentDao;
import com.blog.dao.FavoriteDao;
import com.blog.dao.UserDao;
import com.blog.domain.Article;
import com.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao;
    private CommentDao commentDao;
    private FavoriteDao favoriteDao;
    private UserDao userDao;

    public ArticleServiceImpl(ArticleDao articleDao, CommentDao commentDao, FavoriteDao favoriteDao, UserDao userDao) {
        this.articleDao = articleDao;
        this.commentDao = commentDao;
        this.favoriteDao = favoriteDao;
        this.userDao = userDao;
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
        userDao.addArticleNum(article.getAuthor());
    }

    @Override
    public void delete(int id) {
        commentDao.deleteForArticle(id);
        favoriteDao.deleteForArticle(id);
        articleDao.delete(id);
        userDao.subArticleNum(id);
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

    @Override
    public boolean checkPermission(int userId, int id) {
        return articleDao.checkUserArticle(userId, id);
    }
}
