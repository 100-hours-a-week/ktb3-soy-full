package com.example.community.common.exception;

import com.example.community.common.dto.ErrorResponse;
import com.example.community.posts.PostsController;
import com.example.community.comments.CommentsController;
import com.example.community.likes.LikesController;
import com.example.community.users.UsersController.UsersController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.valueOf(statusCode.value()));
    }

    private ResponseEntity<Object> buildErrorResponse(String message,
                                                      HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message, LocalDateTime.now());
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    // 400
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBadRequestException(HttpClientErrorException.BadRequest exception, WebRequest request) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 401
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleUnAuthorizedException(HttpClientErrorException.Unauthorized exception, WebRequest request) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // 403
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleForbiddenException(HttpClientErrorException.Forbidden exception, WebRequest request) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    // 409
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleConflictException(HttpClientErrorException.NotFound exception, WebRequest request) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.CONFLICT);
    }

    //validation 검사
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode statusCode,
                                                                  WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getBindingResult().getFieldError().getDefaultMessage(), LocalDateTime.now());
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // custom
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), ex.getStatus());
    }

    // 500 InternalServerError
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}