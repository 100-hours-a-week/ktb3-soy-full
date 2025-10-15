package com.example.community.posts.dto;

public class PostEntity {
    private Long postId;
    private Long postWriterId;
    private String postTitle;
    private String postContent;
    private String postImgUrl;
    private Long postLikeCounts;
    private Long postViewCounts;
    private Long postCommentCounts;
    private String postCreatedAt;

    public PostEntity() {}
    public PostEntity(Long postId, Long postWriterId, String postTitle, String postContent,
                      String postImgUrl, Long postLikeCounts, Long postViewCounts, Long postCommentCounts, String postCreatedAt) {
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

    public Long getPostId() {return postId;}
    public void setPostId(Long postId) {this.postId = postId;}
    public Long getPostWriterId() {return postWriterId;}
    public void setPostWriterId(Long postWriterId) {this.postWriterId = postWriterId;}
    public String getPostTitle() {return postTitle;}
    public void setPostTitle(String postTitle) {this.postTitle = postTitle;}
    public String getPostContent() {return postContent;}
    public void setPostContent(String postContent) {this.postContent = postContent;}
    public String getPostImgUrl() {return postImgUrl;}
    public void setPostImgUrl(String postImgUrl) {this.postImgUrl = postImgUrl;}
    public Long getPostLikeCounts() {return postLikeCounts;}
    public void setPostLikeCounts(Long postLikeCounts) {this.postLikeCounts = postLikeCounts;}
    public Long getPostViewCounts() {return postViewCounts;}
    public void setPostViewCounts(Long postViewCounts) {this.postViewCounts = postViewCounts;}
    public Long getPostCommentCounts() {return postCommentCounts;}
    public void setPostCommentCounts(Long postCommentCounts) {this.postCommentCounts = postCommentCounts;}
    public String getPostCreatedAt() {return postCreatedAt;}
    public void setPostCreatedAt(String postCreatedAt) {this.postCreatedAt = postCreatedAt;}

    @Override
    public String toString(){
        return "Post %d %s".formatted(postId, postCreatedAt);
    }
}