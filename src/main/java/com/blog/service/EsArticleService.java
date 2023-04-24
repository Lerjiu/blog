package com.blog.service;

import com.blog.domain.Article;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface EsArticleService {
    void docCreate(Article article);
    void docDelete(int id);
    void docUpdate(Article article);
    Article docSearchById(int id);
    List<Article> docPageGet(int currentPage, int pageSize);
    PageImpl<Article> docPageFuzzySearch(int currentPage, int pageSize, String search);

}
