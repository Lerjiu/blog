package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.exception.Code;
import com.blog.service.FollowService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Response add(@RequestHeader("id") int userId, int followed) {
        followService.add(userId, followed);
        return Response.success(Code.FOLLOW, Code.FOLLOW_MESSAGE);
    }

    @RequestMapping("/getUserFollowed")
    public DataResponse getUserFollowed(@RequestHeader("id") int userId) {
        List<Integer> followedList = followService.getUserFollowed(userId);
        return DataResponse.success(Code.FOLLOW_GET_FOLLOWED_LIST, Code.FOLLOW_GET_FOLLOWED_LIST_MESSAGE, followedList);
    }

    @RequestMapping("/getUserFollower")
    public DataResponse getUserFollower(@RequestHeader("id") int userId) {
        List<Integer> followerList = followService.getUserFollower(userId);
        return DataResponse.success(Code.FOLLOW_GET_FOLLOWER_LIST, Code.FOLLOW_GET_FOLLOWER_LIST_MESSAGE, followerList);
    }

    @RequestMapping("/delete")
    public Response delete(@RequestHeader("id") int userId, @RequestParam(name = "id") int followed) {
        followService.delete(userId, followed);
        return Response.success(Code.FOLLOW_DELETE, Code.FOLLOW_DELETE_MESSAGE);
    }
}
