package com.example.community.likes.service;

import com.example.community.comments.CommentsCsvRepository;
import com.example.community.likes.repository.LikeCsvRepository;
import com.example.community.posts.PostCsvRepository;
import com.example.community.posts.PostException;
import com.example.community.posts.entity.PostEntity;
import com.example.community.users.UserCsvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("postLikeService")
public class PostLikeService extends LikeService {
    @Autowired
    public PostLikeService(UserCsvRepository userCsvRepository,
                           @Qualifier("postLikeRepository") LikeCsvRepository likeCsvRepository,
                           PostCsvRepository postCsvRepository,
                           CommentsCsvRepository commentsCsvRepository) {
        this.contentType = "post";
        this.userCsvRepository = userCsvRepository;
        this.likeCsvRepository = likeCsvRepository;
        this.postCsvRepository = postCsvRepository;
        this.commentsCsvRepository = commentsCsvRepository;
    }

    @Override
    public void validateContent(Long contendId){
        PostEntity entity = postCsvRepository.findById(contendId)
                .orElseThrow(
                        ()-> new PostException.PostNotFoundException("존재하지 않는 게시글입니다.")
                );
    }
}
