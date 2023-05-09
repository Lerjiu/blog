package com.blog.service.impl;

import com.blog.dao.FollowDao;
import com.blog.dao.UserDao;
import com.blog.service.FollowService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    private FollowDao followDao;
    private UserDao userDao;

    public FollowServiceImpl(FollowDao followDao, UserDao userDao) {
        this.followDao = followDao;
        this.userDao = userDao;
    }

    @Override
    public void add(int follower, int followed) {
        followDao.add(follower, followed);
        userDao.addFollowerNum(followed);
        userDao.addFollowedNum(follower);
    }

    @Override
    public List<Integer> getUserFollowed(int follower) {
        return followDao.getFollowedList(follower);
    }

    @Override
    public void delete(int follower, int followed) {
        followDao.delete(follower, followed);
        userDao.subFollowerNum(followed);
        userDao.subFollowedNum(follower);
    }

    @Override
    public boolean checkUserFollow(int userId, int id) {
        return followDao.checkUserFollow(userId, id);
    }

    @Override
    public boolean checkPermission(int userId, int id) {
        return followDao.checkUserFollow(userId, id);
    }
}
