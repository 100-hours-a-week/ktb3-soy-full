package com.example.community.users.dto;

public class SignUpResponse {
    private String userEmail;
    private String userNickname;
    private String userCreatedAt;

    public SignUpResponse(){}

    public SignUpResponse(String userEmail, String userNickname, String userCreatedAt) {
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userCreatedAt = userCreatedAt;
    }

    public String getUserEmail() {return userEmail;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
    public String getUserNickname() {return userNickname;}
    public void setUserNickname(String userNickname) {this.userNickname = userNickname;}
    public String getUserCreatedAt() {return userCreatedAt;}
    public void setUserCreatedAt(String userCreatedAt) {this.userCreatedAt = userCreatedAt;}
}
