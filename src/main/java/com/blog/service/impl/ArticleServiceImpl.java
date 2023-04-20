package com.blog.service.impl;

import com.blog.dao.ArticleDao;
import com.blog.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao;

    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public int getCommentsNum(int id) {
        return articleDao.getCommentsNum(id);
    }
}
