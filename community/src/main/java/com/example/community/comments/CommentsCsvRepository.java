package com.example.community.comments;

import com.example.community.comments.dto.CommentsEntity;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CommentsCsvRepository {
    public final Map<Long, CommentsEntity> commentsStore = new LinkedHashMap<>();
    private AtomicLong sequence = new AtomicLong(0);
    private final String commentsDbPath = "src/main/resources/data/comments.csv";

    public CommentsEntity createCommentEntity(String line){
        String[] parts = line.split(",", -1);
        Long commentId = parseLong(parts[0]);
        Long parentCommentId = parseLong(parts[1]);
        Long postId = parseLong(parts[2]);
        Long writerId = parseLong(parts[3]);
        String commentContent = parts[4];
        String createdAt = parts[5];
        return new CommentsEntity(commentId, parentCommentId, postId, writerId, commentContent, createdAt);
    }

    public Long parseLong(String value){
        if(value==null || value.isEmpty()){return null;}
        return Long.parseLong(value);
    }

    public CommentsCsvRepository()  throws IOException {
        init();
    }

    private void init() throws IOException {
        File file = new File(commentsDbPath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        bufferedReader.readLine(); // 칼럼행 건너뛰기
        while ((line = bufferedReader.readLine()) != null) {
            CommentsEntity commentEntity = createCommentEntity(line);
            Long id = commentEntity.getCommentId();
            sequence.set(id);
            commentsStore.put(id, commentEntity);
        }
    }

    public List<CommentsEntity> getComments(Long postId) {
        List<CommentsEntity> commentsEntities = commentsStore.values().stream()
                .filter(commentsEntity -> commentsEntity.getPostId().equals(postId))
                .collect(Collectors.toList());
        return commentsEntities;
    }

    public Optional<CommentsEntity> findById(Long commentId) {
        return Optional.ofNullable(commentsStore.get(commentId));
    }

    public Boolean verifyComment(Long commentId) {
        return commentsStore.containsKey(commentId);
    }

    public void saveComment(CommentsEntity commentsEntity) {
        Long newCommentId = sequence.incrementAndGet();
        commentsEntity.setCommentId(newCommentId);
        commentsStore.put(newCommentId, commentsEntity);
    }
    public void editComment(Long commentId, String newCommentContent) {
        CommentsEntity commentsEntity = commentsStore.get(commentId);
        commentsEntity.setCommentContent(newCommentContent);
        commentsStore.put(commentsEntity.getCommentId(), commentsEntity);
    }

    public void deleteComment(Long commentId) {
        commentsStore.remove(commentId);
    }
}
