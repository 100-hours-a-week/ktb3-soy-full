package com.example.community.likes;

import com.example.community.likes.dto.PostLikeEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PostLikeCsvRepository implements PostLikeRepository {
    public final Map<String, PostLikeEntity> postLikeStore = new LinkedHashMap<>();
    private final String postLikeDbPath = "src/main/resources/data/postLikes.csv";

    public PostLikeEntity createPostLikeEntity(String line) {
        String[] parts = line.split(",");
        Long postId = Long.parseLong(parts[0]);
        Long writerId = Long.parseLong(parts[1]);
        String createdAt = parts[2];
        return new PostLikeEntity(postId, writerId, createdAt);
    }

    public PostLikeCsvRepository() throws IOException {
        init();
    }

    @PostConstruct
    private void init() throws IOException {
        File file = new File(postLikeDbPath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        bufferedReader.readLine(); // 칼럼행 건너뛰기
        while ((line = bufferedReader.readLine()) != null) {
            PostLikeEntity postLikeEntity = createPostLikeEntity(line);
            String id = key(postLikeEntity.getPostId(), postLikeEntity.getUserId());
            postLikeStore.put(id, postLikeEntity);
        }
    }

    private String key(Long postId, Long userId) {
        return "%s_%s".formatted(postId, userId);
    }

    @Override
    public PostLikeEntity save(PostLikeEntity entity) {
        String compositeKey = key(entity.getPostId(), entity.getUserId());
        postLikeStore.put(compositeKey, entity);
        return entity;
    }

    @Override
    public ArrayList<PostLikeEntity> findAll() {
        return new ArrayList<>(postLikeStore.values());
    }

    @Override
    public Optional<PostLikeEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        return false; // composite key 기반 설계에서는 의미 없음
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public Optional<PostLikeEntity> findByPostAndUser(Long postId, Long userId) {
        String compositeKey = key(postId, userId);
        return Optional.ofNullable(postLikeStore.get(compositeKey));
    }

    @Override
    public void deleteByPostAndUser(Long postId, Long userId) {
        String compositeKey = key(postId, userId);
        postLikeStore.remove(compositeKey);
    }

    @Override
    public boolean existsByPostAndUser(Long postId, Long userId) {
        return postLikeStore.containsKey(key(postId, userId));
    }
}