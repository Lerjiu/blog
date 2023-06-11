package com.blog.service;

import com.blog.domain.Favorite;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FavoriteService extends PermissionService {
    void add(Favorite favorite);
    List<Favorite> getFolderFavorites(int folderId);
    List<Favorite> getPageFolderFavorites(int folderId, int currentPage, int pageSize);
    Integer checkArticleInFolder(int articleId, int folderId);
    void delete(int id);
}
