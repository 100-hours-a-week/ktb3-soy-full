package com.example.community.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignInRequest {

    @NotBlank
    @Email
    private String userEmail;

    @NotBlank
    private String userPassword;

    public SignInRequest() {}
    public SignInRequest(String email, String password) {
        this.userEmail = email;
        this.userPassword = password;
    }

    public String getUserEmail() {return userEmail;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
    public String getUserPassword() {return userPassword;}
    public void setUserPassword(String userPassword) {this.userPassword = userPassword;}
}
