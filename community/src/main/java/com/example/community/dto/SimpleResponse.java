package com.example.community.dto;

public class SimpleResponse {

    private Long userId;
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
