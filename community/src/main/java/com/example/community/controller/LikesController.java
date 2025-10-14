package com.example.community.controller;

import com.example.community.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

}
