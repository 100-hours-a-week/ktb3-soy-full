package com.example.community.likes.service;

import com.example.community.Utility;
import com.example.community.comments.CommentsCsvRepository;
import com.example.community.likes.dto.SimpleResponse;
import com.example.community.likes.entity.LikeEntity;
import com.example.community.likes.repository.LikeCsvRepository;
import com.example.community.posts.PostCsvRepository;
import com.example.community.posts.dto.PostEntity;
import com.example.community.users.UserCsvRepository;
import com.example.community.users.UserException;
import com.example.community.users.dto.UserEntity;

import java.util.Optional;

public abstract class LikeService {
    public PostCsvRepository postCsvRepository;
    public CommentsCsvRepository commentsCsvRepository;
    public UserCsvRepository userCsvRepository;
    public LikeCsvRepository likeCsvRepository;
    private Utility utility = new Utility();
    protected String contentType;
    public LikeService(){}

    public void validateUser(Long userId) {
        UserEntity userEntity = userCsvRepository.findById(userId).orElseThrow(
                () -> new UserException.UserNotFoundException("존재하지 않는 유저입니다.")
        );
        if (userEntity.getUserIsDeleted()){
            throw new UserException.UserNotFoundException("존재하지 않는 유저입니다.");
        }
    }

    public abstract void validateContent(Long contentId);

    public SimpleResponse like(Long contentId, Long userId) {
        validateUser(userId);
        validateContent(contentId);
        String createdAt = utility.getCreatedAt();
        LikeEntity likeEntity = new LikeEntity(
                contentId,
                userId,
                createdAt
        );
        likeCsvRepository.save(likeEntity);
        return SimpleResponse.forLike(
                this.contentType,
                contentId,
                userId
        );
    }

    public SimpleResponse unlike(Long contentId, Long userId) {
        validateUser(userId);
        validateContent(contentId);
        likeCsvRepository.deleteByContentAndUserID(contentId, userId);
        return SimpleResponse.forUnlike(
                this.contentType,
                contentId,
                userId
        );
    }

}
