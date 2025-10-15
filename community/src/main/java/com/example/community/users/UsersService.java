package com.example.community.users;

import com.example.community.users.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.community.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {
    Utility utility = new Utility();
    private UserCsvRepository repository;
    @Autowired
    public UsersService(UserCsvRepository userCsvRepository) {
        this.repository = userCsvRepository;
    }

    private static final String DEFAULT_PROFILE_IMG = "src/main/resources/images/defaultProfile.jpg";

    private boolean isEmailExist(String email) {
        return repository.findByEmail(email).isPresent();
    }

    private boolean isNicknameExist(String nickname) {
        return repository.findByNickname(nickname).isPresent();
    }

    private String checkAndSetProfileImage(String profileImgUrl) {
        if (profileImgUrl == null || profileImgUrl.isBlank()) {
            return DEFAULT_PROFILE_IMG;
        }
        return profileImgUrl;
    }

    public SignUpResponse signup(SignUpRequest signUpRequest) {
        String email = signUpRequest.getUserEmail();
        String password = signUpRequest.getUserPassword();
        String nickname = signUpRequest.getUserNickname();
        String profileImgUrl = checkAndSetProfileImage(signUpRequest.getUserProfileImgUrl());
        String createdAt = utility.getCreatedAt();

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

    public List<UserEntity> findUserByIds(List<Long> ids){
        // Change findById to findAllById
        return repository.findAllById(ids);
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
                authInfoMap.get("issuedAt"),
                authInfoMap.get("expiresIn")
        );
    }

    public SimpleResponse editPassword(Long id, EditPasswordRequest editPasswordRequest) {
        String oldPassword = editPasswordRequest.getUserOldPassword();
        String newPassword = editPasswordRequest.getUserNewPassword();

        UserEntity userEntity = repository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."));

        String realOldPassword = userEntity.getUserPassword();

        if (!oldPassword.equals(realOldPassword)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "잘못된 비밀번호입니다.");
        }

        if (newPassword.equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "현재 비밀번호와 새 비밀번호가 동일합니다.");
        }

        repository.editPassword(userEntity, newPassword);

        return new SimpleResponse(
                userEntity.getUserId(),
                userEntity.getUserNickname()
        );
    }

    public SimpleResponse editProfile(Long id, EditProfileRequest editProfileRequest) {
        UserEntity userEntity = repository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다.")
        );

        String newNickname = editProfileRequest.getUserNickname();
        String newProfileImgUrl = editProfileRequest.getUserProfileImgUrl();

        if (newNickname.equals(userEntity.getUserNickname())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이전 닉네임과 동일합니다.");
        };

        if (repository.findByNickname(newNickname).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "존재하는 닉네임입니다.");
        }

        if (newProfileImgUrl != "" & newProfileImgUrl.equals(userEntity.getUserProfileImgUrl())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이전 프로필 사진과 동일합니다.");
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
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "존재하지 않는 사용자입니다."));

        if (userEntity.getUserIsDeleted()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 삭제된 사용자입니다.");
        }

        repository.softDelete(userEntity);
        return new SimpleResponse(
                userEntity.getUserId(),
                userEntity.getUserNickname()
        );
    }

}
