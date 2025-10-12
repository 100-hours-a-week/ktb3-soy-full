package com.example.community.service.posts;

import com.example.community.application.posts.PostItemAssembler;
import com.example.community.dto.posts.PagingMetaResponse;
import com.example.community.dto.posts.PostEntity;
import com.example.community.dto.posts.PostItemResponse;
import com.example.community.dto.posts.PostListResponse;
import com.example.community.dto.users.UserEntity;
import com.example.community.repository.posts.PostCsvRepository;
import com.example.community.repository.users.UserCsvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ViewPostListService {
    private PostCsvRepository postCsvRepository;
    private UserCsvRepository userCsvRepository;
    private PostItemAssembler postItemAssembler = new PostItemAssembler();

    @Autowired
    public ViewPostListService(PostCsvRepository postCsvRepository, UserCsvRepository userCsvRepository) {
        this.postCsvRepository = postCsvRepository;
        this.userCsvRepository = userCsvRepository;
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
