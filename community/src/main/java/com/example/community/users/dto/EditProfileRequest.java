package com.example.community.users.dto;

import com.example.community.users.UsersConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.apache.catalina.User;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Getter
public class EditProfileRequest {
    @NotBlank
    @Length(max= UsersConstants.NICKNAME_MAX_LEN)
    private String userNickname;
    @URL(message= UsersConstants.MSG_URL_NOT_VALID)
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
