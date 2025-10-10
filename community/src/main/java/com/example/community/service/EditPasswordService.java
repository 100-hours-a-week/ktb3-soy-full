package com.example.community.service;

import com.example.community.dto.EditPasswordRequest;
import com.example.community.dto.SimpleResponse;
import com.example.community.dto.UserEntity;
import com.example.community.repository.UserCsvRepository;
import com.example.community.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EditPasswordService {
    private final UserCsvRepository userCsvRepository;
    private final Logger log = LoggerFactory.getLogger(EditPasswordService.class);

    @Autowired
    public EditPasswordService(UserCsvRepository userCsvRepository) {
        this.userCsvRepository = userCsvRepository;
    }

    public SimpleResponse editPassword(Long id, EditPasswordRequest editPasswordRequest) {
        String oldPassword = editPasswordRequest.getUserOldPassword();
        String newPassword = editPasswordRequest.getUserNewPassword();

        log.info(oldPassword, newPassword);

        UserEntity userEntity = userCsvRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."));

        String realOldPassword = userEntity.getUserPassword();

        if (!oldPassword.equals(realOldPassword)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "잘못된 비밀번호입니다.");
        }

        if (newPassword.equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "현재 비밀번호와 새 비밀번호가 동일합니다.");
        }

        userCsvRepository.editPassword(userEntity, newPassword);

        return new SimpleResponse(
                userEntity.getUserEmail(),
                userEntity.getUserNickname()
        );
    }
}
