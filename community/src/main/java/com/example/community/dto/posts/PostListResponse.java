package com.example.community.dto.posts;

import java.util.List;

public class PostListResponse {
    private List<PostItemResponse> postItemResponseList;
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
