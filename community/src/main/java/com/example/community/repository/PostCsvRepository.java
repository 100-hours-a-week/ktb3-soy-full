package com.example.community.repository;

import com.example.community.dto.posts.PostEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostCsvRepository {
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

    public List<PostEntity> findPageOfPosts(Long startPageId, Long endPageId){
        // 정렬
        List<PostEntity> sortedPostEntityList = postStore.values().stream().sorted(
                Comparator.comparing(PostEntity::getPostCreatedAt).reversed()
        ).toList();

        return sortedPostEntityList.subList(Math.toIntExact(startPageId), Math.toIntExact(endPageId));
    }

    public void verifyPostId(Long postId){
        if (!postStore.containsKey(postId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다.");
        }
    }

    public PostEntity findPostById(Long postId){
        verifyPostId(postId);
        return postStore.get(postId);
    }

    public Long savePost(PostEntity postEntity){
        postEntity.setPostId(sequence.getAndIncrement());
        postStore.put(postEntity.getPostId(), postEntity);
        return postEntity.getPostId();
    }

    public void updatePost(PostEntity postEntity){
        postStore.put(postEntity.getPostId(), postEntity);
    }

    public void deletePost(Long postId){
        postStore.remove(postId);
    }

    public void likePost(Long postId){
        PostEntity postEntity = postStore.get(postId);
        postEntity.setPostLikeCounts(postEntity.getPostLikeCounts() + 1);
        postStore.put(postId, postEntity);
    }

    public void dislikePost(Long postId){
        PostEntity postEntity = postStore.get(postId);
        postEntity.setPostLikeCounts(postEntity.getPostLikeCounts() - 1);
        postStore.put(postId, postEntity);
    }
}
