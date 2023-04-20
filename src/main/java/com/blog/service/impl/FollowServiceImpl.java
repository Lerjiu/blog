package com.blog.service.impl;

import com.blog.dao.FollowDao;
import com.blog.service.FollowService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    private FollowDao followDao;

    public FollowServiceImpl(FollowDao followDao) {
        this.followDao = followDao;
    }

    @Override
    public void follow(int follower, int followed) {
        followDao.follow(follower, followed);
    }

    @Override
    public List<Integer> getFollowedList(int follower) {
        return followDao.getFollowedList(follower);
    }
}
