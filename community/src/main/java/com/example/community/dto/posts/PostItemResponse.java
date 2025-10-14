package com.example.community.dto.posts;

import com.example.community.dto.users.WriterSummary;

import java.time.LocalDateTime;

public class PostItemResponse {
    private int postId;
    private WriterSummary writerSummary;
    private String postTitle;
    private String postCreatedAt;
    private PostCounts postCounts;

    public PostItemResponse(int postId, WriterSummary writerSummary, String postTitle, String postCreatedAt, PostCounts postCounts) {
        this.postId = postId;
        this.writerSummary = writerSummary;
        this.postTitle = postTitle;
        this.postCounts = postCounts;
        this.postCreatedAt = postCreatedAt;
    }

    public int getPostId() {return postId;}
    public void setPostId(int postId) {this.postId = postId;}
    public WriterSummary getWriterSummary() {return writerSummary;}
    public void setWriterSummary(WriterSummary writerSummary) {this.writerSummary = writerSummary;}
    public String getPostTitle() {return postTitle;}
    public void setPostTitle(String postTitle) {this.postTitle = postTitle;}
    public String getPostCreatedAt() {return postCreatedAt;}
    public void setPostCreatedAt(String postCreatedAt) {this.postCreatedAt = postCreatedAt;}
    public PostCounts getPostCounts() {return postCounts;}
    public void setPostCounts(PostCounts postCounts) {this.postCounts = postCounts;}
}
