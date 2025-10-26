package com.example.community.comments.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentsEntity {
    private Long commentId;
    private Long parentCommentId;
    private Long postId;
    private Long commentWriterId;
    private String commentContent;
    private String commentCreatedAt;

    public void updateId(Long commentId) {
        this.commentId = commentId;
    }

    public void updateContent(String commentContent) {
        this.commentContent = commentContent;
    }

}
