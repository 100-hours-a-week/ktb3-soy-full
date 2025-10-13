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

@Repository
public class PostCsvRepository {
    public final Map<Integer, PostEntity> postStore = new LinkedHashMap<>();
    private AtomicInteger sequence = new AtomicInteger(0);
    private final String postDbPath = "src/main/resources/data/posts.csv";

    private PostEntity createPostEntity(String line){
        String[] parts = line.split(",");
        Integer postId = Integer.parseInt(parts[0]);
        Long writerId = Long.parseLong(parts[1]);
        String title = parts[2];
        String content = parts[3];
        String imgUrl = parts[4];
        Integer likeCounts = Integer.parseInt(parts[5]);
        Integer viewCounts = Integer.parseInt(parts[6]);
        Integer commentCounts = Integer.parseInt(parts[7]);
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
            postStore.put((int) sequence.get(), postEntity);
        }
    }

    public PostCsvRepository() throws IOException {
        init();
    }

    private int[] getPageId(int pageNumber, int pageSize, int totalPosts){
        int postStartId = (pageNumber - 1) * pageSize;
        int postEndId = Math.min(postStartId + pageSize, totalPosts);
        return new int[]{postStartId, postEndId};
    }

    public List<PostEntity> findPageOfPosts(int pageNumber, int pageSize){
        // 정렬
        List<PostEntity> sortedPostEntityList = postStore.values().stream().sorted(
                Comparator.comparing(PostEntity::getPostCreatedAt).reversed()
        ).toList();

        // 페이지 사이즈, 페이지 넘버에 맞춰서 페이지 아이디 설정
        int[] pageIds = getPageId(pageNumber, pageSize, sortedPostEntityList.size());

        return sortedPostEntityList.subList(pageIds[0], pageIds[1]);
    }

    public void verifyPostId(int postId){
        if (!postStore.containsKey(postId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다.");
        }
    }

    public PostEntity findPostById(int postId){
        verifyPostId(postId);
        return postStore.get(postId);
    }

    public void savePost(PostEntity postEntity){
        postEntity.setPostId(sequence.getAndIncrement());
        postStore.put(postEntity.getPostId(), postEntity);
    }
}
