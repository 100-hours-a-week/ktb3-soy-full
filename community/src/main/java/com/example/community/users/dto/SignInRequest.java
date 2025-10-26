package com.example.community.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
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
}
