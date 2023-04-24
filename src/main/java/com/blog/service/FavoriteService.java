package com.blog.service;

import com.blog.domain.Favorite;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FavoriteService {
    void add(Favorite favorite);
    List<Favorite> getFavorites(int folderId);
    void delete(int id);
}
