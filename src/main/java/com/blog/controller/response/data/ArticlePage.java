package com.blog.controller.response.data;

import com.blog.domain.Article;

import java.util.List;

public class ArticlePage {
    private List<Article> pageArticles;
    int pageNum;

    public ArticlePage(List<Article> pageArticles, int pageNum) {
        this.pageArticles = pageArticles;
        this.pageNum = pageNum;
    }

    public List<Article> getPageArticles() {
        return pageArticles;
    }

    public void setPageArticles(List<Article> pageArticles) {
        this.pageArticles = pageArticles;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
