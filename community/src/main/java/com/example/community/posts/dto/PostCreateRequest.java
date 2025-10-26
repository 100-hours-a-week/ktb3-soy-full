package com.example.community.posts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class PostCreateRequest {
    private static final String DEFAULT_POST_IMG = "src/main/resources/images/defaultProfile.jpg";
    @NotBlank
    private String postTitle;
    @NotBlank
    private String postContent;

    private String postImageUrl;

    public void updatePostImageUrl(String postImageUrl) {
        if (postImageUrl == ""){
            postImageUrl = DEFAULT_POST_IMG;
        }
        this.postImageUrl = postImageUrl;
    }
}
