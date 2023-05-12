package com.blog.service.impl;

import com.blog.dao.ArticleLabelDao;
import com.blog.dao.LabelDao;
import com.blog.domain.Label;
import com.blog.service.ArticleLabelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleLabelServiceImpl implements ArticleLabelService {
    private LabelDao labelDao;
    private ArticleLabelDao articleLabelDao;

    public ArticleLabelServiceImpl(LabelDao labelDao, ArticleLabelDao articleLabelDao) {
        this.labelDao = labelDao;
        this.articleLabelDao = articleLabelDao;
    }

    @Override
    public void setArticleLabels(int articleId, List<String> labelNames) {
        for (String labelName : labelNames) {
            Label label = labelDao.get(labelName);
            if (label == null) {
                label = new Label();
                label.setName(labelName);
                labelDao.add(label);
            }
            articleLabelDao.add(articleId, label.getId());
        }
    }

    @Override
    public void deleteArticleLabels(int articleId) {
        articleLabelDao.deleteArticleLabel(articleId);
    }

    @Override
    public void updateArticleLabels(int articleId, List<String> labelNames) {
        deleteArticleLabels(articleId);
        setArticleLabels(articleId, labelNames);
    }

    @Override
    public int getLabelArticleNum(int labelId) {
        return articleLabelDao.getLabelArticleNum(labelId);
    }

    @Override
    public List<Integer> getPageArticleIds(int labelId, int currentPage, int pageSize) {
        return articleLabelDao.getPageArticleIds(labelId, (currentPage - 1) * pageSize, pageSize);
    }
}
