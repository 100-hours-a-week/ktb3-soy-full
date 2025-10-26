package com.example.community.users;

public class UsersConstants {
    public static final int NICKNAME_MAX_LEN = 20;

    public static final String PATH_DB = "src/main/resources/data/users.csv";
    public static final String PATH_DEFAULT_PROFILE = "src/main/resources/images/defaultProfile.jpg";

    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,20}$";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public static final String MSG_URL_NOT_VALID = "유효하지 않은 url입니다.";
    public static final String MSG_EMAIL_NOT_VALID = "유효하지 않은 이메일 형식입니다.";
    public static final String MSG_PW_NOT_VALID = "비밀번호는 8자 이상 20자 이하, 대문자, 소문자, 특수문자 포함해야 합니다.";
}
