package com.example.community.dto;

public class SimpleResponse {

    private String userEmail;
    private String userNickname;

    public SimpleResponse() {}
    public SimpleResponse(String userEmail, String userNickname) {
        this.userEmail = userEmail;
        this.userNickname = userNickname;
    }

    public String getUserEmail() {return userEmail;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
    public String getUserNickname() {return userNickname;}
    public void setUserNickname(String userNickname) {this.userNickname = userNickname;}
}
