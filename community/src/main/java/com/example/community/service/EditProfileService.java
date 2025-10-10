package com.example.community.service;

import com.example.community.dto.EditProfileRequest;
import com.example.community.dto.SimpleResponse;
import com.example.community.dto.UserEntity;
import com.example.community.repository.UserCsvRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EditProfileService {
    private final UserCsvRepository userCsvRepository;
    public EditProfileService(UserCsvRepository userCsvRepository) {
        this.userCsvRepository = userCsvRepository;
    }

    public SimpleResponse editProfile(Long id, EditProfileRequest editProfileRequest) {
        UserEntity userEntity = userCsvRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다.")
        );

        String newNickname = editProfileRequest.getUserNickname();
        String newProfileImgUrl = editProfileRequest.getUserProfileImgUrl();

        if (newNickname.equals(userEntity.getUserNickname())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이전 닉네임과 동일합니다.");
        };

        if (userCsvRepository.findByNickname(newNickname).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "존재하는 닉네임입니다.");
        }


        if (newProfileImgUrl != null){
            if (newProfileImgUrl.equals(userEntity.getUserProfileImgUrl())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "이전 프로필 사진과 동일합니다.");
            }
        }


        userCsvRepository.editProfile(
                userEntity,
                newNickname,
                newProfileImgUrl
        );

        return new SimpleResponse(
                userEntity.getUserId(),
                userEntity.getUserNickname()
        );

    }
}
