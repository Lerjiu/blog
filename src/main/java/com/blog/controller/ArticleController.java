package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.controller.response.data.ArticlePage;
import com.blog.domain.Article;
import com.blog.exception.Code;
import com.blog.service.ArticleService;
import com.blog.service.EsArticleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/article")
@ResponseBody
public class ArticleController {
    private ArticleService articleService;
    private EsArticleService elasticService;
    @Value("${search.page-size}")
    private int pageSize;

    public ArticleController(ArticleService articleService, EsArticleService elasticService) {
        this.articleService = articleService;
        this.elasticService = elasticService;
    }

    @RequestMapping("/add")
    public Response add(@RequestHeader("id") int id, Article article) {
        article.setAuthor(id);
        if (article.getDescription() == null || article.getDescription().equals("")) {
            article.setDescription(article.getContent().substring(0, Math.min(Article.DESCRIPTION_MAX_LENGTH, article.getContent().length())));
        }
        articleService.add(article);
        elasticService.docCreate(article);
        return Response.success(Code.ARTICLE_ADD, Code.ARTICLE_ADD_MESSAGE);
    }

    @RequestMapping("/delete")
    public Response delete(int id) {
        articleService.delete(id);
        elasticService.docDelete(id);
        return Response.success(Code.ARTICLE_DELETE, Code.ARTICLE_DELETE_MESSAGE);
    }

    @RequestMapping("/update")
    public Response update(Article article) {
        Article oldArticle = articleService.get(article.getId());
        article.setFavoritesNum(oldArticle.getFavoritesNum());
        article.setCommentsNum(oldArticle.getCommentsNum());
        article.setAuthor(oldArticle.getAuthor());
        if (article.getDescription() == null || article.getDescription().equals("")) {
            article.setDescription(article.getContent().substring(0, Math.min(Article.DESCRIPTION_MAX_LENGTH, article.getContent().length())));
        }
        articleService.update(article);
        elasticService.docUpdate(article);
        return Response.success(Code.ARTICLE_UPDATE, Code.ARTICLE_UPDATE_MESSAGE);
    }

    @RequestMapping("/get")
    public DataResponse get(int id) {
        Article article = elasticService.docSearchById(id);
        if (article == null) {
            article = articleService.get(id);
        }
        return DataResponse.success(Code.ARTICLE_GET, Code.ARTICLE_GET_MESSAGE, article);
    }

    @RequestMapping("/getPageNum")
    public DataResponse getPageNum() {
        int articleNum = articleService.getArticleNum();
        int pageNum = articleNum / pageSize + (articleNum % pageSize == 0 ? 0 : 1);
        return DataResponse.success(Code.ARTICLE_GET_NUM, Code.ARTICLE_GET_NUM_MESSAGE, pageNum);
    }

    @RequestMapping("/getPageArticles")
    public DataResponse getPageArticles(int currentPage) {
        List<Article> pageArticles = elasticService.docPageGet(currentPage, pageSize);
        if (pageArticles.isEmpty()) {
            pageArticles = articleService.getPageArticles(currentPage, pageSize);
        }
        return DataResponse.success(Code.ARTICLE_GET_PAGE, Code.ARTICLE_GET_PAGE_MESSAGE, pageArticles);
    }

    @RequestMapping("/getUserPageNum")
    public DataResponse getUserPageNum(int userId) {
        int userArticleNum = articleService.getUserArticleNum(userId);
        int userPageNum = userArticleNum / pageSize + (userArticleNum % pageSize == 0 ? 0 : 1);
        return DataResponse.success(Code.ARTICLE_GET_USER_NUM, Code.ARTICLE_GET_USER_NUM_MESSAGE, userPageNum);
    }

    @RequestMapping("/getUserPage")
    public DataResponse getUserPageArticles(int userId, int currentPage) {
        List<Article> userPageArticles = articleService.getUserPageArticles(userId, currentPage, pageSize);
        return DataResponse.success(Code.ARTICLE_GET_USER_PAGE, Code.ARTICLE_GET_USER_PAGE_MESSAGE, userPageArticles);
    }

    @RequestMapping("/pageFuzzySearch")
    public DataResponse pageFuzzySearch(int currentPage, String search) {
        PageImpl<Article> articlePageImpl = elasticService.docPageFuzzySearch(currentPage, pageSize, search);
        ArticlePage articlePage = new ArticlePage(articlePageImpl.getContent(), articlePageImpl.getTotalPages());
        return DataResponse.success(Code.ARTICLE_PAGE_FUZZY_SEARCH, Code.ARTICLE_PAGE_FUZZY_SEARCH_MESSAGE, articlePage);
    }
}
