package com.example.community.likes;

public enum Contents {
    POST("post", "/Users/soy/Desktop/ktb3-soy-full/community/src/main/resources/data/postLikes.csv"),
    COMMENT("comment", "/Users/soy/Desktop/ktb3-soy-full/community/src/main/resources/data/commentLikes.csv");

    private String contentType;
    private String dbPath;

    private Contents(String contentType, String dbPath){
        this.contentType = contentType;
        this.dbPath = dbPath;
    }

    public String getDbPath(){return dbPath;}
    public String getContentType(){return contentType;}
}
