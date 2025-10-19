package com.example.community.likes;

import com.example.community.Utility;
import com.example.community.common.dto.SimpleResponse;
import com.example.community.likes.dto.PostLikeEntity;
import com.example.community.users.UserException;
import com.example.community.users.dto.UserEntity;
import com.example.community.posts.PostCsvRepository;
import com.example.community.users.UserCsvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return postLikeCsvRepository.existsByPostAndUser(postId, userId);
    }

    private String getId(Long postId, Long userId) {
        return postId + "-" + userId;
    }

    public UserEntity findUserById(Long userId) {
        return userCsvRepository.findById(userId)
                .orElseThrow(() -> new UserException.UserNotFoundException("존재하지 않는 사용자입니다."));
    }

    public void validateUser(Long userId){
        UserEntity userEntity = findUserById(userId);
        if(userEntity.getUserIsDeleted()){
            throw new UserException.UserNotFoundException("존재하지 않는 사용자입니다.");
        }
    }

    public SimpleResponse likePost(Long postId, Long userId){
        validateUser(userId);
        if (checkUserLikePost(postId, userId)){
            throw new LikeException.AlreadyLikedException("이미 좋아요한 게시글입니다.");
        }
        PostLikeEntity postLikeEntity = new PostLikeEntity(postId, userId, utility.getCreatedAt());
        postLikeCsvRepository.save(postLikeEntity);
        postCsvRepository.incrementLikeCount(postId);
        return SimpleResponse.forLikePost(postId, userId);
    }

    public SimpleResponse dislikePost(Long postId, Long userId){
        validateUser(userId);
        if(!checkUserLikePost(postId, userId)){
            throw new LikeException.AlreadyLikedException("이미 좋아하지 않는 게시글입니다.");
        }
        String id = getId(postId, userId);
        postLikeCsvRepository.delete(Long.parseLong(id));
        postCsvRepository.decrementLikeCount(postId);
        return SimpleResponse.forDislikePost(postId, userId);
    }

}
