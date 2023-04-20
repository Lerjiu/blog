package com.blog.service.impl;

import com.blog.dao.ArticleDao;
import com.blog.dao.CommentDao;
import com.blog.domain.Comment;
import com.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;
    private ArticleDao articleDao;

    public CommentServiceImpl(CommentDao commentDao, ArticleDao articleDao) {
        this.commentDao = commentDao;
        this.articleDao = articleDao;
    }

    @Override
    public void add(Comment comment) {
        int commentsNum = articleDao.getCommentsNum(comment.getArticleId());
        comment.setOrderNum(commentsNum + 1);
        commentDao.add(comment);
    }

    @Override
    public List<Comment> getComments(int articleId) {
        return commentDao.getComments(articleId);
    }
}
