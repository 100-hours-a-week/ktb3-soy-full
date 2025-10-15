package com.example.community.users.dto;

public class SignInResponse {
    private String userEmail;
    private String userAccessToken;
    private String userRefreshToken;
    private String userIssuedAt;
    private String userExpiresIn;

    public SignInResponse() {}
    public SignInResponse(String email, String accessToken, String refreshToken, String issuedAt, String expiresIn) {
        this.userEmail = email;
        this.userAccessToken = accessToken;
        this.userRefreshToken = refreshToken;
        this.userIssuedAt = issuedAt;
        this.userExpiresIn = expiresIn;
    }

    public String getUserAccessToken() {return userAccessToken;}
    public void setUserAccessToken(String userAccessToken) {this.userAccessToken = userAccessToken;}
    public String getUserRefreshToken() {return userRefreshToken;}
    public void setUserRefreshToken(String userRefreshToken) {this.userRefreshToken = userRefreshToken;}
    public String getUserIssuedAt() {return userIssuedAt;}
    public void setUserIssuedAt(String userIssuedAt) {this.userIssuedAt = userIssuedAt;}
    public String getUserExpiresIn() {return userExpiresIn;}
    public void setUserExpiresIn(String userExpiresIn) {this.userExpiresIn = userExpiresIn;}
    public String getUserEmail() {return userEmail;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
}
