package com.example.community.likes.repository;

import com.example.community.likes.entity.LikeEntity;

public interface LikeRepository {
    LikeEntity save(LikeEntity entity);
    boolean existsByContentAndUserId(Long contentId, Long userId);
    void deleteByContentAndUserId(Long contentId, Long userId);
}
