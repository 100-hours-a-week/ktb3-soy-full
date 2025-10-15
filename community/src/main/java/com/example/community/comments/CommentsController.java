package com.example.community.comments;

import com.example.community.dto.SimpleResponse;
import com.example.community.comments.dto.CommentsViewResponse;
import com.example.community.comments.dto.CreateCommentRequest;
import com.example.community.comments.dto.CreateCommentResponse;
import com.example.community.comments.dto.EditCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class CommentsController {
    private CommentService commentService;
    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/api/posts/{postId}/comments")
    public ResponseEntity<CommentsViewResponse> viewComments(@PathVariable("postId") Long postId) {
        CommentsViewResponse commentsViewResponse = commentService.viewComments(postId);
        return ResponseEntity.ok(commentsViewResponse);
    }

    @PostMapping("/api/posts/{postId}/comments")
    public ResponseEntity<CreateCommentResponse> createComments(CreateCommentRequest createCommentRequest,
                                                                @PathVariable("postId") Long postId,
                                                                @RequestParam Long userId,
                                                                @RequestParam(value = "commentId" , required = false) Long parentCommentId) {
        CreateCommentResponse createCommentResponse = commentService.createComments(createCommentRequest, postId, userId, parentCommentId);
        return ResponseEntity.created(URI.create(createCommentResponse.getRedirectUri()))
                .body(createCommentResponse);
    }

    @PatchMapping("/api/posts/{postId}/comments/{commentId}")
    public ResponseEntity<SimpleResponse> editComments(EditCommentRequest editCommentRequest,
                                                       @PathVariable("postId") Long postId,
                                                       @PathVariable Long commentId,
                                                       @RequestParam Long userId){
        SimpleResponse simpleResponse = commentService.editComments(editCommentRequest, postId, commentId, userId);
        return ResponseEntity.ok(simpleResponse);
    }

    @DeleteMapping("/api/posts/{postId}/comments/{commentId}")
    public ResponseEntity<SimpleResponse> deleteComments(@PathVariable("postId") Long postId,
                                                         @PathVariable Long commentId,
                                                         @RequestParam Long userId){
        SimpleResponse simpleResponse = commentService.deleteComments(postId, commentId, userId);
        return ResponseEntity.ok(simpleResponse);
    }

}
