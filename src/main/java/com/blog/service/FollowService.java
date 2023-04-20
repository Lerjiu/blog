package com.blog.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FollowService {
    void follow(int follower, int followed);
    List<Integer> getFollowedList(int follower);
}
