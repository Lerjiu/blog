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
    public List<Favorite> getFavorites(int folderId) {
        return favoriteDao.getFavorites(folderId);
    }

    @Override
    public void delete(int id) {
        favoriteDao.delete(id);
        articleDao.subFavoritesNum(id);
    }
}
