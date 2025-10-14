package com.example.community.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EditPasswordRequest {
    private static final String PW_REGEX = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}";

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

    public String getUserOldPassword() {return userOldPassword;}
    public void setUserOldPassword(String userOldPassword) {this.userOldPassword = userOldPassword;}
    public String getUserNewPassword() {return userNewPassword;}
    public void setUserNewPassword(String userNewPassword) {this.userNewPassword = userNewPassword;}
}
