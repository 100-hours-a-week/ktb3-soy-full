package com.example.community.likes.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeEntity {
    private Long contentId;
    private Long userId;
    private String createdAt;

    private LikeEntity(Long contentId, Long userId, String createdAt) {
        this.contentId = contentId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public static LikeEntity of(Long contentId, Long userId, String createdAt) {
        return new LikeEntity(contentId, userId, createdAt);
    }

}
