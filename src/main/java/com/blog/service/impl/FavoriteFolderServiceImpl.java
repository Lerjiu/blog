package com.blog.service.impl;

import com.blog.dao.FavoriteDao;
import com.blog.dao.FavoriteFolderDao;
import com.blog.domain.FavoriteFolder;
import com.blog.service.FavoriteFolderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteFolderServiceImpl implements FavoriteFolderService {
    private FavoriteFolderDao favoriteFolderDao;
    private FavoriteDao favoriteDao;

    public FavoriteFolderServiceImpl(FavoriteFolderDao favoriteFolderDao, FavoriteDao favoriteDao) {
        this.favoriteFolderDao = favoriteFolderDao;
        this.favoriteDao = favoriteDao;
    }

    @Override
    public void add(FavoriteFolder favoriteFolder) {
        favoriteFolderDao.add(favoriteFolder);
    }

    @Override
    public List<FavoriteFolder> getFavoriteFolders(int userId) {
        return favoriteFolderDao.getFavoriteFolders(userId);
    }

    @Override
    public void delete(int id) {
        favoriteFolderDao.delete(id);
        favoriteDao.deleteForFolder(id);
    }
}
