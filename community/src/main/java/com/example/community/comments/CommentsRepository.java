package com.example.community.comments;

import com.example.community.comments.dto.CommentsEntity;
import com.example.community.repository.Repository;

import java.util.ArrayList;

public interface CommentsRepository extends Repository<CommentsEntity, Long> {
    ArrayList<CommentsEntity> getCommentsByPostId(Long postId);
    void editComment(Long commentId, String content);
}
