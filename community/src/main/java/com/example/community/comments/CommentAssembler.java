package com.example.community.comments;

import com.example.community.Utility;
import com.example.community.comments.dto.CommentsEntity;

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
