package com.example.community.posts.dto;

public class PagingMetaResponse {
    private Long pageNumber;
    private Long pageSize = 20L;
    private Long totalPosts;
    private Long totalPages;
    private String sortCondition = "createdAt,desc";

    public PagingMetaResponse(){}
    public PagingMetaResponse(Long pageNumber, Long pageSize, Long totalPosts,
                              Long totalPages, String sortCondition) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPosts = totalPosts;
        this.totalPages = totalPages;
        this.sortCondition = sortCondition;
    }

    public Long getPageNumber() {return pageNumber;}
    public void setPageNumber(Long pageNumber) {this.pageNumber = pageNumber;}
    public Long getPageSize() {return pageSize;}
    public void setPageSize(Long pageSize) {this.pageSize = pageSize;}
    public Long getTotalPosts() {return totalPosts;}
    public void setTotalPosts(Long totalPosts) {this.totalPosts = totalPosts;}
    public Long getTotalPages() {return totalPages;}
    public void setTotalPages(Long totalPages) {this.totalPages = totalPages;}
    public String getSortCondition() {return sortCondition;}
    public void setSortCondition(String sortCondition) {this.sortCondition = sortCondition;}
}
