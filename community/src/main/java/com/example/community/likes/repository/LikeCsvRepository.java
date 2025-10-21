package com.example.community.likes.repository;

import com.example.community.likes.LikeException;
import com.example.community.likes.entity.LikeEntity;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class LikeCsvRepository{
    Map<String, LikeEntity> likeStore = new HashMap<>();
    String dbPath;
    String contentType;

    public abstract void init();

    public String key(Long contentId, Long userId){
        return contentId + "-" + userId;
    }

    public LikeEntity createEntityFromLine(String line){
        line.split(",");
        Long contentId = Long.valueOf(line.split(",")[0]);
        Long userId = Long.valueOf(line.split(",")[1]);
        String createdAt = line.split(",")[2];
        return new LikeEntity(contentId, userId, createdAt);
    }

    public void initFromFile() {
        File file = new File(dbPath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            reader.readLine();
            line = reader.readLine();
            LikeEntity likeEntity = createEntityFromLine(line);
            String id = key(likeEntity.getContentId(), likeEntity.getUserId());
            likeStore.put(id, likeEntity);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public LikeEntity findByContentAndUserID(Long contentId, Long userID) {
        return Optional.ofNullable(likeStore.get(key(contentId, userID))).orElseThrow(
                () -> new LikeException.NotFoundException("리소스를 찾지 못했습니다.")
        );
    }

    public LikeEntity save(LikeEntity entity) {
        String key = key(entity.getContentId(), entity.getUserId());
        likeStore.put(key, entity);
        return entity;
    }

    public void deleteByContentAndUserID(Long contentId, Long userID){
        String key = key(contentId, userID);
        likeStore.remove(key);
    }

    public boolean existsByContentAndUserID(Long contentId, Long userID) {
        String key = key(contentId, userID);
        return likeStore.containsKey(key);
    }
}
