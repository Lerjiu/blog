package com.blog.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FollowService extends PermissionService {
    void add(int follower, int followed);
    List<Integer> getUserFollowed(int follower);
    List<Integer> getUserFollower(int followed);
    void delete(int follower, int followed);
    boolean checkUserFollow(int follower, int followed);
}
