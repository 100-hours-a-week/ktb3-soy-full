package com.example.community.posts;

import com.example.community.posts.dto.*;
import com.example.community.users.UserException;
import com.example.community.users.dto.UserEntity;
import com.example.community.users.UserCsvRepository;
import com.example.community.common.dto.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
            throw new PostException.PostGoneException("게시글을 찾을 수 없습니다.");
        }
    }

    public UserEntity findUserById(Long id){
        return userCsvRepository.findById(id).orElseThrow(()-> new UserException.UserNotFoundException("존재하지 않는 사용자입니다."));
    }

    public PostEntity findPostById(Long id){
        return postCsvRepository.findById(id)
                .orElseThrow(() -> new PostException.PostNotFoundException("존재하지 않는 게시글입니다."));
    }

    public void validatePost(Long postId) {
        if (!postCsvRepository.existsById(postId)){
            throw new PostException.PostNotFoundException("존재하지 않는 게시글입니다.");
        }
    }

    public PostDetailResponse viewPostDetail(Long postId) {
        validatePost(postId);
        PostEntity postEntity = findPostById(postId);
        UserEntity writerEntity = findUserById(postEntity.getPostWriterId());
        verifyUser(writerEntity);
        PostDetailResponse poseDetailResponse = postAssembler.toDetailResponse(postEntity, writerEntity);
        return poseDetailResponse;
    }

    private Long[] getTotalPostsAndPages(Long pageSize){
        Long totalPosts = (long) userCsvRepository.userStore.size();
        Long totalPages = (totalPosts + pageSize - 1) / pageSize;
        return new Long[]{totalPosts, totalPages};
    }
    private void verifyPagination(Long totalPosts, Long totalPages, Long pageNumber, Long pageSize) {
        if (pageSize <= 0 || pageNumber <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "페이지 정보를 확인하세요.");
        }

        if (totalPages < pageNumber) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "페이지 정보를 확인하세요.");
        }
    }

    private List<PostItemResponse> getPostItemResponseList(List<PostEntity> postEntityList){
        List<Long> uniqueWriterIds = postEntityList.stream().map(PostEntity::getPostWriterId).collect(Collectors.toList());
        Map<Long, UserEntity> uniqueWriterMap = userCsvRepository.findAllByIds(uniqueWriterIds)
                .stream().collect(Collectors.toMap(UserEntity::getUserId, Function.identity()));
        return postEntityList.stream()
                .map(post -> postAssembler.toPostItemResponse(post, uniqueWriterMap.get(post.getPostWriterId())))
                .toList();
    }

    private List<Long> extractWriterIds(List<PostEntity> postEntityList){
        return postEntityList.stream()
                .map(PostEntity::getPostWriterId)
                .collect(Collectors.toList());
    }

    private Long[] getPageId(Long pageNumber, Long pageSize, Long totalPosts){
        Long postStartId = (Long) (pageNumber - 1) * pageSize;
        Long postEndId = (Long) Math.min(postStartId + pageSize, totalPosts);
        return new Long[]{postStartId, postEndId};
    }

    public PostListResponse viewPostList(Long pageNumber, Long pageSize) {
        Long[] pageInfo = getTotalPostsAndPages(pageSize); // 서비스 계층으로 바꾸기
        Long totalPosts = pageInfo[0];
        Long totalPages = pageInfo[1];

        Long[] pageIds = getPageId(pageNumber, pageSize, totalPosts);
        Long startPageId = pageIds[0];
        Long endPageId = pageIds[1];

        verifyPagination(totalPages, totalPosts, pageNumber, pageSize);

        List<PostEntity> paginatedPosts = postCsvRepository.findPagedPosts(startPageId, endPageId);
        List<PostItemResponse> postItemResponseList = getPostItemResponseList(paginatedPosts);

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
                .orElseThrow(() -> new PostException.PostNotAuthorizedException("게시글을 작성할 수 없습니다."));

        PostEntity postEntity = postAssembler.toEntity(postCreateRequest, userId);
        postCsvRepository.save(postEntity);
        Long postId = postEntity.getPostId();
        return PostCreateResponse.of(postId);
    }

    public void validatePostEditRequest(PostEditRequest postEditRequest) {
        if((postEditRequest.getPostTitle()==null || postEditRequest.getPostTitle().isEmpty()) &&
                (postEditRequest.getPostContent()==null || postEditRequest.getPostContent().isEmpty()) &&
                (postEditRequest.getPostImageUrl()==null || postEditRequest.getPostImageUrl().isEmpty())){
            throw new PostException.NoEditPostException("수정할 내용이 없습니다.");
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
            throw new PostException.PostNotAuthorizedException("접근할 수 없는 게시글입니다.");
        }
    }

    public SimpleResponse editPost(Long postId, Long userId, PostEditRequest postEditRequest) {
        validatePost(postId);
        PostEntity postEntity = findPostById(postId);
        ensureUserIsPostWriter(postEntity.getPostWriterId(), userId);
        validatePostEditRequest(postEditRequest);
        editPostTitle(postEntity, postEditRequest.getPostTitle());
        editPostContent(postEntity, postEditRequest.getPostContent());
        editPostImgUrl(postEntity, postEditRequest.getPostImageUrl());
        postCsvRepository.edit(postEntity);
        return SimpleResponse.forEditPost(userId, postId);
    }

    public SimpleResponse deletePost(Long postId, Long userId) {
        validatePost(postId);
        PostEntity postEntity = findPostById(postId);
        ensureUserIsPostWriter(postEntity.getPostWriterId(), userId);
        postCsvRepository.delete(postId);
        return SimpleResponse.forDeletePost(userId, postId);
    }
}
