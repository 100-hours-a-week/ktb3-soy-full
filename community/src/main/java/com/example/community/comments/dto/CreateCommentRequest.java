package com.example.community.comments.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateCommentRequest {
    @NotBlank
    private String commentContent;

    public CreateCommentRequest() {}
    public CreateCommentRequest(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentContent() {return commentContent;}
    public void setCommentContent(String commentContent) {this.commentContent = commentContent;}

}
