package com.example.community.dto.posts;

import com.example.community.dto.users.WriterSummary;

import java.io.Writer;

public class PostDetailResponse {
    private int postId;
    private String postTitle;
    private String postContent;
    private String postImgUrl;
    private String postCreatedAt;
    private PostCounts postCounts;
    private WriterSummary writerSummary;

    public PostDetailResponse(){}
    public PostDetailResponse(int postId, String postTitle, String postContent, String postImgUrl,
                              String postCreatedAt, PostCounts PostCounts, WriterSummary writerSummary) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postImgUrl = postImgUrl;
        this.postCreatedAt = postCreatedAt;
        this.postCounts = PostCounts;
        this.writerSummary = writerSummary;
    }

    public int getPostId() {return postId;}
    public void setPostId(int postId) {this.postId = postId;}
    public String getPostTitle() {return postTitle;}
    public void setPostTitle(String postTitle) {this.postTitle = postTitle;}
    public String getPostContent() {return postContent;}
    public void setPostContent(String postContent) {this.postContent = postContent;}
    public String getPostImgUrl() {return postImgUrl;}
    public void setPostImgUrl(String postImgUrl) {this.postImgUrl = postImgUrl;}
    public String getPostCreatedAt() {return postCreatedAt;}
    public void setPostCreatedAt(String postCreatedAt) {this.postCreatedAt = postCreatedAt;}
    public PostCounts getPostCounts() {return postCounts;}
    public void setPostCounts(PostCounts postCounts) {this.postCounts = postCounts;}
    public WriterSummary getWriterSummary() {return writerSummary;}
    public void setWriterSummary(WriterSummary writerSummary) {this.writerSummary = writerSummary;}
}
