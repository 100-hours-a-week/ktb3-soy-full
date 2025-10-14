package com.example.community.service;

import com.example.community.application.posts.PostAssembler;
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
    private PostAssembler postAssembler = new PostAssembler();
    private static final String DEFAULT_POST_IMG = "src/main/resources/images/defaultProfile.jpg";


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
        PostDetailResponse poseDetailResponse = postAssembler.toPublicdetailResponse(postEntity, writerEntity);
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
            PostItemResponse postItemResponse = postAssembler.toPostItemResponse(paginatedPost, user);
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

    public PostCreateResponse createPost(Long userId, PostCreateRequest postCreateRequest) {
        if (postCreateRequest.getPostImageUrl() == ""){
            postCreateRequest.setPostImageUrl(DEFAULT_POST_IMG);
        }

        UserEntity writerEntity = userCsvRepository.findNotDeletedById(userId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "게시글을 작성할 수 없습니다."));

        PostEntity postEntity = postAssembler.toEntity(postCreateRequest, userId);
        int postId = postCsvRepository.savePost(postEntity);
        return PostCreateResponse.of(postId);
    }

    public void validatePostEditRequest(PostEditRequest postEditRequest) {
        if((postEditRequest.getPostTitle()==null || postEditRequest.getPostTitle().isEmpty()) &&
                (postEditRequest.getPostContent()==null || postEditRequest.getPostContent().isEmpty()) &&
                (postEditRequest.getPostImageUrl()==null || postEditRequest.getPostImageUrl().isEmpty())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정할 내용이 없습니다.");
        }
    }

    public void editPostTitle(PostEntity postEntity, String newTitle) {
        if (newTitle != null & newTitle != ""){
            postEntity.setPostTitle(newTitle);
        }
    }

    public void editPostContent(PostEntity postEntity, String newContent) {
        if (newContent != null & newContent != ""){
            postEntity.setPostContent(newContent);
        }
    }

    public void editPostImgUrl(PostEntity postEntity, String newImageUrl) {
        if (newImageUrl != null & newImageUrl != ""){
            postEntity.setPostImgUrl(newImageUrl);
        }
    }

    public void ensureUserIsPostWriter(Long postWriterId, Long userId){
        if (postWriterId != userId){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "접근할 수 없는 게시글입니다.");
        }
    }

    public SimpleResponse editPost(int postId, Long userId, PostEditRequest postEditRequest) {
        PostEntity postEntity = postCsvRepository.findPostById(postId);
        ensureUserIsPostWriter(postEntity.getPostWriterId(), userId);
        validatePostEditRequest(postEditRequest);
        editPostTitle(postEntity, postEditRequest.getPostTitle());
        editPostContent(postEntity, postEditRequest.getPostContent());
        editPostImgUrl(postEntity, postEditRequest.getPostImageUrl());
        postCsvRepository.updatePost(postEntity);
        return SimpleResponse.of(
                "게시글 수정이 완료되었습니다." ,
                postId);
    }

    public SimpleResponse deletePost(int postId, Long userId) {
        PostEntity postEntity = postCsvRepository.findPostById(postId);
        ensureUserIsPostWriter(postEntity.getPostWriterId(), userId);
        postCsvRepository.deletePost(postId);
        return SimpleResponse.of(
                "게시글 삭제를 완료하였습니다.",
                postId
        );
    }

    public SimpleResponse likePost(int postId, Long userId) {
        PostEntity postEntity = postCsvRepository.findPostById(postId);
        postEntity.setPostLikeCounts(postEntity.getPostLikeCounts() + 1);
    }

}
