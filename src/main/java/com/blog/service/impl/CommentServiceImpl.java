package com.blog.service.impl;

import com.blog.dao.ArticleDao;
import com.blog.dao.CommentDao;
import com.blog.dao.EsArticleDao;
import com.blog.dao.EsArticleRepository;
import com.blog.domain.Comment;
import com.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;
    private ArticleDao articleDao;
    private EsArticleDao esArticleDao;

    public CommentServiceImpl(CommentDao commentDao, ArticleDao articleDao, EsArticleDao esArticleDao) {
        this.commentDao = commentDao;
        this.articleDao = articleDao;
        this.esArticleDao = esArticleDao;
    }

    @Override
    public void add(Comment comment) {
        int commentOrderNum = articleDao.getCommentOrderNum(comment.getArticleId());
        comment.setOrderNum(commentOrderNum + 1);
        commentDao.add(comment);
        articleDao.addCommentsNum(comment.getArticleId());
        esArticleDao.addCommentsNum(comment.getArticleId());
        articleDao.addCommentOrderNum(comment.getArticleId());
        esArticleDao.addCommentOrderNum(comment.getArticleId());
    }

    @Override
    public List<Comment> getArticleComments(int articleId) {
        return commentDao.getArticleComments(articleId);
    }

    @Override
    public void delete(int id) {
        commentDao.delete(id);
        Comment comment = commentDao.get(id);
        articleDao.subCommentsNum(comment.getArticleId());
        esArticleDao.subCommentsNum(comment.getArticleId());
    }

    @Override
    public boolean checkPermission(int userId, int id) {
        return commentDao.checkUserComment(userId, id);
    }
}
