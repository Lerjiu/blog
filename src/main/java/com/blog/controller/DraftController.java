package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.domain.Article;
import com.blog.domain.Draft;
import com.blog.domain.Label;
import com.blog.domain.Theme;
import com.blog.exception.Code;
import com.blog.service.ArticleService;
import com.blog.service.DraftLabelService;
import com.blog.service.DraftService;
import com.blog.service.DraftThemeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/draft")
@ResponseBody
public class DraftController {
    private DraftService draftService;
    private ArticleService articleService;
    private DraftThemeService draftThemeServices;
    private DraftLabelService draftLabelService;
    @Value("${search.page-size}")
    private int pageSize;

    public DraftController(DraftService draftService, ArticleService articleService, DraftThemeService draftThemeServices, DraftLabelService draftLabelService) {
        this.draftService = draftService;
        this.articleService = articleService;
        this.draftThemeServices = draftThemeServices;
        this.draftLabelService = draftLabelService;
    }

    @RequestMapping("/add")
    public Response add(@RequestHeader("id") int userId, Draft draft, int themeId, @RequestParam List<String> labelNames) {
        draft.setAuthor(userId);
        if (draft.getDescription() == null || draft.getDescription().equals("")) {
            draft.setDescription(draft.getContent().substring(0, Math.min(Article.DESCRIPTION_MAX_LENGTH, draft.getContent().length())));
        }
        draftService.add(draft);
        draftThemeServices.setDraftTheme(draft.getId(), themeId);
        draftLabelService.setDraftLabels(draft.getId(), labelNames);
        return DataResponse.success(Code.DRAFT_ADD, Code.DRAFT_ADD_MESSAGE, draft.getId());
    }

    @RequestMapping("/delete")
    public Response delete(int id) {
        draftThemeServices.deleteDraftTheme(id);
        draftLabelService.deleteDraftLabels(id);
        draftService.delete(id);
        return Response.success(Code.DRAFT_DELETE, Code.DRAFT_DELETE_MESSAGE);
    }

    @RequestMapping("/update")
    public Response update(Draft draft, int themeId, @RequestParam List<String> labelNames) {
        if (draft.getDescription() == null || draft.getDescription().equals("")) {
            draft.setDescription(draft.getContent().substring(0, Math.min(Article.DESCRIPTION_MAX_LENGTH, draft.getContent().length())));
        }
        draftService.update(draft);
        draftThemeServices.updateDraftTheme(draft.getId(), themeId);
        draftLabelService.updateDraftLabels(draft.getId(), labelNames);
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

    @RequestMapping("/publishDraft")
    public DataResponse publishDraft(int id, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date updateTime) {
        Draft draft = draftService.get(id);
        Article article = Article.getArticleFromDraft(draft);
        article.setUpdateTime(updateTime);
        articleService.add(article);
        draftThemeServices.deleteDraftTheme(id);
        draftLabelService.deleteDraftLabels(id);
        draftService.delete(id);
        return DataResponse.success(Code.DRAFT_PUBLISH, Code.DRAFT_PUBLISH_MESSAGE, article.getId());
    }

    @RequestMapping("/getTheme")
    public DataResponse getTheme(int id) {
        Theme theme = draftThemeServices.getDraftTheme(id);
        return DataResponse.success(Code.DRAFT_GET_THEME, Code.DRAFT_GET_THEME_MESSAGE, theme);
    }

    @RequestMapping("/getThemeByIds")
    public DataResponse getThemeByIds(@RequestParam List<Integer> ids) {
        List<Theme> themes = new ArrayList<>();
        for (Integer id : ids) {
            Theme theme = draftThemeServices.getDraftTheme(id);
            themes.add(theme);
        }
        return DataResponse.success(Code.DRAFT_GET_THEME_BY_IDS, Code.DRAFT_GET_THEME_BY_IDS_MESSAGE, themes);
    }

    @RequestMapping("/getLabels")
    public DataResponse getLabels(int id) {
        List<Label> labels = draftLabelService.getDraftLabels(id);
        return DataResponse.success(Code.DRAFT_GET_LABELS, Code.DRAFT_GET_LABELS_MESSAGE, labels);
    }

    @RequestMapping("/getLabelsByIds")
    public DataResponse getLabelsByIds(@RequestParam List<Integer> ids) {
        List<List<Label>> labelLists = new ArrayList<>();
        for (Integer id : ids) {
            List<Label> labels = draftLabelService.getDraftLabels(id);
            labelLists.add(labels);
        }
        return DataResponse.success(Code.DRAFT_GET_LABELS_BY_IDS, Code.DRAFT_GET_LABELS_BY_IDS_MESSAGE, labelLists);
    }
}
