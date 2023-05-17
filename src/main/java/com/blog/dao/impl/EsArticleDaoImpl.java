package com.blog.dao.impl;

import com.blog.dao.EsArticleDao;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Repository;

@Repository
public class EsArticleDaoImpl implements EsArticleDao {
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public EsArticleDaoImpl(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @Override
    public void addCommentsNum(int articleId) {
        UpdateQuery updateQuery = UpdateQuery.builder(String.valueOf(articleId))
                .withIndex("articles")
                .withScript("ctx._source.commentsNum += 1")
                .build();
        elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of("articles"));
    }

    @Override
    public void subCommentsNum(int articleId) {
        UpdateQuery updateQuery = UpdateQuery.builder(String.valueOf(articleId))
                .withIndex("articles")
                .withScript("ctx._source.commentsNum -= 1")
                .build();
        elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of("articles"));
    }

    @Override
    public void addFavoritesNum(int articleId) {
        UpdateQuery updateQuery = UpdateQuery.builder(String.valueOf(articleId))
                .withIndex("articles")
                .withScript("ctx._source.favoritesNum += 1")
                .build();
        elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of("articles"));
    }

    @Override
    public void subFavoritesNum(int articleId) {
        UpdateQuery updateQuery = UpdateQuery.builder(String.valueOf(articleId))
                .withIndex("articles")
                .withScript("ctx._source.favoritesNum -= 1")
                .build();
        elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of("articles"));
    }

    @Override
    public void addCommentOrderNum(int articleId) {
        UpdateQuery updateQuery = UpdateQuery.builder(String.valueOf(articleId))
                .withIndex("articles")
                .withScript("ctx._source.commentOrderNum += 1")
                .build();
        elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of("articles"));
    }

}
