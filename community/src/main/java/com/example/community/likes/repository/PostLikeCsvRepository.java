package com.example.community.likes.repository;

import com.example.community.likes.Contents;
import org.springframework.stereotype.Repository;

@Repository("postLikeRepository")
public class PostLikeCsvRepository extends LikeCsvRepository{
    @Override
    public void init(){
        this.dbPath = Contents.POST.getDbPath();
        this.contentType = Contents.POST.getContentType();
        initFromFile();
    }
}
