package com.example.community.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class SimpleResponse {

    @Schema(description = "사용자 아이디", example = "1")
    private Long userId;
    @Schema(description = "사용자 닉네임", example = "test1")
    private String userNickname;

    public SimpleResponse() {}
    public SimpleResponse(Long userId, String userNickname) {
        this.userId = userId;
        this.userNickname = userNickname;
    }

    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public String getUserNickname() {return userNickname;}
    public void setUserNickname(String userNickname) {this.userNickname = userNickname;}
}
