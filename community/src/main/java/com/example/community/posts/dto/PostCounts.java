package com.example.community.posts.dto;

public class PostCounts {
    private Long likeCounts;
    private Long commentCounts;
    private Long viewCounts;

    public PostCounts(Long likeCounts, Long commentCounts, Long viewCounts) {
        this.likeCounts = likeCounts;
        this.commentCounts = commentCounts;
        this.viewCounts = viewCounts;
    }

    public Long getLikeCounts() {return likeCounts;}
    public void setLikeCounts(Long likeCounts) {this.likeCounts = likeCounts;}
    public Long getCommentCounts() {return commentCounts;}
    public void setCommentCounts(Long commentCounts) {this.commentCounts = commentCounts;}
    public Long getViewCounts() {return viewCounts;}
    public void setViewCounts(Long viewCounts) {this.viewCounts = viewCounts;}
}
