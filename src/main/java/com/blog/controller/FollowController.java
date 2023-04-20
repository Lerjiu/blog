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

    @RequestMapping("/follow")
    public Response follow(@RequestHeader("id") int id, int followed) {
        followService.follow(id, followed);
        return Response.success(Code.FOLLOW, Code.FOLLOW_MESSAGE);
    }

    @RequestMapping("/getFollowedList")
    public DataResponse getFollowedList(@RequestHeader("id") int id) {
        List<Integer> followedList = followService.getFollowedList(id);
        return DataResponse.success(Code.FOLLOW_GET_FOLLOWED_LIST, Code.FOLLOW_GET_FOLLOWED_LIST_MESSAGE, followedList);
    }
}
