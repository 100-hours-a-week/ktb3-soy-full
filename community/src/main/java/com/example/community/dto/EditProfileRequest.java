package com.example.community.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class EditProfileRequest {
    @NotBlank
    @Length(max=10)
    private String userNickname;
    private String userProfileImgUrl;
    public EditProfileRequest() {}
    public EditProfileRequest(String userNickname, String userProfileImgUrl) {
        this.userNickname = userNickname;
        this.userProfileImgUrl = userProfileImgUrl;
    }
    public String getUserNickname() {return userNickname;}
    public void setUserNickname(String userNickname) {this.userNickname = userNickname;}
    public String getUserProfileImgUrl() {return userProfileImgUrl;}
    public void setUserProfileImgUrl(String userProfileImgUrl) {this.userProfileImgUrl = userProfileImgUrl;}
}
