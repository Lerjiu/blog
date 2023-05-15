package com.blog.service;

import com.blog.domain.Label;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ArticleLabelService {
    void setArticleLabels(int articleId, List<String> labelNames);
    void deleteArticleLabels(int articleId);
    void updateArticleLabels(int articleId, List<String> labelNames);
    List<Label> getArticleLabels(int articleId);
    int getLabelArticleNum(int labelId);
    List<Integer> getPageArticleIds(int labelId, int currentPage, int pageSize);
}
