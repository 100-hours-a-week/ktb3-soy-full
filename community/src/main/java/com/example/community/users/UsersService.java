package com.example.community.users;

import com.example.community.users.dto.*;
import com.example.community.users.entity.UserEntity;
import com.example.community.validator.DomainValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.community.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsersService {
    private UserCsvRepository repository;
    private DomainValidator domainValidator;
    @Autowired
    public UsersService(
            UserCsvRepository userCsvRepository,
            DomainValidator domainValidator) {
        this.repository = userCsvRepository;
        this.domainValidator = domainValidator;
    }

    private boolean isEmailExist(String email) {
        return repository.findByEmail(email).isPresent();
    }

    private boolean isNicknameExist(String nickname) {
        return repository.findByNickname(nickname).isPresent();
    }

    private String checkAndSetProfileImage(String profileImgUrl) {
        if (profileImgUrl == null || profileImgUrl.isBlank()) {
            return UsersConstants.PATH_DEFAULT_PROFILE;
        }
        return profileImgUrl;
    }

    public SignUpResponse signup(SignUpRequest signUpRequest) {
        String email = signUpRequest.getUserEmail();
        String password = signUpRequest.getUserPassword();
        String nickname = signUpRequest.getUserNickname();
        String profileImgUrl = checkAndSetProfileImage(signUpRequest.getUserProfileImgUrl());
        String createdAt = Utility.getCreatedAt();

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
        UserEntity userEntity = UserEntity.builder()
                .userEmail(email)
                .userPassword(password)
                .userNickname(nickname)
                .userProfileImgUrl(profileImgUrl)
                .userCreatedAt(createdAt)
                .userIsDeleted(false).build();
        repository.save(userEntity);

        return SignUpResponse.create(email, nickname, createdAt);
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
        String[] timestamp = getIssueAndExpirationTimes();
        authInfoMap.put("issuedAt",timestamp[0]);
        authInfoMap.put("expiresIn",timestamp[1]);
        return authInfoMap;
    }

    public void verifyPassword(UserEntity userEntity, String givenPassword){
        if(!userEntity.isPasswordMatch(givenPassword)){
            throw new UserException.WrongPasswordException("잘못된 비밀번호입니다.");
        }
    }

    private UserEntity getUserEntityByEmail(String email){
        UserEntity userEntity = repository.findByEmail(email)
                .orElseThrow(() -> new UserException.UserNotFoundException("존재하지 않는 사용자입니다."));

        if (userEntity.getUserIsDeleted()){
            throw new UserException.UserNotFoundException("탈퇴한 유저입니다.");
        }
        return userEntity;
    }

    private UserEntity getUserEntityById(Long id){
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new UserException.UserNotFoundException("존재하지 않는 사용자입니다."));

        if (userEntity.getUserIsDeleted()){
            throw new UserException.UserNotFoundException("탈퇴한 유저입니다.");
        }
        return userEntity;
    }

    public SignInResponse signIn(SignInRequest signInRequest) {

        String email = signInRequest.getUserEmail();
        String password = signInRequest.getUserPassword();

        UserEntity userEntity = getUserEntityByEmail(email);
        verifyPassword(userEntity, password);

        Map<String,String> authInfoMap = getAuthInfoMap();

        return new SignInResponse(
                userEntity.getUserEmail(),
                authInfoMap.get("issuedAt"),
                authInfoMap.get("expiresIn")
        );
    }

    public SimpleResponse editPassword(Long id, EditPasswordRequest editPasswordRequest) {
        String oldPassword = editPasswordRequest.getUserOldPassword();
        String newPassword = editPasswordRequest.getUserNewPassword();

        UserEntity userEntity = getUserEntityById(id);

        String realOldPassword = userEntity.getUserPassword();

        if (!oldPassword.equals(realOldPassword)) {
            throw new UserException.InvalidCurrentPasswordException("잘못된 비밀번호입니다.");
        }

        if (newPassword.equals(oldPassword)) {
            throw new UserException.SamePasswordException("현재 비밀번호와 새 비밀번호가 동일합니다.");
        }

        repository.editPassword(userEntity, newPassword);

        return new SimpleResponse(
                userEntity.getUserId(),
                userEntity.getUserNickname()
        );
    }

    public SimpleResponse editProfile(Long id, EditProfileRequest editProfileRequest) {
        UserEntity userEntity = getUserEntityById(id);

        String newNickname = editProfileRequest.getUserNickname();
        String newProfileImgUrl = editProfileRequest.getUserProfileImgUrl();

        if (newNickname.equals(userEntity.getUserNickname())) {
            throw new UserException.SameNicknameException("이전 닉네임과 동일합니다.");
        };

        if (repository.findByNickname(newNickname).isPresent()) {
            throw new UserException.UserNicknameAlreadyExistsException("존재하는 닉네임입니다.");
        }

        if (newProfileImgUrl != "" & newProfileImgUrl.equals(userEntity.getUserProfileImgUrl())) {
            throw new UserException.SameProfileImgException("이전 프로필 사진과 동일합니다.");
        }

        repository.editProfile(
                userEntity,
                newNickname,
                newProfileImgUrl
        );

        return new SimpleResponse(
                userEntity.getUserId(),
                userEntity.getUserNickname()
        );
    }

    public SimpleResponse softDelete(Long id) {
        UserEntity userEntity = getUserEntityById(id);

        if (userEntity.getUserIsDeleted()) {
            throw new UserException.UserNotFoundException("존재하지 않는 사용자입니다.");
        }

        repository.softDelete(userEntity);
        return new SimpleResponse(
                userEntity.getUserId(),
                userEntity.getUserNickname()
        );
    }

}
