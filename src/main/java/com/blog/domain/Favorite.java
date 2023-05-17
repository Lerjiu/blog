package com.blog.domain;

public class Favorite {
    private int id;
    private int userId;
    private int articleId;
    private int folderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", userId=" + userId +
                ", articleId=" + articleId +
                ", folderId=" + folderId +
                '}';
    }
}
