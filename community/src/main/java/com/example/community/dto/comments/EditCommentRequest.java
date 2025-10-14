package com.example.community.dto.comments;

public class EditCommentRequest {
    private String newCommentContent;
    public EditCommentRequest() {}
    public EditCommentRequest(String newCommentContent) {
        this.newCommentContent = newCommentContent;
    }
    public String getNewCommentContent() {return newCommentContent;}
    public void setNewCommentContent(String newCommentContent) {this.newCommentContent = newCommentContent;}
}
