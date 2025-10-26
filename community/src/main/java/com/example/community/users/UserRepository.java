package com.example.community.users;

import com.example.community.repository.Repository;
import com.example.community.users.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByNickname(String username);
    ArrayList<UserEntity> findAllByIds(List<Long> ids);
    void editPassword(UserEntity userEntity, String newPassword);
    void editProfile(UserEntity userEntity, String newNickname, String newProfileImgUrl);
    void softDelete(UserEntity userEntity);
}
