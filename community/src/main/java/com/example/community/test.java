package com.example.community;

import com.example.community.repository.PostLikeCsvRepository;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        PostLikeCsvRepository postLikeCsvRepository = new PostLikeCsvRepository();
        postLikeCsvRepository.checkUserLikePost(1l, 1l);
    }
}
