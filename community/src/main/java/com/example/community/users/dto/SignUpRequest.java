package com.example.community.users.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Builder
@Getter
public class SignUpRequest {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String PW_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,20}$";


    @NotBlank
    @Pattern(regexp= EMAIL_REGEX, message = "이메일 형식이 올바르지 않습니다.")
    private String userEmail;

    @NotBlank()
    @Pattern(regexp = PW_REGEX, message = "비밀번호는 8자 이상 20자 이하, 대문자, 소문자, 특수문자 포함.")
    private String userPassword;

    @NotBlank
    @Length(max=10)
    private String userNickname;

    @URL(message="유효하지 않은 url입니다.")
    private String userProfileImgUrl;
}
