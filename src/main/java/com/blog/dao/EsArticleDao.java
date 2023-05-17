package com.blog.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface EsArticleDao {
    void addCommentsNum(int articleId);
    void subCommentsNum(int articleId);
    void addFavoritesNum(int articleId);
    void subFavoritesNum(int articleId);
    void addCommentOrderNum(int articleId);
}
