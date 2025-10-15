package com.example.community.likes;

import com.example.community.likes.dto.PostLikeEntity;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PostLikeCsvRepository {
    public final Map<String, PostLikeEntity> postLikeStore = new LinkedHashMap<>();
    private final String postLikeDbPath = "src/main/resources/data/postLikes.csv";

    public PostLikeEntity createPostLikeEntity(String line){
        String[] parts = line.split(",");
        Long postId = Long.parseLong(parts[0]);
        Long writerId = Long.parseLong(parts[1]);
        String createdAt = parts[2];
        return new PostLikeEntity(postId, writerId, createdAt);
    }

    public PostLikeCsvRepository()  throws IOException{
        init();
    }

    private void init() throws IOException {
        File file = new File(postLikeDbPath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        bufferedReader.readLine(); // 칼럼행 건너뛰기
        while ((line = bufferedReader.readLine()) != null) {
            PostLikeEntity postLikeEntity = createPostLikeEntity(line);
            String id = "%d%d".formatted(postLikeEntity.getPostId(), postLikeEntity.getUserId());
            postLikeStore.put(id, postLikeEntity);
        }
    }

    public void likePost(Long postId, Long userId, String createdAt) {
        String nextSequence = "%d%d".formatted(postId,userId);
        postLikeStore.put(nextSequence, new PostLikeEntity(postId, userId, createdAt));
    }

    public Boolean checkUserLikePost(Long postId, Long userId) {
        Optional<PostLikeEntity> entity = postLikeStore.values().stream().filter(
                postLikeEntity -> postLikeEntity.getPostId().equals(postId)
        ).filter(
                postLikeEntity -> postLikeEntity.getUserId().equals(userId)
        ).findAny();
        return entity.isPresent();
    }

    public void dislikePost(Long postId, Long userId) {
        String findId = "%d%d".formatted(postId,userId);
        postLikeStore.remove(findId);
    }

}
