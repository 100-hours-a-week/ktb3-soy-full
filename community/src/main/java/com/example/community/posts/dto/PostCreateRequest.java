package com.example.community.posts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class PostCreateRequest {
    @NotBlank
    private String postTitle;
    @NotBlank
    private String postContent;

    private String postImageUrl;

    public void updatePostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }
}
