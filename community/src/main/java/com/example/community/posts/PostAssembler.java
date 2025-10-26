package com.example.community.posts;

import com.example.community.Utility;
import com.example.community.posts.dto.*;
import com.example.community.posts.entity.PostCounts;
import com.example.community.posts.entity.PostEntity;
import com.example.community.users.entity.UserEntity;
import com.example.community.users.entity.WriterSummary;

public class PostAssembler {
    Utility util = new Utility();
    public PostAssembler(){}
    public PostEntity toEntity(PostCreateRequest postCreateRequest, Long userId) {
        return PostEntity.builder()
                .postTitle(postCreateRequest.getPostTitle())
                .postContent(postCreateRequest.getPostContent())
                .postImgUrl(postCreateRequest.getPostImageUrl())
                .postWriterId(userId)
                .postLikeCounts(0L)
                .postCommentCounts(0L)
                .postViewCounts(0L)
                .postCreatedAt(util.getCreatedAt())
                .build();
    }

    public PostDetailResponse toDetailResponse(PostEntity postEntity, UserEntity userEntity) {
        WriterSummary writerSummary = WriterSummary.create(
                userEntity.getUserId(),
                userEntity.getUserNickname(),
                userEntity.getUserProfileImgUrl()
        );

        PostCounts postCounts = PostCounts.of(
                postEntity.getPostLikeCounts(),
                postEntity.getPostCommentCounts(),
                postEntity.getPostViewCounts()
        );

        return PostDetailResponse.builder()
                .postId(postEntity.getPostId())
                .postTitle(postEntity.getPostTitle())
                .postContent(postEntity.getPostContent())
                .postImgUrl(postEntity.getPostImgUrl())
                .postCreatedAt(postEntity.getPostCreatedAt())
                .postCounts(postCounts)
                .writerSummary(writerSummary)
                .build();
    }

    public PostItemResponse toPostItemResponse(PostEntity postEntity, UserEntity userEntity) {
        WriterSummary writerSummary = WriterSummary.create(
                userEntity.getUserId(),
                userEntity.getUserNickname(),
                userEntity.getUserProfileImgUrl()
        );

        PostCounts postCounts = PostCounts.of(
                postEntity.getPostLikeCounts(),
                postEntity.getPostCommentCounts(),
                postEntity.getPostViewCounts()
        );

        PostItemResponse postItemResponse = PostItemResponse.builder()
                .postId(postEntity.getPostId())
                .writerSummary(writerSummary)
                .postTitle(postEntity.getPostTitle())
                .postCreatedAt(postEntity.getPostCreatedAt())
                .postCounts(postCounts)
                .build();

        return postItemResponse;
    }
}
