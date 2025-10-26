package com.example.community.posts.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostEntity {
    private Long postId;
    private Long postWriterId;
    private String postTitle;
    private String postContent;
    private String postImgUrl;
    private Long postLikeCounts;
    private Long postViewCounts;
    private Long postCommentCounts;
    private String postCreatedAt;

    @Override
    public String toString(){
        return "Post %d %s".formatted(postId, postCreatedAt);
    }

    public void updatePostId(Long postId) {
        this.postId = postId;
    }

    public void updateLikeCounts() {
        this.postLikeCounts += 1;
    }

    public void decrementLikeCount() {
        this.postLikeCounts -= 1;
    }

    public void updatePostTitle(String title) {
        if (title != null & title != "") {
            this.postTitle = title;
        }
    }

    public void updatePostContent(String content) {
        if (content != null & content != "") {
            this.postContent = content;
        }
    }

    public void updatePostImgUrl(String url) {
        if (url != null & url != ""){
            this.postImgUrl = url;
        }
    }


}