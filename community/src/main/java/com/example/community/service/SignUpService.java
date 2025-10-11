package com.example.community.service;

import com.example.community.dto.SignUpRequest;
import com.example.community.dto.SignUpResponse;
import com.example.community.dto.UserEntity;
import com.example.community.repository.UserCsvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SignUpService {
    private UserCsvRepository repository;
    private static final String DEFAULT_PROFILE_IMG = "src/main/resources/images/defaultProfile.jpg";

    @Autowired
    public SignUpService(UserCsvRepository userCsvRepository) {
        this.repository = userCsvRepository;
    }

    private String getCreatedAt(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private boolean isEmailExist(String email) {
        return repository.findByEmail(email).isPresent();
    }

    private boolean isNicknameExist(String nickname) {
        return repository.findByNickname(nickname).isPresent();
    }

    private String checkAndSetProfileImage(String profileImgUrl) {
        if (profileImgUrl.equals("")) {
            return DEFAULT_PROFILE_IMG;
        } else {
            return profileImgUrl;
        }
    }

    public SignUpResponse signup(SignUpRequest signUpRequest) {
        String email = signUpRequest.getUserEmail();
        String password = signUpRequest.getUserPassword();
        String nickname = signUpRequest.getUserNickname();
        String profileImgUrl = checkAndSetProfileImage(signUpRequest.getUserProfileImgUrl());
        String createdAt = getCreatedAt();

        nickname = nickname.trim();
        password = password.trim();
        email = email.trim();

        if (isEmailExist(email)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.");
        }

        if(isNicknameExist(nickname)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다.");
        }

        // 데이터 저장
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmail(email);
        userEntity.setUserPassword(password);
        userEntity.setUserNickname(nickname);
        userEntity.setUserProfileImgUrl(profileImgUrl);
        userEntity.setUserCreatedAt(createdAt);
        userEntity.setUserIsDeleted(false);
        repository.save(userEntity);

        return new SignUpResponse(email, nickname, createdAt);
    }

}
