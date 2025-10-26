package com.example.community.posts.entity;

import lombok.Getter;

@Getter
public class PostCounts {
    private Long likeCounts;
    private Long commentCounts;
    private Long viewCounts;

    private PostCounts(Long likeCounts, Long commentCounts, Long viewCounts) {
        this.likeCounts = likeCounts;
        this.commentCounts = commentCounts;
        this.viewCounts = viewCounts;
    }

    public static PostCounts of(Long likeCounts, Long commentCounts, Long viewCounts) {
        return new PostCounts(likeCounts, commentCounts, viewCounts);
    }
}
