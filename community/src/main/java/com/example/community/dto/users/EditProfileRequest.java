package com.example.community.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

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
    public String getUserNickname() {return userNickname;}
    public void setUserNickname(String userNickname) {this.userNickname = userNickname;}
    public String getUserProfileImgUrl() {if (userProfileImgUrl != null) return userProfileImgUrl; else return "";}
    public void setUserProfileImgUrl(String userProfileImgUrl) {this.userProfileImgUrl = userProfileImgUrl;}
}
