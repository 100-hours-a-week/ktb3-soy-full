package com.example.community.dto.posts;

import java.time.LocalDateTime;

public class PostEntity {
    private Integer postId;
    private Long postWriterId;
    private String postTitle;
    private String postContent;
    private String postImgUrl;
    private Integer postLikeCounts;
    private Integer postViewCounts;
    private Integer postCommentCounts;
    private String postCreatedAt;

    public PostEntity() {}
    public PostEntity(Integer postId, Long postWriterId, String postTitle, String postContent,
                      String postImgUrl, Integer postLikeCounts, Integer postViewCounts, Integer postCommentCounts, String postCreatedAt) {
        this.postId = postId;
        this.postWriterId = postWriterId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postImgUrl = postImgUrl;
        this.postLikeCounts = postLikeCounts;
        this.postViewCounts = postViewCounts;
        this.postCommentCounts = postCommentCounts;
        this.postCreatedAt = postCreatedAt;
    }

    public Integer getPostId() {return postId;}
    public void setPostId(Integer postId) {this.postId = postId;}
    public Long getPostWriterId() {return postWriterId;}
    public void setPostWriterId(Long postWriterId) {this.postWriterId = postWriterId;}
    public String getPostTitle() {return postTitle;}
    public void setPostTitle(String postTitle) {this.postTitle = postTitle;}
    public String getPostContent() {return postContent;}
    public void setPostContent(String postContent) {this.postContent = postContent;}
    public String getPostImgUrl() {return postImgUrl;}
    public void setPostImgUrl(String postImgUrl) {this.postImgUrl = postImgUrl;}
    public Integer getPostLikeCounts() {return postLikeCounts;}
    public void setPostLikeCounts(Integer postLikeCounts) {this.postLikeCounts = postLikeCounts;}
    public Integer getPostViewCounts() {return postViewCounts;}
    public void setPostViewCounts(Integer postViewCounts) {this.postViewCounts = postViewCounts;}
    public Integer getPostCommentCounts() {return postCommentCounts;}
    public void setPostCommentCounts(Integer postCommentCounts) {this.postCommentCounts = postCommentCounts;}
    public String getPostCreatedAt() {return postCreatedAt;}
    public void setPostCreatedAt(String postCreatedAt) {this.postCreatedAt = postCreatedAt;}

    @Override
    public String toString(){
        return "Post %d %s".formatted(postId, postCreatedAt);
    }
}