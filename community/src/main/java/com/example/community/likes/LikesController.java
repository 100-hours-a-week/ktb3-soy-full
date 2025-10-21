package com.example.community.likes;

import com.example.community.likes.dto.SimpleResponse;
import com.example.community.likes.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LikesController {
    private Map<String, LikeService> likeServiceMap;
    @Autowired
    public LikesController(Map<String, LikeService> likeServiceMap) {
        this.likeServiceMap = likeServiceMap;
    }


    public LikeService getLikeService(String contentType) {
        LikeService likeService = likeServiceMap.get(contentType + "LikeService");
        if (likeService == null) {
            throw new IllegalArgumentException("지원하지 않는 contentType: " + contentType);
        }
        return likeService;
    }


    @PostMapping("/{contentType}/{contentId}/likes")
    @Operation(summary = "컨텐츠 좋아요")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "컨텐츠 좋아요 성공")
    })
    public ResponseEntity<com.example.community.likes.dto.SimpleResponse> like(@PathVariable("contentType") String contentType, @PathVariable("contentId") Long contentId, @RequestParam Long userId){
        LikeService likeService = getLikeService(contentType);
        SimpleResponse simpleResponse = likeService.like(contentId, userId);
        return ResponseEntity.ok(simpleResponse);
    }

    @DeleteMapping("/{contentType}/{contentId}/likes")
    @Operation(summary = "컨텐츠 좋아요 취소")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "컨텐츠 좋아요 취소 성공")
    })
    public ResponseEntity<SimpleResponse> unlike(@PathVariable("contentType") String contentType, @PathVariable("contentId") Long contentId, @RequestParam Long userId){
        LikeService likeService = getLikeService(contentType);
        SimpleResponse simpleResponse = likeService.unlike(contentId, userId);
        return ResponseEntity.ok(simpleResponse);
    }
}
