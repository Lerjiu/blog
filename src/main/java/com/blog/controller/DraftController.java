package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.domain.Article;
import com.blog.domain.Draft;
import com.blog.exception.Code;
import com.blog.service.DraftService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/draft")
@ResponseBody
public class DraftController {
    private DraftService draftService;
    @Value("${search.page-size}")
    private int pageSize;

    public DraftController(DraftService draftService) {
        this.draftService = draftService;
    }

    @RequestMapping("/add")
    public Response add(@RequestHeader("id") int userId, Draft draft) {
        draft.setAuthor(userId);
        if (draft.getDescription() == null || draft.getDescription().equals("")) {
            draft.setDescription(draft.getContent().substring(0, Math.min(Article.DESCRIPTION_MAX_LENGTH, draft.getContent().length())));
        }
        draftService.add(draft);
        return Response.success(Code.DRAFT_ADD, Code.DRAFT_ADD_MESSAGE);
    }

    @RequestMapping("/delete")
    public Response delete(int id) {
        draftService.delete(id);
        return Response.success(Code.DRAFT_DELETE, Code.DRAFT_DELETE_MESSAGE);
    }

    @RequestMapping("/update")
    public Response update(Draft draft) {
        if (draft.getDescription() == null || draft.getDescription().equals("")) {
            draft.setDescription(draft.getContent().substring(0, Math.min(Article.DESCRIPTION_MAX_LENGTH, draft.getContent().length())));
        }
        draftService.update(draft);
        return Response.success(Code.DRAFT_UPDATE, Code.DRAFT_UPDATE_MESSAGE);
    }

    @RequestMapping("/get")
    public DataResponse get(int id) {
        Draft draft = draftService.get(id);
        return DataResponse.success(Code.DRAFT_GET, Code.DRAFT_GET_MESSAGE, draft);
    }

    @RequestMapping("/getUserPageNum")
    public DataResponse getUserPageNum(@RequestHeader("id") int userId) {
        int userDraftNum = draftService.getUserDraftNum(userId);
        int userPageNum = userDraftNum / pageSize + (userDraftNum % pageSize == 0 ? 0 : 1);
        return DataResponse.success(Code.DRAFT_GET_USER_NUM, Code.DRAFT_GET_USER_NUM_MESSAGE, userPageNum);
    }

    @RequestMapping("/getUserPage")
    public DataResponse getUserPageDrafts(@RequestHeader("id") int userId, int currentPage) {
        List<Draft> userPageDrafts = draftService.getUserPageDrafts(userId, currentPage, pageSize);
        return DataResponse.success(Code.DRAFT_GET_USER_PAGE, Code.DRAFT_GET_USER_PAGE_MESSAGE, userPageDrafts);
    }
}
