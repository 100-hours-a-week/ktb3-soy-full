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

    private String[] getTimestamp(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return new String[]{dtf.format(now), dtf.format(now.plusDays(7))};
    }

    private Map<String,String> getAuthInfoMap(){
        Map<String,String> authInfoMap = new HashMap<>();
        authInfoMap.put("accessToken",getAccessToken());
        authInfoMap.put("refreshToken",getRefreshToken());
        String[] timestamp = getTimestamp();
        authInfoMap.put("issuedAt",timestamp[0]);
        authInfoMap.put("expiresIn",timestamp[1]);
        return authInfoMap;
    }

    public SignInResponse signIn(SignInRequest signInRequest) {

        String email = signInRequest.getUserEmail();
        String password = signInRequest.getUserPassword();

        UserEntity userEntity = repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "존재하지 않는 이메일입니다."
                ));

        if(!userEntity.getUserPassword().equals(password)){
            throw new UserUnauthorizedException("비밀번호가 다릅니다.");
        };


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
