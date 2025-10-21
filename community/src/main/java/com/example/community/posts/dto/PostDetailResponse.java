package com.example.community.posts.dto;

import com.example.community.users.dto.WriterSummary;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시글 상세 조회 응답 DTO")
public class PostDetailResponse {

    @Schema(description = "게시글 식별자", example = "101")
    private Long postId;

    @Schema(description = "게시글 제목", example = "title")
    private String postTitle;

    @Schema(description = "게시글 내용", example = "content")
    private String postContent;

    @Schema(description = "게시글 사진", example = "https://example.com/images/post-101.png")
    private String postImgUrl;

    @Schema(description = "게시글 생성일시", example = "202510101010")
    private String postCreatedAt;

    @Schema(description = "게시글 개수 정보", example = "{likes:10, views:10, comments:10}")
    private PostCounts postCounts;

    @Schema(description = "게시글 작성자 정보", example = "{userID: 1, userNickname: test1, userProfileImgUrl: 'https://example.com/users/1/profile-image.png'}")
    private WriterSummary writerSummary;

    public PostDetailResponse(){}
    public PostDetailResponse(Long postId, String postTitle, String postContent, String postImgUrl,
                              String postCreatedAt, PostCounts PostCounts, WriterSummary writerSummary) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postImgUrl = postImgUrl;
        this.postCreatedAt = postCreatedAt;
        this.postCounts = PostCounts;
        this.writerSummary = writerSummary;
    }

    public Long getPostId() {return postId;}
    public void setPostId(Long postId) {this.postId = postId;}
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
