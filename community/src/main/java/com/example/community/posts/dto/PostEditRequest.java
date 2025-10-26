package com.example.community.posts.dto;

import lombok.Getter;

@Getter
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
}
