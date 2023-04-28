package com.blog.service.impl;

import com.blog.dao.ArticleDao;
import com.blog.dao.FavoriteDao;
import com.blog.domain.Favorite;
import com.blog.service.FavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao;
    private ArticleDao articleDao;

    public FavoriteServiceImpl(FavoriteDao favoriteDao, ArticleDao articleDao) {
        this.favoriteDao = favoriteDao;
        this.articleDao = articleDao;
    }

    @Override
    public void add(Favorite favorite) {
        favoriteDao.add(favorite);
        articleDao.addFavoritesNum(favorite.getArticleId());
    }

    @Override
    public List<Favorite> getFolderFavorites(int folderId) {
        return favoriteDao.getFolderFavorites(folderId);
    }

    @Override
    public void delete(int id) {
        Favorite favorite = favoriteDao.get(id);
        favoriteDao.delete(id);
        articleDao.subFavoritesNum(favorite.getArticleId());
    }

    @Override
    public boolean checkPermission(int userId, int id) {
        return favoriteDao.checkUserFavorite(userId, id);
    }
}
