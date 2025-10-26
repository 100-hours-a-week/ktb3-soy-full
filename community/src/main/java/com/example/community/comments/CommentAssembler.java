package com.example.community.comments;

import com.example.community.Utility;
import com.example.community.comments.dto.CommentsEntity;

public class CommentAssembler {
    public static CommentsEntity toEntity(Long postId,
                                   Long userId,
                                   Long parentId,
                                   String commentContent){
        return CommentsEntity.builder()
                .postId(postId)
                .commentWriterId(userId)
                .parentCommentId(parentId)
                .commentContent(commentContent)
                .build();
    }
}
