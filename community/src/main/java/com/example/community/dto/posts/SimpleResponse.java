package com.example.community.dto.posts;

public class SimpleResponse {
    private String message;
    private int postId;


    public SimpleResponse(String message, int postId) {
        this.message = message;
        this.postId = postId;
    }

    public static SimpleResponse of(String message, int postId) {
        return new SimpleResponse(
                message, // 반환 메시지
                postId
        );
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
