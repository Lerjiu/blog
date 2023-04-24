package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.domain.Favorite;
import com.blog.exception.Code;
import com.blog.service.FavoriteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/favorite")
@ResponseBody
public class FavoriteController {
    private FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @RequestMapping("/add")
    public Response add(@RequestHeader("id") int id, Favorite favorite) {
        favorite.setUserId(id);
        favoriteService.add(favorite);
        return Response.success(Code.FAVORITE_ADD, Code.FAVORITE_ADD_MESSAGE);
    }

    @RequestMapping("/getFavorites")
    public DataResponse getFavorites(int folderId) {
        List<Favorite> favorites = favoriteService.getFavorites(folderId);
        return DataResponse.success(Code.FAVORITE_GET_LIST, Code.FAVORITE_GET_LIST_MESSAGE, favorites);
    }

    @RequestMapping("/delete")
    public Response delete(int id) {
        favoriteService.delete(id);
        return Response.success(Code.FAVORITE_DELETE, Code.FAVORITE_DELETE_MESSAGE);
    }
}
