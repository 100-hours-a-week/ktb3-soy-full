package com.example.community.likes.dto;

public class SimpleResponse {
    private String contentType;
    private Long contentId;
    private Long userId;

    public SimpleResponse() {}

    public SimpleResponse(String contentType, Long contentId, Long userId) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.userId = userId;
    }

    public static SimpleResponse forLike(String contentType, Long contentId, Long userId) {
        return new SimpleResponse("Like " + contentType, contentId, userId);
    }

    public static SimpleResponse forUnlike(String contentType, Long contentId, Long userId) {
        return new SimpleResponse("Unlike " + contentType, contentId, userId);
    }

    public String getContentType() {return contentType;}
    public Long getContentId() {return contentId;}
    public Long getUserId() {return userId;}
}
