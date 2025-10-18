package com.example.community.likes;

import com.example.community.likes.dto.PostLikeEntity;
import com.example.community.repository.Repository;

import java.util.Optional;

public interface PostLikeRepository extends Repository<PostLikeEntity, Long> {
    Optional<PostLikeEntity> findByPostAndUser(Long postId, Long userId);
    void deleteByPostAndUser(Long postId, Long userId);
    boolean existsByPostAndUser(Long postId, Long userId);
}
