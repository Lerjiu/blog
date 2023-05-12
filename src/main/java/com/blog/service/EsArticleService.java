package com.blog.service;

import com.blog.domain.Article;
import org.springframework.data.domain.PageImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EsArticleService {
    void docCreate(Article article);
    void docDelete(int id);
    void docUpdate(Article article);
    Article docSearchById(int id);
    List<Article> docPageGet(int currentPage, int pageSize);
    PageImpl<Article> docPageFuzzySearch(int currentPage, int pageSize, String search);
    List<Article> docPageGetByIdList(List<Integer> articleIds);
}
