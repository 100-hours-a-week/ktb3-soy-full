package com.example.community.dto.likes;

public class PostLikeEntity {
    private Long postId;
    private Long userId;
    private String createdAt;

    public PostLikeEntity(){}
    public PostLikeEntity(Long postId, Long userId, String createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public Long getPostId() {return postId;}
    public void setPostId(Long postId) {this.postId = postId;}
    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public String getCreatedAt() {return createdAt;}
    public void setCreatedAt(String createdAt) {this.createdAt = createdAt;}

    @Override
    public String toString(){
        return "%d %d %s".formatted(postId, userId, createdAt);
    }
}
