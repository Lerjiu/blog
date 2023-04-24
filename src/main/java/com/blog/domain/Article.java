package com.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(indexName = "articles")
public class Article {
    public static final int DESCRIPTION_MAX_LENGTH = 200;
    private int id;
    private String title;
    private String description;
    private String content;
    private int commentsNum;
    private int favoritesNum;
    private int author;
    private boolean status; // 0草稿 1已发布
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private int commentOrderNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(int commentsNum) {
        this.commentsNum = commentsNum;
    }

    public int getFavoritesNum() {
        return favoritesNum;
    }

    public void setFavoritesNum(int favoritesNum) {
        this.favoritesNum = favoritesNum;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getCommentOrderNum() {
        return commentOrderNum;
    }

    public void setCommentOrderNum(int commentOrderNum) {
        this.commentOrderNum = commentOrderNum;
    }
}
