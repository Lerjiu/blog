package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
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
    public DataResponse add(@RequestHeader("id") int userId, Comment comment) {
        comment.setUserId(userId);
        commentService.add(comment);
        return DataResponse.success(Code.COMMENT_ADD, Code.COMMENT_ADD_MESSAGE, comment);
    }

    @RequestMapping("/getArticleComments")
    public DataResponse getArticleComments(int articleId) {
        List<Comment> comments = commentService.getArticleComments(articleId);
        return DataResponse.success(Code.COMMENT_GET_LIST, Code.COMMENT_GET_LIST_MESSAGE, comments);
    }

    @RequestMapping("/delete")
    public Response delete(int id) {
        commentService.delete(id);
        return Response.success(Code.COMMENT_DELETE, Code.COMMENT_DELETE_MESSAGE);
    }
}
