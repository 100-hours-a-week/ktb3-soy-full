package com.example.community.likes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.community.dto.SimpleResponse;

@RestController
public class LikesController {
    private LikesService postLikesService;
    @Autowired
    public LikesController(LikesService postLikesService) {
        this.postLikesService = postLikesService;
    }

    @PostMapping("/api/posts/{postId}/likes")
    public ResponseEntity<SimpleResponse> likePost(@PathVariable("postId") Long postId, @RequestParam Long userId) {
        SimpleResponse simpleResponse = postLikesService.likePost(postId, userId);
        return ResponseEntity.ok(simpleResponse);
    }

    @DeleteMapping("/api/posts/{postId}/likes")
    public ResponseEntity<SimpleResponse> dislikePost(@PathVariable("postId") Long postId, @RequestParam Long userId){
        SimpleResponse simpleResponse = postLikesService.dislikePost(postId, userId);
        return ResponseEntity.ok(simpleResponse);
    }

}
