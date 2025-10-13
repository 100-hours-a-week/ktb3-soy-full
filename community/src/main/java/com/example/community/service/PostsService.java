package com.example.community.service;

import com.example.community.application.posts.PostDetailAssembler;
import com.example.community.dto.posts.*;
import com.example.community.dto.users.UserEntity;
import com.example.community.repository.PostCsvRepository;
import com.example.community.repository.users.UserCsvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostsService {
    private PostCsvRepository postCsvRepository;
    private UserCsvRepository userCsvRepository;
    private PostDetailAssembler postDetailAssembler = new PostDetailAssembler();

    @Autowired
    public PostsService(PostCsvRepository postCsvRepository, UserCsvRepository userCsvRepository) {
        this.postCsvRepository = postCsvRepository;
        this.userCsvRepository = userCsvRepository;
    }

    private void verifyUser(UserEntity userEntity) {
        if (userEntity.getUserIsDeleted()){
            throw new ResponseStatusException(HttpStatus.GONE, "게시글을 찾을 수 없습니다.");
        }
    }

    public PostDetailResponse viewPostDetail(int postId) {
        PostEntity postEntity = postCsvRepository.findPostById(postId);
        UserEntity writerEntity = userCsvRepository.findById(postEntity.getPostWriterId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."));
        verifyUser(writerEntity);
        PostDetailResponse poseDetailResponse = postDetailAssembler.toPublicdetailResponse(postEntity, writerEntity);
        return poseDetailResponse;
    }

    private int[] getTotalPostsAndPages(int pageSize){
        int totalPosts = userCsvRepository.userStore.size();
        int totalPages = (int) Math.ceil((double)totalPosts/(double)pageSize);
        return new int[]{totalPosts, totalPages};
    }

    private void verifyPagination(int totalPosts, int totalPages, int pageNumber, int pageSize) {
        if (pageSize <= 0 || pageNumber <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "페이지 정보를 확인하세요.");
        }

        if (totalPages < pageNumber) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "페이지 정보를 확인하세요.");
        }
    }

    private List<PostItemResponse> getPostItemResponseList(List<PostEntity> postEntityList, List<Long> writerIds){
        List<PostItemResponse> postItemResponseList = new ArrayList<>();

        for (int index = 0; index<postEntityList.size(); index++) {
            UserEntity user = userCsvRepository.findById(writerIds.get(index)).get();
            PostEntity paginatedPost = postEntityList.get(index);
            PostItemResponse postItemResponse = postItemAssembler.toPostItemResponse(paginatedPost, user);
            postItemResponseList.add(postItemResponse);
        }

        return postItemResponseList;
    }

    private List<Long> extractWriterIds(List<PostEntity> postEntityList){
        return postEntityList.stream()
                .map(PostEntity::getPostWriterId)
                .collect(Collectors.toList());
    }

    public PostListResponse viewPostList(int pageNumber, int pageSize) {
        int[] pageInfo = getTotalPostsAndPages(pageSize); // 서비스 계층으로 바꾸기
        int totalPosts = pageInfo[0];
        int totalPages = pageInfo[1];

        verifyPagination(totalPages, totalPosts, pageNumber, pageSize);

        List<PostEntity> paginatedPosts = postCsvRepository.findPageOfPosts(pageNumber, pageSize);
        List<Long> writerIds = extractWriterIds(paginatedPosts);
        List<PostItemResponse> postItemResponseList = getPostItemResponseList(paginatedPosts, writerIds);

        PagingMetaResponse pagingMetaResponse = new PagingMetaResponse(
                pageNumber, pageSize, totalPosts, totalPages, "createdAt,desc"
        );

        return new PostListResponse(
                postItemResponseList,
                pagingMetaResponse
        );

    }
}
