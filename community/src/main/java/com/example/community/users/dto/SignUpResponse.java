package com.example.community.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SignUpResponse {
    @Schema(description = "사용자 이메일", example = "test1@gmail.com")
    private String userEmail;
    @Schema(description = "사용자 닉네임", example = "test1")
    private String userNickname;
    @Schema(description = "사용자 생성일시", example = "202510212147")
    private String userCreatedAt;

    private SignUpResponse(String userEmail, String userNickname, String userCreatedAt) {
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userCreatedAt = userCreatedAt;
    }

    public static SignUpResponse create(String email, String nickname, String createdAt) {
        return new SignUpResponse(email, nickname, createdAt);
    }
}
