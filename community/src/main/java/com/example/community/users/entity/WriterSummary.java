package com.example.community.users.entity;

import lombok.Getter;

@Getter
public class WriterSummary {
    private long userId;
    private String userNickname;
    private String userProfileImgUrl;

    private WriterSummary(long userId, String userNickname, String userProfileImgUrl) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userProfileImgUrl = userProfileImgUrl;
    }

    public static WriterSummary create(long userId, String userNickname, String userProfileImgUrl) {
        return new WriterSummary(userId, userNickname, userProfileImgUrl);
    }
}
