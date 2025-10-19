package com.example.community.posts;

import com.example.community.dto.SimpleResponse;
import com.example.community.posts.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class PostsController {
    private PostsService postsService;
    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/api/posts")
    public ResponseEntity<PostListResponse> getPostList(@RequestParam Long page, @RequestParam Long size) {
        PostListResponse postListResponse = postsService.viewPostList(page, size);
        return ResponseEntity.ok(postListResponse);
    }

    @GetMapping("/api/posts/detail/{postId}")
    public ResponseEntity<PostDetailResponse> getPostDetail(@PathVariable("postId") Long postId) {
        PostDetailResponse postDetailResponse = postsService.viewPostDetail(postId);
        return ResponseEntity.ok(postDetailResponse);
    }

    @PostMapping("/api/posts")
    public ResponseEntity<PostCreateResponse> createPost(@RequestParam Long userId, @RequestBody PostCreateRequest postCreateRequest) {
        PostCreateResponse postCreateResponse = postsService.createPost(userId, postCreateRequest);
        return ResponseEntity
                .created(URI.create(postCreateResponse.getRedirectUri()))
                .body(postCreateResponse);
    }

    @PatchMapping("/api/posts/{postId}")
    public ResponseEntity<SimpleResponse> editPost(@PathVariable("postId") Long postId, @RequestParam Long userId, @RequestBody PostEditRequest postEditRequest) {
        SimpleResponse simpleResponse = postsService.editPost(postId, userId, postEditRequest);
        return ResponseEntity.ok(simpleResponse);
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<SimpleResponse> deletePost(@PathVariable("postId") Long postId, @RequestParam Long userId) {
        SimpleResponse simpleResponse = postsService.deletePost(postId, userId);
        return ResponseEntity.ok(simpleResponse);
    }
}
