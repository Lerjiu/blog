package com.blog.controller.response.data;

import com.blog.domain.Article;
import com.blog.domain.Favorite;

public class FavoriteArticle {
    private Favorite favorite;
    private Article article;

    public Favorite getFavorite() {
        return favorite;
    }

    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public static FavoriteArticle getFavoriteArticle(Favorite favorite, Article article) {
        FavoriteArticle favoriteArticle = new FavoriteArticle();
        favoriteArticle.setFavorite(favorite);
        article.setContent("");
        favoriteArticle.setArticle(article);
        return favoriteArticle;
    }
}
