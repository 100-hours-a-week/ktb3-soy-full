package com.example.community.users.dto;

public class SignInResponse {
    private String userEmail;
    private String userIssuedAt;
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
