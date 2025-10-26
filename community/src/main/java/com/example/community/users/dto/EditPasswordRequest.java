package com.example.community.users.dto;

import com.example.community.users.UsersConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class EditPasswordRequest {
    @NotBlank
    private String userOldPassword;

    @NotBlank
    @Pattern(regexp = UsersConstants.REGEX_PASSWORD, message = "비밀번호는 8자 이상 20자 이하, 대문자, 소문자, 특수문자 포함.")
    private String userNewPassword;
    public EditPasswordRequest() {}
    public EditPasswordRequest(String userOldPassword, String userNewPassword) {
        this.userOldPassword = userOldPassword;
        this.userNewPassword = userNewPassword;
    }
}
