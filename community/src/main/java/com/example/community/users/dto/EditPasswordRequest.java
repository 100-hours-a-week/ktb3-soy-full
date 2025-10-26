package com.example.community.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class EditPasswordRequest {
    private static final String PW_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,20}$";

    @NotBlank
    private String userOldPassword;

    @NotBlank
    @Pattern(regexp = PW_REGEX, message = "비밀번호는 8자 이상 20자 이하, 대문자, 소문자, 특수문자 포함.")
    private String userNewPassword;
    public EditPasswordRequest() {}
    public EditPasswordRequest(String userOldPassword, String userNewPassword) {
        this.userOldPassword = userOldPassword;
        this.userNewPassword = userNewPassword;
    }
}
