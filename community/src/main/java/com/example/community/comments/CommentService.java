package com.example.community.comments;

import com.example.community.comments.dto.*;
import com.example.community.dto.SimpleResponse;
import com.example.community.posts.PostCsvRepository;
import com.example.community.users.UserCsvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private PostCsvRepository postCsvRepository;
    private UserCsvRepository userCsvRepository;
    private CommentsCsvRepository commentsCsvRepository;

    private CommentAssembler commentAssembler = new CommentAssembler();

    @Autowired
    public CommentService(CommentsCsvRepository commentsCsvRepository, UserCsvRepository userCsvRepository, PostCsvRepository postCsvRepository) {
        this.commentsCsvRepository = commentsCsvRepository;
        this.userCsvRepository = userCsvRepository;
        this.postCsvRepository = postCsvRepository;
    }

    public void validatePost(Long postId) {
        if (!postCsvRepository.verifyPostId(postId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다.");
        }
    }

    public void validateUser(Long userId){
        if (!userCsvRepository.verifyUser(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자 정보를 찾을 수 없습니다.");
        }
    }

    public void validateComment(Long commentId) {
        if(!commentsCsvRepository.verifyComment(commentId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글 정보를 찾을 수 없습니다.");
        }
    }

    public List<CommentsEntity> sortCommentsByCreatedAt(List<CommentsEntity> commentsEntities) {
        List<CommentsEntity> sortedCommentsEntities = commentsEntities.stream().sorted(Comparator.comparing(CommentsEntity::getCommentCreatedAt)).collect(Collectors.toList());
        return sortedCommentsEntities;
    }

    public CommentsViewResponse viewComments(Long postId) {
        validatePost(postId);
        List<CommentsEntity> commentsEntities = commentsCsvRepository.getComments(postId);
        commentsEntities = sortCommentsByCreatedAt(commentsEntities);
        return new CommentsViewResponse(commentsEntities);
    }

    public void ensureCommentMatchPost(Long parentCommentId, Long postId) {
        CommentsEntity commentsEntity = commentsCsvRepository.findById(parentCommentId).get();
        if (!commentsEntity.getPostId().equals(postId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "댓글 정보를 확인하세요.");
        }

    }

    public void ensureCommentMatchUser(Long commentId, Long userId) {
        CommentsEntity commentsEntity = commentsCsvRepository.findById(commentId).get();
        if(!commentsEntity.getCommentWriterId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "댓글에 대한 권한이 없습니다.");
        }

    }

    public CreateCommentResponse createComments(CreateCommentRequest createCommentRequest,
                                                Long postId,
                                                Long userId,
                                                Long parentCommentId
    ) {
        validatePost(postId);
        validateUser(userId);
        if(parentCommentId != null){
            validateComment(parentCommentId);
            ensureCommentMatchPost(parentCommentId, postId);
        }
        CommentsEntity commentsEntity = commentAssembler.toEntity(postId, userId, parentCommentId, createCommentRequest.getCommentContent());
        commentsCsvRepository.saveComment(commentsEntity);
        return CreateCommentResponse.of(postId);
    }

    public SimpleResponse editComments(EditCommentRequest editCommentRequest,
                                       Long postId,
                                       Long commentId,
                                       Long userId){
        validatePost(postId);
        validateComment(commentId);
        validateUser(userId);

        ensureCommentMatchUser(commentId, userId);
        ensureCommentMatchPost(postId, commentId);

        commentsCsvRepository.editComment(commentId, editCommentRequest.getNewCommentContent());
        return SimpleResponse.forEditComment(userId, commentId);
    }

    public SimpleResponse deleteComments(Long postId, Long commentId, Long userId){
        validatePost(postId);
        validateComment(commentId);
        validateUser(userId);

        ensureCommentMatchUser(commentId, userId);
        ensureCommentMatchPost(postId, commentId);

        commentsCsvRepository.deleteComment(commentId);
        return SimpleResponse.forDeleteComment(userId, commentId);
    }


}
