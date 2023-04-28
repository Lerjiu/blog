package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.exception.Code;
import com.blog.service.FollowService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/follow")
@ResponseBody
public class FollowController {
    private FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @RequestMapping("/add")
    public Response add(@RequestHeader("id") int id, int followed) {
        followService.add(id, followed);
        return Response.success(Code.FOLLOW, Code.FOLLOW_MESSAGE);
    }

    @RequestMapping("/getUserFollowed")
    public DataResponse getUserFollowed(@RequestHeader("id") int id) {
        List<Integer> followedList = followService.getUserFollowed(id);
        return DataResponse.success(Code.FOLLOW_GET_FOLLOWED_LIST, Code.FOLLOW_GET_FOLLOWED_LIST_MESSAGE, followedList);
    }

    @RequestMapping("/delete")
    public Response delete(@RequestHeader("id") int id, int followed) {
        followService.delete(id, followed);
        return Response.success(Code.FOLLOW_DELETE, Code.FAVORITE_DELETE_MESSAGE);
    }
}
