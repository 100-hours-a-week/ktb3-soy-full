package com.example.community.users.dto;
import com.example.community.users.UsersConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Builder
@Getter
public class SignUpRequest {
    @NotBlank
    @Pattern(regexp= UsersConstants.REGEX_EMAIL, message = UsersConstants.MSG_EMAIL_NOT_VALID)
    private String userEmail;

    @NotBlank()
    @Pattern(regexp = UsersConstants.REGEX_EMAIL, message = UsersConstants.MSG_PW_NOT_VALID)
    private String userPassword;

    @NotBlank
    @Length(max=10)
    private String userNickname;

    @URL(message= UsersConstants.MSG_URL_NOT_VALID)
    private String userProfileImgUrl;
}
