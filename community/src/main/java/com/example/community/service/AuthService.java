package com.example.community.service;

import com.example.community.dto.SignInRequest;
import com.example.community.dto.SignInResponse;
import com.example.community.dto.UserEntity;
import com.example.community.exception.UserUnauthorizedException;
import com.example.community.repository.UserCsvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private UserCsvRepository repository;

    @Autowired
    public AuthService(UserCsvRepository repository) {
        this.repository = repository;
    }

    private String getAccessToken(){
        return "accessTokenTest";
    }

    private String getRefreshToken(){
        return "refreshTokenTest";
    }

    private String[] getIssueAndExpirationTimes(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return new String[]{dtf.format(now), dtf.format(now.plusDays(7))};
    }

    private Map<String,String> getAuthInfoMap(){
        Map<String,String> authInfoMap = new HashMap<>();
        authInfoMap.put("accessToken",getAccessToken());
        authInfoMap.put("refreshToken",getRefreshToken());
        String[] timestamp = getIssueAndExpirationTimes();
        authInfoMap.put("issuedAt",timestamp[0]);
        authInfoMap.put("expiresIn",timestamp[1]);
        return authInfoMap;
    }

    public UserEntity findUserByEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "존재하지 않는 이메일입니다."
                ));
    }

    public void verifyPassword(UserEntity userEntity, String givenPassword){
        if(!userEntity.isPasswordMatch(givenPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호입니다.");
        }
    }

    public SignInResponse signIn(SignInRequest signInRequest) {

        String email = signInRequest.getUserEmail();
        String password = signInRequest.getUserPassword();
        UserEntity userEntity = findUserByEmail(email);
        verifyPassword(userEntity, password);
        Map<String,String> authInfoMap = getAuthInfoMap();

        return new SignInResponse(
                userEntity.getUserEmail(),
                authInfoMap.get("accessToken"),
                authInfoMap.get("refreshToken"),
                authInfoMap.get("issuedAt"),
                authInfoMap.get("expiresIn")
        );
    }

}
