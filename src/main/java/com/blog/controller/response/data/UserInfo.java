package com.blog.controller.response.data;

import com.blog.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserInfo {
    private String name;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    private String description;
    private boolean sex;
    private String avatar;
    private int articleNum;
    private int followerNum;
    private int followedNum;
    private int defaultFavoriteFolder;
    private boolean followed;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(int articleNum) {
        this.articleNum = articleNum;
    }

    public int getFollowerNum() {
        return followerNum;
    }

    public void setFollowerNum(int followerNum) {
        this.followerNum = followerNum;
    }

    public int getFollowedNum() {
        return followedNum;
    }

    public void setFollowedNum(int followedNum) {
        this.followedNum = followedNum;
    }

    public int getDefaultFavoriteFolder() {
        return defaultFavoriteFolder;
    }

    public void setDefaultFavoriteFolder(int defaultFavoriteFolder) {
        this.defaultFavoriteFolder = defaultFavoriteFolder;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public static UserInfo getUserInfoFromUser(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(user.getName());
        userInfo.setEmail(user.getEmail());
        userInfo.setBirthday(user.getBirthday());
        userInfo.setDescription(user.getDescription());
        userInfo.setSex(user.isSex());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setArticleNum(user.getArticleNum());
        userInfo.setFollowerNum(user.getFollowerNum());
        userInfo.setFollowedNum(user.getFollowedNum());
        userInfo.setDefaultFavoriteFolder(user.getDefaultFavoriteFolder());
        return userInfo;
    }

    public static UserInfo getUserInfoFromUser(User user, boolean followed) {
        UserInfo userInfo = getUserInfoFromUser(user);
        userInfo.setFollowed(followed);
        return userInfo;
    }
}
