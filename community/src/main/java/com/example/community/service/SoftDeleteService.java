package com.example.community.service;

import com.example.community.dto.SimpleResponse;
import com.example.community.dto.UserEntity;
import com.example.community.repository.UserCsvRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SoftDeleteService {
    private UserCsvRepository userCsvRepository;
    @Autowired
    public SoftDeleteService(UserCsvRepository userCsvRepository) {
        this.userCsvRepository = userCsvRepository;
    }

    public SimpleResponse softDelete(Long id) {
        UserEntity userEntity = userCsvRepository.findById(id)
                        .orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "존재하지 않는 사용자입니다."));

        if (userEntity.getUserIsDeleted()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 삭제된 사용자입니다.");
        }

        userCsvRepository.softDelete(userEntity);
        return new SimpleResponse(
                userEntity.getUserId(),
                userEntity.getUserNickname()
        );
    }
}
