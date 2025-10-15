package com.example.community.comments.dto;

import java.util.List;

public class CommentsViewResponse {
    private List<CommentsEntity> commentsEntities;
    public CommentsViewResponse() {}
    public CommentsViewResponse(List<CommentsEntity> commentsEntities) {
        this.commentsEntities = commentsEntities;
    }

    public List<CommentsEntity> getCommentsEntities() {return this.commentsEntities;}
    public void setCommentsEntities(List<CommentsEntity> commentsEntities) {}
}
