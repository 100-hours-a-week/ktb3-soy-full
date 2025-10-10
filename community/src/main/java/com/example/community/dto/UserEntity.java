package com.example.community.dto;

public class UserEntity {
    private Long userId;
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private String userProfileImgUrl;
    private Boolean userIsDeleted;
    private String userCreatedAt;
    private String userDeletedAt;

    public UserEntity() {}
    public UserEntity(Long userId, String userEmail, String userPassword, String userNickname, String userProfileImgUrl, Boolean userIsDeleted, String userCreatedAt, String userDeletedAt) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userProfileImgUrl = userProfileImgUrl;
        this.userIsDeleted = userIsDeleted;
        this.userCreatedAt = userCreatedAt;
        this.userDeletedAt = userDeletedAt;
    }

    public Long getUserId() {return this.userId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public String getUserEmail() {return this.userEmail;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
    public String getUserPassword() {return this.userPassword;}
    public void setUserPassword(String userPassword) {this.userPassword = userPassword;}
    public String getUserNickname() {return this.userNickname;}
    public void setUserNickname(String userNickname) {this.userNickname = userNickname;}
    public String getUserProfileImgUrl() {return this.userProfileImgUrl;}
    public void setUserProfileImgUrl(String userProfileImgUrl) {this.userProfileImgUrl = userProfileImgUrl;}
    public Boolean getUserIsDeleted() {return this.userIsDeleted;}
    public void setUserIsDeleted(Boolean userIsDeleted) {this.userIsDeleted = userIsDeleted;}
    public String getUserCreatedAt() {return this.userCreatedAt;}
    public void setUserCreatedAt(String userCreatedAt) {this.userCreatedAt = userCreatedAt;}
    public String getUserDeletedAt() {return this.userDeletedAt;}
    public void setUserDeletedAt(String userDeletedAt) {this.userDeletedAt = userDeletedAt;}

    @Override
    public String toString() {
        return "%d %s %s %s %s %s %s".formatted(userId, userEmail, userPassword, userNickname, userProfileImgUrl, userCreatedAt, userDeletedAt);
    }
}
