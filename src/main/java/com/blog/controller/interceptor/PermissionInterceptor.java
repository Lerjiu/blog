package com.blog.controller.interceptor;

import com.blog.service.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Component
public class PermissionInterceptor implements HandlerInterceptor {
    private ArticleService articleService;
    private CommentService commentService;
    private DraftService draftService;
    private FavoriteFolderService favoriteFolderService;
    private FavoriteService favoriteService;
    private FollowService followService;
    private HashMap<String, PermissionService> pathPermissionMap;

    public PermissionInterceptor(ArticleService articleService, CommentService commentService, DraftService draftService, FavoriteFolderService favoriteFolderService, FavoriteService favoriteService, FollowService followService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.draftService = draftService;
        this.favoriteFolderService = favoriteFolderService;
        this.favoriteService = favoriteService;
        this.followService = followService;
        pathPermissionMap = new HashMap<>();
        pathPermissionMap.put("article", articleService);
        pathPermissionMap.put("comment", commentService);
        pathPermissionMap.put("draft", draftService);
        pathPermissionMap.put("favoriteFolder", favoriteFolderService);
        pathPermissionMap.put("favorite", favoriteService);
        pathPermissionMap.put("follow", followService);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("check permission for update and delete");
        String midUri = request.getRequestURI().split("/")[2];
        System.out.println(midUri);
        PermissionService permissionService = pathPermissionMap.get(midUri);
        int userId = Integer.parseInt(request.getHeader("id"));
        int id = Integer.parseInt(request.getParameter("id"));
        return permissionService.checkPermission(userId, id);
    }
}
