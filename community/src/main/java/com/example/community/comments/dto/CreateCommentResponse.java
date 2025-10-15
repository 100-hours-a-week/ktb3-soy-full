package com.example.community.comments.dto;

public class CreateCommentResponse {
    private String message;
    private Long postId;
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