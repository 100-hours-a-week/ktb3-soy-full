package com.example.community.comments.dto;

public class CommentsEntity {
    private Long commentId;
    private Long parentCommentId;
    private Long postId;
    private Long commentWriterId;
    private String commentContent;
    private String commentCreatedAt;

    public CommentsEntity(){}
    public CommentsEntity(Long commentId, Long parentCommentId, Long postId, Long commentWriterId, String commentContent, String commentCreatedAt) {
        this.commentId = commentId;
        this.parentCommentId = parentCommentId;
        this.postId = postId;
        this.commentWriterId = commentWriterId;
        this.commentContent = commentContent;
        this.commentCreatedAt = commentCreatedAt;
    }
    public Long getCommentId() {return commentId;}
    public void setCommentId(Long commentId) {this.commentId = commentId;}
    public Long getParentCommentId() {return parentCommentId;}
    public void setParentCommentId(Long parentCommentId) {this.parentCommentId = parentCommentId;}
    public Long getPostId() {return postId;}
    public void setPostId(Long postId) {this.postId = postId;}
    public Long getCommentWriterId() {return commentWriterId;}
    public void setCommentWriterId(Long commentWriterId) {this.commentWriterId = commentWriterId;}
    public String getCommentContent() {return commentContent;}
    public void setCommentContent(String commentContent) {this.commentContent = commentContent;}
    public String getCommentCreatedAt() {return commentCreatedAt;}
    public void setCommentCreatedAt(String commentCreatedAt) {this.commentCreatedAt = commentCreatedAt;}
}
