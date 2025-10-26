package com.example.community.likes;

public enum Contents {
    POST(LikesConstants.CONTENT_TYPE_POST, LikesConstants.PATH_DB_POSTS_LIKE),
    COMMENT(LikesConstants.CONTENT_TYPE_COMMENT, LikesConstants.PATH_DB_COMMENTS_LIKE);

    private String contentType;
    private String dbPath;

    private Contents(String contentType, String dbPath){
        this.contentType = contentType;
        this.dbPath = dbPath;
    }

    public String getDbPath(){return dbPath;}
    public String getContentType(){return contentType;}
}
