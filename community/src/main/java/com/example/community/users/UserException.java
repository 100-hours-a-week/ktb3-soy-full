package com.example.community.users;

import com.example.community.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserException extends BusinessException {

    public UserException(HttpStatus status, String message) {
        super(status, message);
    }

    public static class UserNotFoundException extends UserException {
        public UserNotFoundException(String message) {
            super(HttpStatus.NOT_FOUND, message);
        }
    }

    public static class WrongPasswordException extends UserException {
        public WrongPasswordException(String message) {
            super(HttpStatus.UNAUTHORIZED, message);
        }
    }

    public static class InvalidCurrentPasswordException extends UserException {
        public InvalidCurrentPasswordException(String message) {
            super(HttpStatus.CONFLICT, message);
        }
    }

    public static class SamePasswordException extends UserException {
        public SamePasswordException(String message) {
            super(HttpStatus.CONFLICT, message);
        }
    }

    public static class SameNicknameException extends UserException {
        public SameNicknameException(String message) {
            super(HttpStatus.CONFLICT, message);
        }
    }

    public static class SameProfileImgException extends UserException {
        public SameProfileImgException(String message) {
            super(HttpStatus.CONFLICT, message);
        }
    }

    public static class UserNicknameAlreadyExistsException extends UserException {
        public UserNicknameAlreadyExistsException(String message) {
            super(HttpStatus.CONFLICT, message);
        }
    }

}
