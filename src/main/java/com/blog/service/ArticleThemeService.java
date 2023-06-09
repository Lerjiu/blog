package com.blog.service;

import com.blog.domain.Theme;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ArticleThemeService {
    void setArticleTheme(int articleId, int themeId);
    void deleteArticleTheme(int articleId);
    Theme getArticleTheme(int articleId);
    void updateArticleTheme(int articleId, int themeId);
    int getThemeArticleNum(int themeId);
    List<Integer> getPageArticleIds(int themeId, int currentPage, int pageSize);
}
