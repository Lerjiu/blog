package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.domain.Comment;
import com.blog.exception.Code;
import com.blog.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/comment")
@ResponseBody
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/add")
    public DataResponse add(@RequestHeader("id") int id, Comment comment) {
        comment.setUserId(id);
        commentService.add(comment);
        return DataResponse.success(Code.COMMENT_ADD, Code.COMMENT_ADD_MESSAGE, comment);
    }

    @RequestMapping("/getComments")
    public DataResponse getList(int articleId) {
        List<Comment> comments = commentService.getComments(articleId);
        return DataResponse.success(Code.COMMENT_GET_LIST, Code.COMMENT_GET_LIST_MESSAGE, comments);
    }
}
