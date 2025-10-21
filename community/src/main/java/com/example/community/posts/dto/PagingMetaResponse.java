package com.example.community.posts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시글 페이징 메타데이터")
public class PagingMetaResponse {

    @Schema(description = "현재 페이지 번호 (0부터 시작)", example = "0")
    private Long pageNumber;

    @Schema(description = "한 페이지에 포함되는 게시글 수", example = "20")
    private Long pageSize = 20L;

    @Schema(description = "전체 게시글 수", example = "156")
    private Long totalPosts;

    @Schema(description = "전체 페이지 수", example = "8")
    private Long totalPages;

    @Schema(description = "정렬 기준 (예: createdAt,desc)", example = "createdAt,desc")
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
