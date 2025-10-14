package com.example.community.repository.users;

import com.example.community.dto.users.UserEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository {
    public ArrayList<UserEntity> findAll();
    public Optional<UserEntity> findById(Long id);
    public void save(UserEntity userEntity);
}
