package com.example.community.posts.dto;

import com.example.community.users.dto.WriterSummary;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시글 아이템 DTO")
public class PostItemResponse {

    @Schema(description = "게시글 식별자", example = "101")
    private Long postId;

    @Schema(description = "게시글 작성자 정보", example = "{userID: 1, userNickname: test1, userProfileImgUrl: 'https://example.com/users/1/profile-image.png'}")
    private WriterSummary writerSummary;

    @Schema(description = "게시글 제목", example = "title")
    private String postTitle;

    @Schema(description = "게시글 생성일시", example = "202510101010")
    private String postCreatedAt;

    @Schema(description = "게시글 개수 정보", example = "{likes:10, views:10, comments:10}")
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
