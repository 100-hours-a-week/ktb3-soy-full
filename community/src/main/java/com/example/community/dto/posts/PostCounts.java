package com.example.community.dto.posts;

public class PostCounts {
    private int likeCounts;
    private int commentCounts;
    private int viewCounts;

    public PostCounts(int likeCounts, int commentCounts, int viewCounts) {
        this.likeCounts = likeCounts;
        this.commentCounts = commentCounts;
        this.viewCounts = viewCounts;
    }

    public int getLikeCounts() {return likeCounts;}
    public void setLikeCounts(int likeCounts) {this.likeCounts = likeCounts;}
    public int getCommentCounts() {return commentCounts;}
    public void setCommentCounts(int commentCounts) {this.commentCounts = commentCounts;}
    public int getViewCounts() {return viewCounts;}
    public void setViewCounts(int viewCounts) {this.viewCounts = viewCounts;}
}
