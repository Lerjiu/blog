package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.controller.response.data.ArticlePage;
import com.blog.domain.Article;
import com.blog.exception.Code;
import com.blog.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/api/article")
@ResponseBody
public class ArticleController {
    private ArticleService articleService;
    private EsArticleService elasticService;
    private FileService fileService;
    private ArticleLabelService articleLabelService;
    private ArticleThemeService articleThemeService;
    @Value("${file.article-img}")
    private String articleImgPath;
    @Value("${search.page-size}")
    private int pageSize;

    public ArticleController(ArticleService articleService, EsArticleService elasticService, FileService fileService, ArticleLabelService articleLabelService, ArticleThemeService articleThemeService) {
        this.articleService = articleService;
        this.elasticService = elasticService;
        this.fileService = fileService;
        this.articleLabelService = articleLabelService;
        this.articleThemeService = articleThemeService;
    }

    @RequestMapping("/add")
    public Response add(@RequestHeader("id") int userId, Article article, int themeId, @RequestParam List<String> labelNames) {
        article.setAuthor(userId);
        if (article.getDescription() == null || article.getDescription().equals("")) {
            article.setDescription(article.getContent().substring(0, Math.min(Article.DESCRIPTION_MAX_LENGTH, article.getContent().length())));
        }
        articleService.add(article);
        elasticService.docCreate(article);
        articleThemeService.setArticleTheme(article.getId(), themeId);
        articleLabelService.setArticleLabels(article.getId(), labelNames);
        return Response.success(Code.ARTICLE_ADD, Code.ARTICLE_ADD_MESSAGE);
    }

    @RequestMapping("/delete")
    public Response delete(int id) {
        articleService.delete(id);
        elasticService.docDelete(id);
        articleThemeService.deleteArticleTheme(id);
        articleLabelService.deleteArticleLabels(id);
        return Response.success(Code.ARTICLE_DELETE, Code.ARTICLE_DELETE_MESSAGE);
    }

    @RequestMapping("/update")
    public Response update(Article article, int themeId, @RequestParam List<String> labelNames) {
        // es对文章所有字段进行更改，因此需要获取一下原来的字段信息
        Article oldArticle = articleService.get(article.getId());
        article.setFavoritesNum(oldArticle.getFavoritesNum());
        article.setCommentsNum(oldArticle.getCommentsNum());
        article.setCommentOrderNum(oldArticle.getCommentOrderNum());
        article.setAuthor(oldArticle.getAuthor());
        if (article.getDescription() == null || article.getDescription().equals("")) {
            article.setDescription(article.getContent().substring(0, Math.min(Article.DESCRIPTION_MAX_LENGTH, article.getContent().length())));
        }
        articleService.update(article);
        elasticService.docUpdate(article);
        articleThemeService.updateArticleTheme(article.getId(), themeId);
        articleLabelService.updateArticleLabels(article.getId(), labelNames);
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

    @RequestMapping("/uploadImg")
    public DataResponse uploadImg(MultipartFile articleImg) {
        String articleImgName = fileService.upload(articleImg, articleImgPath);
        return DataResponse.success(Code.ARTICLE_UPLOAD_IMG, Code.ARTICLE_UPLOAD_IMG_MESSAGE, articleImgName);
    }

    @RequestMapping("/getThemeArticleNum")
    public DataResponse getThemeArticleNum(int themeId) {
        int themeArticleNum = articleThemeService.getThemeArticleNum(themeId);
        int themePageNum = themeArticleNum / pageSize + (themeArticleNum % pageSize == 0 ? 0 : 1);
        return DataResponse.success(Code.ARTICLE_THEME_GET_NUM, Code.ARTICLE_THEME_GET_NUM_MESSAGE, themePageNum);
    }

    @RequestMapping("/getPageThemeArticleIds")
    public DataResponse getPageThemeArticleIds(int themeId, int currentPage) {
        List<Integer> ids = articleThemeService.getPageArticleIds(themeId, currentPage, pageSize);
        return DataResponse.success(Code.ARTICLE_THEME_GET_PAGE, Code.ARTICLE_THEME_GET_PAGE_MESSAGE, ids);
    }

    @RequestMapping("/getLabelArticleNum")
    public DataResponse getLabelArticleNum(int labelId) {
        int labelArticleNum = articleLabelService.getLabelArticleNum(labelId);
        int labelPageNum = labelArticleNum / pageSize + (labelArticleNum % pageSize == 0 ? 0 : 1);
        return DataResponse.success(Code.ARTICLE_LABEL_GET_NUM, Code.ARTICLE_LABEL_GET_NUM_MESSAGE, labelPageNum);
    }

    @RequestMapping("/getPageLabelArticleIds")
    public DataResponse getPageLabelArticleIds(int labelId, int currentPage) {
        List<Integer> ids = articleLabelService.getPageArticleIds(labelId, currentPage, pageSize);
        return DataResponse.success(Code.ARTICLE_LABEL_GET_PAGE, Code.ARTICLE_LABEL_GET_PAGE_MESSAGE, ids);
    }

    @RequestMapping("/getByIdList")
    public DataResponse getByIdList(List<Integer> ids) {
        List<Article> articles = elasticService.docPageGetByIdList(ids);
         return DataResponse.success(Code.ARTICLE_GET_BY_ID_LIST, Code.ARTICLE_GET_BY_ID_LIST_MESSAGE, articles);
    }
}
