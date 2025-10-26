package com.example.community.comments.dto;

import lombok.Getter;

@Getter
public class EditCommentRequest {
    private String newCommentContent;
    public EditCommentRequest() {}
    public EditCommentRequest(String newCommentContent) {
        this.newCommentContent = newCommentContent;
    }
}
