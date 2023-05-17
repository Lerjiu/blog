package com.blog.service.impl;

import com.blog.dao.ArticleDao;
import com.blog.dao.EsArticleDao;
import com.blog.dao.FavoriteDao;
import com.blog.dao.FavoriteFolderDao;
import com.blog.domain.Favorite;
import com.blog.domain.FavoriteFolder;
import com.blog.service.FavoriteFolderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteFolderServiceImpl implements FavoriteFolderService {
    private FavoriteFolderDao favoriteFolderDao;
    private FavoriteDao favoriteDao;
    private ArticleDao articleDao;
    private EsArticleDao esArticleDao;

    public FavoriteFolderServiceImpl(FavoriteFolderDao favoriteFolderDao, FavoriteDao favoriteDao, ArticleDao articleDao, EsArticleDao esArticleDao) {
        this.favoriteFolderDao = favoriteFolderDao;
        this.favoriteDao = favoriteDao;
        this.articleDao = articleDao;
        this.esArticleDao = esArticleDao;
    }

    @Override
    public void add(FavoriteFolder favoriteFolder) {
        favoriteFolderDao.add(favoriteFolder);
    }

    @Override
    public FavoriteFolder getFavoriteFolder(int id) {
        return favoriteFolderDao.getFavoriteFolder(id);
    }

    @Override
    public List<FavoriteFolder> getFavoriteFolders(int userId) {
        return favoriteFolderDao.getFavoriteFolders(userId);
    }

    @Override
    public void delete(int id) {
        List<Favorite> folderFavorites = favoriteDao.getFolderFavorites(id);
        favoriteDao.deleteForFolder(id);
        for (Favorite favorite : folderFavorites) {
            articleDao.subFavoritesNum(favorite.getArticleId());
            esArticleDao.subFavoritesNum(favorite.getArticleId());
        }
        favoriteFolderDao.delete(id);
    }

    @Override
    public boolean checkPermission(int userId, int id) {
        return favoriteFolderDao.checkUserFavoriteFolder(userId, id);
    }
}
