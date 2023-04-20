package com.blog.service;

import com.blog.domain.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CommentService {
    void add(Comment comment);
    List<Comment> getComments(int articleId);
}
