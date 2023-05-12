package com.blog.controller.response.data;

import com.blog.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserBriefInfo {
    private String name;
    private String avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static List<UserBriefInfo> getUserBriefInfosFromUsers(List<User> users) {
        List<UserBriefInfo> userBriefInfos = new ArrayList<>();
        for (User user : users) {
            UserBriefInfo userBriefInfo = new UserBriefInfo();
            userBriefInfo.setName(user.getName());
            userBriefInfo.setAvatar(user.getAvatar());
            userBriefInfos.add(userBriefInfo);
        }
        return userBriefInfos;
    }
}
