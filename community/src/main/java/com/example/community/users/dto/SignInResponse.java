package com.example.community.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class SignInResponse {
    @Schema(description = "사용자 이메일", example = "test1@gmail.com")
    private String userEmail;
    @Schema(description = "사용자 로그인 일시", example = "202510212147")
    private String userIssuedAt;
    @Schema(description = "사용자 로그인 만료 일시", example = "202510282147")
    private String userExpiresIn;

    public SignInResponse() {}
    public SignInResponse(String email, String issuedAt, String expiresIn) {
        this.userEmail = email;
        this.userIssuedAt = issuedAt;
        this.userExpiresIn = expiresIn;
    }

    public String getUserIssuedAt() {return userIssuedAt;}
    public void setUserIssuedAt(String userIssuedAt) {this.userIssuedAt = userIssuedAt;}
    public String getUserExpiresIn() {return userExpiresIn;}
    public void setUserExpiresIn(String userExpiresIn) {this.userExpiresIn = userExpiresIn;}
    public String getUserEmail() {return userEmail;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
}
