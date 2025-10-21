package com.example.community.comments.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "게시글 보기 응답 dto")
public class CommentsViewResponse {
    @Schema(description = "게시글 엔티티 리스트")
    private List<CommentsEntity> commentsEntities;
    public CommentsViewResponse() {}
    public CommentsViewResponse(List<CommentsEntity> commentsEntities) {
        this.commentsEntities = commentsEntities;
    }

    public List<CommentsEntity> getCommentsEntities() {return this.commentsEntities;}
    public void setCommentsEntities(List<CommentsEntity> commentsEntities) {}
}
