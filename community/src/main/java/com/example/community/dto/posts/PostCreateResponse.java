package com.example.community.dto.posts;

// domain.post.PostCreateResponse.java (Lombok 없이)

public class PostCreateResponse {

    private String message;
    private int postId;
    private String redirectUri;

    public PostCreateResponse(String message, int postId, String redirectUri) {
        this.message = message;
        this.postId = postId;
        this.redirectUri = redirectUri;
    }

    public static PostCreateResponse of(int postId) {
        return new PostCreateResponse(
                "게시글이 성공적으로 생성되었습니다.", // 반환 메시지
                postId,
                "/posts/" + postId
        );
    }

    public String getMessage() {return message;}
    public int getPostId() {return postId;}
    public String getRedirectUri() {return redirectUri;}
}
