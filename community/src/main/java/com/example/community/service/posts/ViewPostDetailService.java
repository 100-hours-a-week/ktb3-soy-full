package com.example.community.service.posts;

import com.example.community.application.posts.PostDetailAssembler;
import com.example.community.dto.posts.PostDetailResponse;
import com.example.community.dto.posts.PostEntity;
import com.example.community.dto.users.UserEntity;
import com.example.community.repository.posts.PostCsvRepository;
import com.example.community.repository.users.UserCsvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ViewPostDetailService {
    private PostCsvRepository postcsvRepository;
    private UserCsvRepository usercsvRepository;
    private PostDetailAssembler postDetailAssembler = new PostDetailAssembler();

    @Autowired
    public ViewPostDetailService(PostCsvRepository postcsvRepository, UserCsvRepository usercsvRepository) {
        this.postcsvRepository = postcsvRepository;
        this.usercsvRepository = usercsvRepository;
    }

    private void verifyUser(UserEntity userEntity) {
        if (userEntity.getUserIsDeleted()){
            throw new ResponseStatusException(HttpStatus.GONE, "게시글을 찾을 수 없습니다.");
        }
    }

    public PostDetailResponse viewPostDetail(int postId) {
        PostEntity postEntity = postcsvRepository.findPostById(postId);
        UserEntity writerEntity = usercsvRepository.findById(postEntity.getPostWriterId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."));
        verifyUser(writerEntity);
        PostDetailResponse poseDetailResponse = postDetailAssembler.toPublicdetailResponse(postEntity, writerEntity);
        return poseDetailResponse;
    }
}
