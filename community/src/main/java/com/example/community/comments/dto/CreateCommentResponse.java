package com.example.community.comments.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "댓글 생성 응답 DTO")
public class CreateCommentResponse {

    @Schema(description = "댓글 내용", example = "comment1")
    private String message;

    @Schema(description = "댓글을 단 게시글 식별자", example = "1")
    private Long postId;

    @Schema(description = "생성 후 리다이렉트할 리소스 위치", example = "/api/posts/123")
    private String redirectUri;

    public CreateCommentResponse(String message, Long postId, String redirectUri) {
        this.message = message;
        this.postId = postId;
        this.redirectUri = redirectUri;
    }

    public static CreateCommentResponse of(Long postId) {
        return new CreateCommentResponse(
                "댓글이 성공적으로 생성되었습니다.", // 반환 메시지
                postId,
                "/posts/%d/comments".formatted(postId)
        );
    }

    public String getMessage() {return message;}
    public Long getPostId() {return postId;}
    public String getRedirectUri() {return redirectUri;}
}