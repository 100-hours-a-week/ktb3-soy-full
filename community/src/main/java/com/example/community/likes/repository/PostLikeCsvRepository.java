package com.example.community.likes.repository;

import com.example.community.likes.Contents;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository("postLikeRepository")
public class PostLikeCsvRepository extends LikeCsvRepository{
    @Override
    @PostConstruct
    public void init(){
        this.dbPath = Contents.POST.getDbPath();
        this.contentType = Contents.POST.getContentType();
        initFromFile();
        log.info(this.likeStore.toString());
    }
}
