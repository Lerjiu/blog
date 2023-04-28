package com.blog.service;

import com.blog.domain.Article;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ArticleService extends PermissionService {
    int getCommentsNum(int id);
    int getFavoritesNum(int id);
    void add(Article article);
    void delete(int id);
    void update(Article article);
    Article get(int id);
    int getArticleNum();
    List<Article> getPageArticles(int currentPage, int pageSize);
    int getUserArticleNum(int userId);
    List<Article> getUserPageArticles(int userId, int currentPage, int pageSize);
}
