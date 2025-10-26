package com.example.community.users.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserEntity {
    private Long userId;
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private String userProfileImgUrl;
    private Boolean userIsDeleted;
    private String userCreatedAt;
    private String userDeletedAt;

    public void updatePassword(String newPassword) {
        this.userPassword = newPassword;
    }

    public void updateProfileImgUrl(String newProfileImgUrl) {
        this.userProfileImgUrl = newProfileImgUrl;
    }

    public void updateUserNickname(String newNickname) {
        this.userNickname = newNickname;
    }

    public void updateUserId(Long newUserId) {
        this.userId = newUserId;
    }

    public void updateUserIsDeleted(Boolean newUserIsDeleted) {
        this.userIsDeleted = newUserIsDeleted;
    }

    public boolean isPasswordMatch(String givenPassword) {
        return this.userPassword.equals(givenPassword);
    }

    @Override
    public String toString() {
        return "%d %s %s %s %s %s %s".formatted(userId, userEmail, userPassword, userNickname, userProfileImgUrl, userCreatedAt, userDeletedAt);
    }
}
