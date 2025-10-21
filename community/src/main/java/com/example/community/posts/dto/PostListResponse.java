package com.example.community.posts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "게시글 목록보기 DTO")
public class PostListResponse {

    @Schema(description = "게시글 리스트")
    private List<PostItemResponse> postItemResponseList;
    @Schema(description = "게시글 페이징 메타데이터")
    private PagingMetaResponse pagingMeta;

    public PostListResponse() {}
    public PostListResponse(List<PostItemResponse> postItemResponseList, PagingMetaResponse pagingMeta) {
        this.postItemResponseList = postItemResponseList;
        this.pagingMeta = pagingMeta;
    }

    public List<PostItemResponse> getPostItemResponseList() {return postItemResponseList;}
    public void setPostItemResponseList(List<PostItemResponse> postItemResponseList) {this.postItemResponseList = postItemResponseList;}
    public PagingMetaResponse getPagingMeta() {return pagingMeta;}
    public void setPagingMeta(PagingMetaResponse pagingMeta) {this.pagingMeta = pagingMeta;}
}
