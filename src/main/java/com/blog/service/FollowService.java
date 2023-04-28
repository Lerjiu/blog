package com.blog.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FollowService extends PermissionService {
    void add(int follower, int followed);
    List<Integer> getUserFollowed(int follower);
    void delete(int follower, int followed);
}
