package com.example.community.likes.service;

import com.example.community.comments.CommentsCsvRepository;
import com.example.community.comments.CommentsException;
import com.example.community.comments.dto.CommentsEntity;
import com.example.community.likes.repository.LikeCsvRepository;
import com.example.community.posts.PostCsvRepository;
import com.example.community.users.UserCsvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("commentLikeService")
public class CommentLikeService extends LikeService {
    @Autowired
    public CommentLikeService(UserCsvRepository userCsvRepository,
                              @Qualifier("commentLikeRepository") LikeCsvRepository likeCsvRepository,
                              PostCsvRepository postCsvRepository,
                              CommentsCsvRepository commentsCsvRepository) {
        this.contentType = "comment";
        this.userCsvRepository = userCsvRepository;
        this.likeCsvRepository = likeCsvRepository;
        this.postCsvRepository = postCsvRepository;
        this.commentsCsvRepository = commentsCsvRepository;
    }


    @Override
    public void validateContent(Long contentId) {
        CommentsEntity entity = commentsCsvRepository.findById(contentId)
                .orElseThrow(
                        ()-> new CommentsException.CommentsNotFoundException("존재하지 않는 댓글입니다."));
    }
}
