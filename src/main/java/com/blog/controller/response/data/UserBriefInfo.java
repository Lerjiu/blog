package com.blog.controller.response.data;

import com.blog.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserBriefInfo {
    private String name;
    private String avatar;
    private String description;
    private boolean followed;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public static List<UserBriefInfo> getUserBriefInfosFromUsers(List<User> users, List<Boolean> followedList) {
        List<UserBriefInfo> userBriefInfos = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            boolean followed = followedList.get(i);
            UserBriefInfo userBriefInfo = new UserBriefInfo();
            userBriefInfo.setName(user.getName());
            userBriefInfo.setAvatar(user.getAvatar());
            userBriefInfo.setDescription(user.getDescription());
            userBriefInfo.setFollowed(followed);
            userBriefInfos.add(userBriefInfo);
        }
        return userBriefInfos;
    }
}
