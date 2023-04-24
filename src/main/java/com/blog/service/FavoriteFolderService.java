package com.blog.service;

import com.blog.domain.FavoriteFolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FavoriteFolderService {
    void add(FavoriteFolder favoriteFolder);
    List<FavoriteFolder> getFavoriteFolders(int userId);
    void delete(int id);
}
