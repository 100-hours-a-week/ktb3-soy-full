package com.example.community.users;

import com.example.community.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserException extends BusinessException {

    public UserException(HttpStatus status, String message) {
        super(status, message);
    }

    public static class UserNotFoundException extends BusinessException {
        public UserNotFoundException(String message) {
            super(HttpStatus.NOT_FOUND, message);
        }
    }

    public static class WrongPasswordException extends BusinessException {
        public WrongPasswordException(String message) {
            super(HttpStatus.UNAUTHORIZED, message);
        }
    }

    public static class InvalidCurrentPasswordException extends BusinessException {
        public InvalidCurrentPasswordException(String message) {
            super(HttpStatus.CONFLICT, message);
        }
    }

    public static class SamePasswordException extends BusinessException {
        public SamePasswordException(String message) {
            super(HttpStatus.BAD_REQUEST, message);
        }
    }

    public static class SameNicknameException extends BusinessException {
        public SameNicknameException(String message) {
            super(HttpStatus.CONFLICT, message);
        }
    }

    public static class SameProfileImgException extends BusinessException {
        public SameProfileImgException(String message) {
            super(HttpStatus.CONFLICT, message);
        }
    }

    public static class UserNicknameAlreadyExistsException extends BusinessException {
        public UserNicknameAlreadyExistsException(String message) {
            super(HttpStatus.CONFLICT, message);
        }
    }

}
