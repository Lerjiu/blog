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
@RequestMapping("/api/favoriteFolder")
@ResponseBody
public class FavoriteFolderController {
    private FavoriteFolderService favoriteFolderService;

    public FavoriteFolderController(FavoriteFolderService favoriteFolderService) {
        this.favoriteFolderService = favoriteFolderService;
    }

    @RequestMapping("/add")
    public Response add(@RequestHeader("id") int userId, FavoriteFolder favoriteFolder) {
        favoriteFolder.setUserId(userId);
        favoriteFolderService.add(favoriteFolder);
        return DataResponse.success(Code.FAVORITE_ADD_FOLDER, Code.FAVORITE_ADD_FOLDER_MESSAGE, favoriteFolder.getId());
    }

    @RequestMapping("/getUserFolders")
    public DataResponse getUserFolders(@RequestHeader("id") int userId) {
        List<FavoriteFolder> favoriteFolders = favoriteFolderService.getFavoriteFolders(userId);
        return DataResponse.success(Code.FAVORITE_GET_FOLDER_LIST, Code.FAVORITE_GET_FOLDER_LIST_MESSAGE, favoriteFolders);
    }

    @RequestMapping("/delete")
    public Response delete(int id) {
        favoriteFolderService.delete(id);
        return Response.success(Code.FAVORITE_DELETE_FOLDER, Code.FAVORITE_DELETE_FOLDER_MESSAGE);
    }

}
