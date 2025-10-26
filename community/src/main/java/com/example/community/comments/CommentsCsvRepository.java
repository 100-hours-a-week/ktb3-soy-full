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
public class CommentsCsvRepository implements CommentsRepository {
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
        return CommentsEntity.builder()
                .commentId(commentId)
                .parentCommentId(parentCommentId)
                .postId(postId)
                .commentWriterId(writerId)
                .commentContent(commentContent)
                .commentCreatedAt(createdAt).build();
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

    @Override
    public Optional<CommentsEntity> findById(Long commentId) {
        return Optional.ofNullable(commentsStore.get(commentId));
    }

    @Override
    public ArrayList<CommentsEntity> findAll() {
        return new ArrayList<>(commentsStore.values());
    }

    @Override
    public CommentsEntity save(CommentsEntity commentsEntity) {
        Long newCommentId = sequence.incrementAndGet();
        commentsEntity.updateId(newCommentId);
        commentsStore.put(newCommentId, commentsEntity);
        return commentsEntity;
    }

    @Override
    public void delete(Long id) {
        commentsStore.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return commentsStore.containsKey(id);
    }

    @Override
    public ArrayList<CommentsEntity> getCommentsByPostId(Long postId) {
        List<CommentsEntity> commentsEntityList = commentsStore.values().stream()
                .filter(commentsEntity -> commentsEntity.getPostId().equals(postId))
                .collect(Collectors.toList());
        return new ArrayList<>(commentsEntityList);
    }

    @Override
    public void editComment(Long commentId, String newCommentContent) {
        CommentsEntity commentsEntity = commentsStore.get(commentId);
        commentsEntity.updateContent(newCommentContent);
        commentsStore.put(commentsEntity.getCommentId(), commentsEntity);
    }

}
