package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.controller.response.data.FavoriteArticle;
import com.blog.domain.Article;
import com.blog.domain.Favorite;
import com.blog.domain.FavoriteFolder;
import com.blog.exception.Code;
import com.blog.service.ArticleService;
import com.blog.service.FavoriteFolderService;
import com.blog.service.FavoriteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/favorite")
@ResponseBody
public class FavoriteController {
    private FavoriteService favoriteService;
    private ArticleService articleService;
    private FavoriteFolderService favoriteFolderService;
    @Value("${search.page-size}")
    private int pageSize;

    public FavoriteController(FavoriteService favoriteService, ArticleService articleService, FavoriteFolderService favoriteFolderService) {
        this.favoriteService = favoriteService;
        this.articleService = articleService;
        this.favoriteFolderService = favoriteFolderService;
    }

    @RequestMapping("/add")
    public Response add(@RequestHeader("id") int userId, Favorite favorite) {
        favorite.setUserId(userId);
        favoriteService.add(favorite);
        return Response.success(Code.FAVORITE_ADD, Code.FAVORITE_ADD_MESSAGE);
    }

    @RequestMapping("/getFolderFavorites")
    public DataResponse getFolderFavorites(int folderId) {
        List<Favorite> favorites = favoriteService.getFolderFavorites(folderId);
        return DataResponse.success(Code.FAVORITE_GET_LIST, Code.FAVORITE_GET_LIST_MESSAGE, favorites);
    }

    @RequestMapping("/getFolderFavoritesPageNum")
    public DataResponse getFolderFavoritesPageNum(int folderId) {
        int folderFavoritesNum = favoriteFolderService.getFavoriteFolder(folderId).getFavoritesNum();
        int pageNum = folderFavoritesNum / pageSize + (folderFavoritesNum % pageSize == 0 ? 0 : 1);
        return DataResponse.success(Code.FAVORITE_GET_PAGE_NUM, Code.FAVORITE_GET_PAGE_NUM_MESSAGE, pageNum);
    }

    @RequestMapping("/getPageFolderFavorites")
    public DataResponse getPageFolderFavorites(int folderId, int currentPage) {
        List<Favorite> pageFolderFavorites = favoriteService.getPageFolderFavorites(folderId, currentPage, pageSize);
        System.out.println(pageFolderFavorites);
        List<Article> articles = new ArrayList<>();
        for (Favorite favorite : pageFolderFavorites) {
            Article article = articleService.get(favorite.getArticleId());
            articles.add(article);
        }
        System.out.println(articles);
        List<FavoriteArticle> favoriteArticles = new ArrayList<>();
        for (int i = 0; i < pageFolderFavorites.size(); i++) {
            Favorite favorite = pageFolderFavorites.get(i);
            Article article = articles.get(i);
            favoriteArticles.add(FavoriteArticle.getFavoriteArticle(favorite, article));
        }
        System.out.println(favoriteArticles);
        return DataResponse.success(Code.FAVORITE_GET_PAGE, Code.FAVORITE_GET_PAGE_MESSAGE, favoriteArticles);
    }

    @RequestMapping("/delete")
    public Response delete(int id) {
        favoriteService.delete(id);
        return Response.success(Code.FAVORITE_DELETE, Code.FAVORITE_DELETE_MESSAGE);
    }

    @RequestMapping("/checkArticleInFolders")
    public DataResponse checkArticleInFolders(@RequestHeader("id") int userId, int articleId) {
        List<FavoriteFolder> favoriteFolders = favoriteFolderService.getFavoriteFolders(userId);
        List<Boolean> isArticleInFolders = new ArrayList<>();
        for (FavoriteFolder favoriteFolder : favoriteFolders) {
            boolean isArticleInFolder = favoriteService.checkArticleInFolder(articleId, favoriteFolder.getId());
            isArticleInFolders.add(isArticleInFolder);
        }
        return DataResponse.success(Code.FAVORITE_CHECK_ARTICLE_IN_FOLDERS, Code.FAVORITE_CHECK_ARTICLE_IN_FOLDERS_MESSAGE, isArticleInFolders);
    }
}
