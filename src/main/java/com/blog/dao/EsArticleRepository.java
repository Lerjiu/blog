package com.blog.dao;

import com.blog.domain.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsArticleRepository extends ElasticsearchRepository<Article, Integer> {
}
