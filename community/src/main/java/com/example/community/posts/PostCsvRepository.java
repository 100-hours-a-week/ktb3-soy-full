package com.example.community.posts;

import com.example.community.posts.dto.PostEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostCsvRepository implements PostRepository {
    public final Map<Long, PostEntity> postStore = new LinkedHashMap<>();
    private AtomicLong sequence = new AtomicLong(0);
    private final String postDbPath = "src/main/resources/data/posts.csv";

    private PostEntity createPostEntity(String line){
        String[] parts = line.split(",");
        Long postId = Long.parseLong(parts[0]);
        Long writerId = Long.parseLong(parts[1]);
        String title = parts[2];
        String content = parts[3];
        String imgUrl = parts[4];
        Long likeCounts = Long.parseLong(parts[5]);
        Long viewCounts = Long.parseLong(parts[6]);
        Long commentCounts = Long.parseLong(parts[7]);
        String createdAt = parts[8];

        return new PostEntity(
                postId, writerId, title, content, imgUrl,
                likeCounts, viewCounts, commentCounts, createdAt
        );
    }

    @PostConstruct
    private void init() throws IOException {
        File file = new File(postDbPath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        bufferedReader.readLine(); // 칼럼행 건너뛰기
        while ((line = bufferedReader.readLine()) != null) {
            PostEntity postEntity = createPostEntity(line);
            sequence.set(postEntity.getPostId());
            postStore.put(sequence.get(), postEntity);
        }
    }

    public PostCsvRepository() throws IOException {
        init();
    }

    @Override
    public Optional<PostEntity> findById(Long postId) {
        return Optional.ofNullable(postStore.get(postId));
    }

    @Override
    public ArrayList<PostEntity> findAll() {
        return new ArrayList<>(postStore.values());
    }

    @Override
    public PostEntity save(PostEntity postEntity) {
        postEntity.setPostId(sequence.incrementAndGet());
        postStore.put(postEntity.getPostId(), postEntity);
        return postEntity;
    }

    @Override
    public void delete(Long postId) {
        postStore.remove(postId);
    }

    @Override
    public boolean existsById(Long postId) {
        return postStore.containsKey(postId);
    }

    @Override
    public void edit(PostEntity postEntity){
        postStore.put(postEntity.getPostId(), postEntity);
    }

    @Override
    public void incrementLikeCount(Long postId){
        PostEntity postEntity = postStore.get(postId);
        postEntity.setPostLikeCounts(postEntity.getPostLikeCounts() + 1);
        postStore.put(postId, postEntity);
    }

    @Override
    public void decrementLikeCount(Long postId){
        PostEntity postEntity = postStore.get(postId);
        postEntity.setPostLikeCounts(postEntity.getPostLikeCounts() - 1);
        postStore.put(postId, postEntity);
    }

    @Override
    public List<PostEntity> findPagedPosts(Long startPageId, Long endPageId){
        List<PostEntity> sortedPostEntityList = postStore.values().stream().sorted(
                Comparator.comparing(PostEntity::getPostCreatedAt).reversed()
        ).toList();
        return sortedPostEntityList.subList(Math.toIntExact(startPageId), Math.toIntExact(endPageId));
    }
}
