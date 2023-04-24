package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.domain.FavoriteFolder;
import com.blog.exception.Code;
import com.blog.service.FavoriteFolderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/FavoriteFolder")
@ResponseBody
public class FavoriteFolderController {
    private FavoriteFolderService favoriteFolderService;

    public FavoriteFolderController(FavoriteFolderService favoriteFolderService) {
        this.favoriteFolderService = favoriteFolderService;
    }

    @RequestMapping("/add")
    public Response add(@RequestHeader("id") int id, FavoriteFolder favoriteFolder) {
        favoriteFolder.setUserId(id);
        favoriteFolderService.add(favoriteFolder);
        return Response.success(Code.FAVORITE_ADD_FOLDER, Code.FAVORITE_ADD_FOLDER_MESSAGE);
    }

    @RequestMapping("/getFavoriteFolders")
    public DataResponse getList(@RequestHeader("id") int id) {
        List<FavoriteFolder> favoriteFolders = favoriteFolderService.getFavoriteFolders(id);
        return DataResponse.success(Code.FAVORITE_GET_FOLDER_LIST, Code.FOLLOW_GET_FOLLOWED_LIST_MESSAGE, favoriteFolders);
    }

    @RequestMapping("/delete")
    public Response delete(int id) {
        favoriteFolderService.delete(id);
        return Response.success(Code.FAVORITE_DELETE_FOLDER, Code.FAVORITE_DELETE_FOLDER_MESSAGE);
    }
}
