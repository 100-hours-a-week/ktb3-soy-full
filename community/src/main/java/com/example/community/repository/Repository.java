package com.example.community.repository;

import com.example.community.users.dto.UserEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public interface Repository<T, Long> {
    Optional<T> findById(Long id);
    ArrayList<T> findAll();
    T save(T t);
    void delete(Long id);
    boolean existsById(Long id);
}
