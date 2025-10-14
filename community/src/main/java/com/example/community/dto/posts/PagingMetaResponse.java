package com.example.community.dto.posts;

public class PagingMetaResponse {
    private int pageNumber;
    private int pageSize = 20;
    private int totalPosts;
    private int totalPages;
    private String sortCondition = "createdAt,desc";

    public PagingMetaResponse(){}
    public PagingMetaResponse(int pageNumber, int pageSize, int totalPosts, int totalPages, String sortCondition) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPosts = totalPosts;
        this.totalPages = totalPages;
        this.sortCondition = sortCondition;
    }

    public int getPageNumber() {return pageNumber;}
    public void setPageNumber(int pageNumber) {this.pageNumber = pageNumber;}
    public int getPageSize() {return pageSize;}
    public void setPageSize(int pageSize) {this.pageSize = pageSize;}
    public int getTotalPosts() {return totalPosts;}
    public void setTotalPosts(int totalPosts) {this.totalPosts = totalPosts;}
    public int getTotalPages() {return totalPages;}
    public void setTotalPages(int totalPages) {this.totalPages = totalPages;}
    public String getSortCondition() {return sortCondition;}
    public void setSortCondition(String sortCondition) {this.sortCondition = sortCondition;}
}
