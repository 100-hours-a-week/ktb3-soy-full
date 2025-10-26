package com.example.community.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Getter
public class EditProfileRequest {
    @NotBlank
    @Length(max=10)
    private String userNickname;
    @URL(message="유효하지 않은 url입니다.")
    private String userProfileImgUrl;
    public EditProfileRequest() {}
    public EditProfileRequest( String userNickname){
        this.userNickname = userNickname;
        this.userProfileImgUrl = "";
    }
    public EditProfileRequest(String userNickname, String userProfileImgUrl) {
        this.userNickname = userNickname;
        this.userProfileImgUrl = userProfileImgUrl;
    }
}
