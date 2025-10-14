package com.example.community.service;

import com.example.community.Utility;
import com.example.community.dto.SimpleResponse;
import com.example.community.dto.likes.PostLikeEntity;
import com.example.community.dto.users.UserEntity;
import com.example.community.repository.PostCsvRepository;
import com.example.community.repository.PostLikeCsvRepository;
import com.example.community.repository.UserCsvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LikesService {
    Utility utility = new Utility();
    PostCsvRepository postCsvRepository;
    PostLikeCsvRepository postLikeCsvRepository;
    UserCsvRepository userCsvRepository;

    @Autowired
    public LikesService(PostCsvRepository postCsvRepository,
                        UserCsvRepository userCsvRepository,
                        PostLikeCsvRepository postLikeCsvRepository) {
        this.userCsvRepository = userCsvRepository;
        this.postLikeCsvRepository = postLikeCsvRepository;
        this.postCsvRepository = postCsvRepository;
    }

    public Boolean checkUserLikePost(Long postId, Long userId) {
        return postLikeCsvRepository.checkUserLikePost(postId, userId);
    }

    public void validateUser(Long userId){
        UserEntity userEntity = userCsvRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자 정보를 찾을 수 없습니다."));
        if(userEntity.getUserIsDeleted()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자 정보를 찾을 수 없습니다.");
        }
    }

    public SimpleResponse likePost(Long postId, Long userId){
        validateUser(userId);
        if (checkUserLikePost(postId, userId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 좋아요한 게시글입니다.");
        }
        postLikeCsvRepository.likePost(postId, userId, utility.getCreatedAt());
        postCsvRepository.likePost(postId);
        return SimpleResponse.forLikePost(postId, userId);
    }

    public SimpleResponse dislikePost(Long postId, Long userId){
        validateUser(userId);
        if(!checkUserLikePost(postId, userId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 좋아하지 않는 게시글입니다.");
        }
        postLikeCsvRepository.dislikePost(postId, userId);
        postCsvRepository.dislikePost(postId);
        return SimpleResponse.forDislikePost(postId, userId);
    }

}
