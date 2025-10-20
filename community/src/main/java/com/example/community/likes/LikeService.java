package com.example.community.likes;

import com.example.community.common.dto.SimpleResponse;

public interface LikeService {
    SimpleResponse like(Long contentId, Long userId);
    SimpleResponse dislike(Long contentId, Long userId);
    Boolean checkUserLike(Long contentId, Long userId);
    String getId(Long contentId, Long userId);
}
