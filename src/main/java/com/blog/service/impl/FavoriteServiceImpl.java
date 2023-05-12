package com.blog.service.impl;

import com.blog.dao.ArticleDao;
import com.blog.dao.FavoriteDao;
import com.blog.dao.FavoriteFolderDao;
import com.blog.domain.Favorite;
import com.blog.service.FavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao;
    private ArticleDao articleDao;
    private FavoriteFolderDao favoriteFolderDao;

    public FavoriteServiceImpl(FavoriteDao favoriteDao, ArticleDao articleDao, FavoriteFolderDao favoriteFolderDao) {
        this.favoriteDao = favoriteDao;
        this.articleDao = articleDao;
        this.favoriteFolderDao = favoriteFolderDao;
    }

    @Override
    public void add(Favorite favorite) {
        favoriteDao.add(favorite);
        articleDao.addFavoritesNum(favorite.getArticleId());
        favoriteFolderDao.addFavoritesNum(favorite.getFolderId());
    }

    @Override
    public List<Favorite> getFolderFavorites(int folderId) {
        return favoriteDao.getFolderFavorites(folderId);
    }

    @Override
    public List<Favorite> getPageFolderFavorites(int folderId, int currentPage, int pageSize) {
        return favoriteDao.getPageFolderFavorites(folderId, (currentPage - 1)*pageSize, pageSize);
    }

    @Override
    public void delete(int id) {
        Favorite favorite = favoriteDao.get(id);
        favoriteDao.delete(id);
        articleDao.subFavoritesNum(favorite.getArticleId());
        favoriteFolderDao.subFavoritesNum(favorite.getFolderId());
    }

    @Override
    public boolean checkPermission(int userId, int id) {
        return favoriteDao.checkUserFavorite(userId, id);
    }
}
