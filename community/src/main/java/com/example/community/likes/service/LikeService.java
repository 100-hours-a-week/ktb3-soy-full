package com.example.community.likes.service;

import com.example.community.Utility;
import com.example.community.comments.CommentsCsvRepository;
import com.example.community.likes.dto.SimpleResponse;
import com.example.community.likes.entity.LikeEntity;
import com.example.community.likes.repository.LikeCsvRepository;
import com.example.community.posts.PostCsvRepository;
import com.example.community.users.UserCsvRepository;
import com.example.community.users.UserException;
import com.example.community.users.entity.UserEntity;
import com.example.community.validator.DomainValidator;

public abstract class LikeService {
    public PostCsvRepository postCsvRepository;
    public CommentsCsvRepository commentsCsvRepository;
    public UserCsvRepository userCsvRepository;
    public LikeCsvRepository likeCsvRepository;
    public DomainValidator domainValidator;

    protected String contentType;
    public LikeService(){}

    public abstract void validateContent(Long contentId);

    public SimpleResponse like(Long contentId, Long userId) {
        domainValidator.validateUserExistById(userId);
        validateContent(contentId);
        String createdAt = Utility.getCreatedAt();
        LikeEntity likeEntity = LikeEntity.of(
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
        domainValidator.validateUserExistById(userId);
        validateContent(contentId);
        likeCsvRepository.deleteByContentAndUserID(contentId, userId);
        return SimpleResponse.forUnlike(
                this.contentType,
                contentId,
                userId
        );
    }

}
