package com.example.community.posts.dto;

import com.example.community.users.dto.WriterSummary;

public class PostItemResponse {
    private Long postId;
    private WriterSummary writerSummary;
    private String postTitle;
    private String postCreatedAt;
    private PostCounts postCounts;

    public PostItemResponse(Long postId, WriterSummary writerSummary, String postTitle, String postCreatedAt, PostCounts postCounts) {
        this.postId = postId;
        this.writerSummary = writerSummary;
        this.postTitle = postTitle;
        this.postCounts = postCounts;
        this.postCreatedAt = postCreatedAt;
    }

    public Long getPostId() {return postId;}
    public void setPostId(Long postId) {this.postId = postId;}
    public WriterSummary getWriterSummary() {return writerSummary;}
    public void setWriterSummary(WriterSummary writerSummary) {this.writerSummary = writerSummary;}
    public String getPostTitle() {return postTitle;}
    public void setPostTitle(String postTitle) {this.postTitle = postTitle;}
    public String getPostCreatedAt() {return postCreatedAt;}
    public void setPostCreatedAt(String postCreatedAt) {this.postCreatedAt = postCreatedAt;}
    public PostCounts getPostCounts() {return postCounts;}
    public void setPostCounts(PostCounts postCounts) {this.postCounts = postCounts;}
}
