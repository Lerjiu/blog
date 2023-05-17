package com.blog.service.impl;

import com.blog.dao.EsArticleRepository;
import com.blog.domain.Article;
import com.blog.service.EsArticleService;

import com.blog.utils.IntIdConverter;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.MultiGetItem;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EsArticleServiceImpl implements EsArticleService {
    private EsArticleRepository esArticleRepository;
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Value("${search.max-page}")
    private int maxPage;

    public EsArticleServiceImpl(EsArticleRepository esArticleRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.esArticleRepository = esArticleRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @Override
    public void docCreate(Article article) {
        esArticleRepository.save(article);
    }

    @Override
    public void docDelete(int id) {
        esArticleRepository.deleteById(id);
    }

    @Override
    public void docUpdate(Article article) {
        esArticleRepository.save(article);
    }

    @Override
    public Article docSearchById(int id) {
        Optional<Article> article = esArticleRepository.findById(id);
        return article.orElse(null);
    }

    @Override
    public List<Article> docPageGet(int currentPage, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize, sort);
        List<Article> pageArticles = esArticleRepository.findAll(pageRequest).getContent();
        for (Article article : pageArticles) {
            System.out.println(article.getId());
        }
        return pageArticles;
    }

    @Override
    public PageImpl<Article> docPageFuzzySearch(int currentPage, int pageSize, String search) {
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

        FunctionScoreQueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(
                        QueryBuilders.matchQuery("all", search)
                                .analyzer("ik_max_word")
                                .operator(Operator.AND)
                                .fuzziness(Fuzziness.AUTO));

        Sort sort = Sort.by(Sort.Direction.DESC, "_score", "favoritesNum");

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withSort(sort)
                .withPageable(pageable)
                .withMaxResults(pageSize * maxPage)
                .build();

        SearchHits<Article> searchHits = elasticsearchRestTemplate.search(searchQuery, Article.class);
        List<Article> pageArticles = new ArrayList<>();
        searchHits.forEach(searchHit -> {
            Article article = searchHit.getContent();
            article.setContent("");
            pageArticles.add(article);
            System.out.println(searchHit.getContent().getId() + ": " + searchHit.getScore());
        });

        int resultNum = (int) searchHits.getTotalHits();

        return new PageImpl<>(pageArticles, pageable, resultNum);
    }

    @Override
    public List<Article> docPageGetByIdList(List<Integer> articleIds) {
        List<MultiGetItem<Article>> multiGetItems = elasticsearchRestTemplate.multiGet(Query.multiGetQuery(IntIdConverter.convert(articleIds)), Article.class);
        List<Article> articles = new ArrayList<>();
        multiGetItems.forEach(articleMultiGetItem -> {
            Article article = articleMultiGetItem.getItem();
            if (article != null) {
                article.setContent("");
                articles.add(article);
            }
        });
        return articles;
    }

}
