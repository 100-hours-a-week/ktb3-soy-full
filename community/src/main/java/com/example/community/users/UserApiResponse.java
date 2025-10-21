package com.example.community.users;

import com.example.community.common.exception.CommonResponse;
import com.example.community.common.exception.BusinessException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserApiResponse {
    public static <T> ResponseEntity<CommonResponse<T>> ok(HttpStatus status, String message, T data) {
        return ResponseEntity.status(status)
                .body(CommonResponse.success(status.value(), message, data));
    }

    public static <T> ResponseEntity<CommonResponse<T>> created(HttpStatus status, String message, T data){
        return ResponseEntity.status(status)
                .body(CommonResponse.success(status.value(), message, data));
    }

    public static <T> ResponseEntity<CommonResponse<T>> fail(BusinessException e) {
        return ResponseEntity.status(e.getStatus())
                .body(CommonResponse.fail(e.getStatus().value(), e.getMessage()));
    }
}