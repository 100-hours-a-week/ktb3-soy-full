package com.example.community.likes;

import com.example.community.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class LikeException extends BusinessException {

    public LikeException(HttpStatus status, String message) {
        super(status, message);
    }

    public static class AlreadyLikedException extends BusinessException {
        public AlreadyLikedException(String message) {
            super(HttpStatus.BAD_REQUEST, message);
        }
    }

    public static class AlreadyDislikedException extends BusinessException {
        public AlreadyDislikedException(String message) {
            super(HttpStatus.BAD_REQUEST, message);
        }
    }
}
