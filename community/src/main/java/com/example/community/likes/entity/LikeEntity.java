package com.example.community.likes.entity;

public class LikeEntity {
    private Long contentId;
    private Long userId;
    private String createdAt;

    public LikeEntity(Long contentId, Long userId, String createdAt) {
        this.contentId = contentId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public LikeEntity() {}

    public Long getContentId() {return contentId;}
    public Long getUserId() {return userId;}
    public String getCreatedAt() {return createdAt;}
}
