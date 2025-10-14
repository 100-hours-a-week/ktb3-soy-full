package com.example.community.dto.users;

public class WriterSummary {
    private long userId;
    private String userNickname;
    private String userProfileImgUrl;

    public WriterSummary() {}

    public WriterSummary(long userId, String userNickname, String userProfileImgUrl) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userProfileImgUrl = userProfileImgUrl;
    }
    public long getUserId() {return userId;}
    public void setUserId(long userId) {this.userId = userId;}
    public String getUserNickname() {return userNickname;}
    public void setUserNickname(String userNickname) {this.userNickname = userNickname;}
    public String getUserProfileImgUrl() {return userProfileImgUrl;}
    public void setUserProfileImgUrl(String userProfileImgUrl) {this.userProfileImgUrl = userProfileImgUrl;}
}
