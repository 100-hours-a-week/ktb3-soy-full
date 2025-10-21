package com.example.community.likes.repository;

import com.example.community.likes.Contents;
import org.springframework.stereotype.Repository;

@Repository("commentLikeRepository")
public class CommentLikeCsvRepository extends LikeCsvRepository {
    @Override
    public void init() {
        this.dbPath = Contents.COMMENT.getDbPath();
        this.contentType = Contents.COMMENT.getContentType();
        initFromFile();
    }
}
