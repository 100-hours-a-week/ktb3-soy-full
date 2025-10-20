package com.example.community.likes;

import com.example.community.Utility;
import com.example.community.likes.dto.PostLikeEntity;
import com.example.community.posts.PostCsvRepository;
import com.example.community.users.UserCsvRepository;
import com.example.community.users.UserException;
import com.example.community.common.dto.SimpleResponse;
import com.example.community.users.dto.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("postLikeService")
public class PostLikeService implements LikeService {

    Utility utility = new Utility();
    PostCsvRepository postCsvRepository;
    PostLikeCsvRepository postLikeCsvRepository;
    UserCsvRepository userCsvRepository;

    @Autowired
    public PostLikeService(PostCsvRepository postCsvRepository,
                        UserCsvRepository userCsvRepository,
                        PostLikeCsvRepository postLikeCsvRepository) {
        this.userCsvRepository = userCsvRepository;
        this.postLikeCsvRepository = postLikeCsvRepository;
        this.postCsvRepository = postCsvRepository;
    }

    public UserEntity findUserById(Long userId) {
        return userCsvRepository.findById(userId)
                .orElseThrow(() -> new UserException.UserNotFoundException("존재하지 않는 사용자입니다."));
    }

    @Override
    public String getId(Long postId, Long userId) {
        return postId + "-" + userId;
    }

    public void validateUser(Long userId){
        UserEntity userEntity = findUserById(userId);
        if (userEntity.getUserIsDeleted()) {
            throw new UserException.UserNotFoundException("존재하지 않는 사용자입니다.");
        }
    }

    @Override
    public SimpleResponse like(Long postId, Long userId) {
        validateUser(userId);
        if (checkUserLike(postId, userId)){
            throw new LikeException.AlreadyLikedException("이미 좋아요한 게시글입니다.");
        }
        PostLikeEntity postLikeEntity = new PostLikeEntity(postId, userId, utility.getCreatedAt());
        postLikeCsvRepository.save(postLikeEntity);
        postCsvRepository.incrementLikeCount(postId);
        return com.example.community.common.dto.SimpleResponse.forLikePost(postId, userId);

    }

    @Override
    public SimpleResponse dislike(Long postId, Long userId) {
        validateUser(userId);
        if(!checkUserLike(postId, userId)){
            throw new LikeException.AlreadyLikedException("이미 좋아하지 않는 게시글입니다.");
        }
        String id = getId(postId, userId);
        postLikeCsvRepository.delete(Long.parseLong(id));
        postCsvRepository.decrementLikeCount(postId);
        return com.example.community.common.dto.SimpleResponse.forDislikePost(postId, userId);
    }

    @Override
    public Boolean checkUserLike(Long postId, Long userId) {
        return postLikeCsvRepository.existsByPostAndUser(postId, userId);
    }
}
