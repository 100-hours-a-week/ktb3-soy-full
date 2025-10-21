package com.example.community.posts;

import com.example.community.posts.dto.PostEntity;
import com.example.community.repository.Repository;

import java.util.List;

public interface PostRepository extends Repository<PostEntity, Long> {
    void edit(PostEntity postEntity);
    void incrementLikeCount(Long postId);
    void decrementLikeCount(Long postId);
    List<PostEntity> findPagedPosts(Long startPageId, Long endPageId);
}
