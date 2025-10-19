package com.example.community.posts;

import com.example.community.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PostException extends BusinessException {

    public PostException(HttpStatus status, String message) {
        super(status, message);
    }

    public static class PostGoneException extends PostException {
        public PostGoneException(String message) {
            super(HttpStatus.GONE, message);
        }
    }

    public static class PostNotFoundException extends PostException {
        public PostNotFoundException(String message) {
            super(HttpStatus.NOT_FOUND, message);
        }
    }

    public static class PostNotAuthorizedException extends PostException {
        public PostNotAuthorizedException(String message) {
            super(HttpStatus.FORBIDDEN, message);
        }
    }

    public static class NoEditPostException extends PostException {
        public NoEditPostException(String message) {
            super(HttpStatus.BAD_REQUEST, message);
        }
    }


}
