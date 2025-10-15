package com.example.community.posts.dto;

public class PostEditRequest {
    private String postTitle;
    private String postContent;
    private String postImageUrl;

    public PostEditRequest() {}
    public PostEditRequest(String postTitle, String postContent, String postImageUrl) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postImageUrl = postImageUrl;
    }

    public String getPostTitle() {return postTitle;}
    public void setPostTitle(String postTitle) {this.postTitle = postTitle;}
    public String getPostContent() {return postContent;}
    public void setPostContent(String postContent) {this.postContent = postContent;}
    public String getPostImageUrl() {return postImageUrl;}
    public void setPostImageUrl(String postImageUrl) {this.postImageUrl = postImageUrl;}
}
