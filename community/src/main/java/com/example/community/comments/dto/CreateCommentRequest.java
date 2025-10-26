package com.example.community.comments.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    @NotBlank
    private String commentContent;

    public CreateCommentRequest() {}
    public CreateCommentRequest(String commentContent) {
        this.commentContent = commentContent;
    }
}
