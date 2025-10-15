package com.example.community.comments.dto;

public class EditCommentRequest {
    private String newCommentContent;
    public EditCommentRequest() {}
    public EditCommentRequest(String newCommentContent) {
        this.newCommentContent = newCommentContent;
    }
    public String getNewCommentContent() {return newCommentContent;}
    public void setNewCommentContent(String newCommentContent) {this.newCommentContent = newCommentContent;}
}
