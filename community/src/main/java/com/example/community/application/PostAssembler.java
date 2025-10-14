package com.example.community.application;

import com.example.community.Utility;
import com.example.community.dto.posts.*;
import com.example.community.dto.users.UserEntity;
import com.example.community.dto.users.WriterSummary;

public class PostAssembler {
    Utility util = new Utility();
    public PostAssembler(){}
    public PostEntity toEntity(PostCreateRequest postCreateRequest, Long userId) {
        PostEntity postEntity = new PostEntity();
        postEntity.setPostTitle(postCreateRequest.getPostTitle());
        postEntity.setPostContent(postCreateRequest.getPostContent());
        postEntity.setPostImgUrl(postCreateRequest.getPostImageUrl());
        postEntity.setPostWriterId(userId);
        postEntity.setPostLikeCounts(0L);
        postEntity.setPostCommentCounts(0L);
        postEntity.setPostViewCounts(0L);
        postEntity.setPostCreatedAt(util.getCreatedAt());
        return postEntity;
    }

    public PostDetailResponse toPublicdetailResponse(PostEntity postEntity, UserEntity userEntity) {
        WriterSummary writerSummary = new WriterSummary(
                userEntity.getUserId(),
                userEntity.getUserNickname(),
                userEntity.getUserProfileImgUrl()
        );

        PostCounts postCounts = new PostCounts(
                postEntity.getPostLikeCounts(),
                postEntity.getPostCommentCounts(),
                postEntity.getPostViewCounts()
        );

        return new PostDetailResponse(
                postEntity.getPostId(),
                postEntity.getPostTitle(),
                postEntity.getPostContent(),
                postEntity.getPostImgUrl(),
                postEntity.getPostCreatedAt(),
                postCounts,
                writerSummary
        );
    }

    public PostItemResponse toPostItemResponse(PostEntity postEntity, UserEntity userEntity) {
        WriterSummary writerSummary = new WriterSummary(
                userEntity.getUserId(),
                userEntity.getUserNickname(),
                userEntity.getUserProfileImgUrl()
        );

        PostCounts postCounts = new PostCounts(
                postEntity.getPostLikeCounts(),
                postEntity.getPostCommentCounts(),
                postEntity.getPostViewCounts()
        );

        PostItemResponse postItemResponse = new PostItemResponse(
                postEntity.getPostId(),
                writerSummary,
                postEntity.getPostTitle(),
                postEntity.getPostCreatedAt(),
                postCounts
        );

        return postItemResponse;
    }
}
