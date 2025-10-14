package com.example.community.application;

import com.example.community.Utility;
import com.example.community.dto.comments.CommentsEntity;

public class CommentAssembler {
    Utility util = new Utility();
    public CommentAssembler(){}
    public CommentsEntity toEntity(Long postId,
                                   Long userId,
                                   Long parentId,
                                   String commentContent){
        CommentsEntity commentsEntity = new CommentsEntity();
        commentsEntity.setPostId(postId);
        commentsEntity.setCommentWriterId(userId);
        commentsEntity.setParentCommentId(parentId);
        commentsEntity.setCommentContent(commentContent);
        commentsEntity.setCommentCreatedAt(util.getCreatedAt());
        return commentsEntity;
    }
}
