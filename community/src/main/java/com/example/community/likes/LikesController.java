package com.example.community.likes;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.community.common.dto.SimpleResponse;

import java.util.Map;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikesController {
    private final Map<String, LikeService> likeServices;

    @PostMapping("/{type}/{id}/likes")
    public ResponseEntity<SimpleResponse> like(@PathVariable String type, @PathVariable Long id, @RequestParam Long userId) {
        LikeService service = likeServices.get(type + "LikeService");
        System.out.println("userId = " + userId);
        if (service == null) throw new IllegalArgumentException("Invalid type");
        SimpleResponse simpleResponse = service.like(id, userId);
        return ResponseEntity.ok(simpleResponse);
    }

    @DeleteMapping("/{type}/{id}/likes")
    public ResponseEntity<SimpleResponse> dislikePost(@PathVariable String type, @PathVariable Long id, @RequestParam Long userId){
        LikeService service = likeServices.get(type + "LikeService");
        if(service == null) throw new IllegalArgumentException("Invalid type");
        SimpleResponse simpleResponse = service.dislike(id, userId);
        return ResponseEntity.ok(simpleResponse);
    }
}
