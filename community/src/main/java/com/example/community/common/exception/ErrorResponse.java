package com.example.community.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final int status;
    private final String message;
    private final LocalDateTime timestamp;
    private List<ValidationError> validErrors;

    @Getter
    @RequiredArgsConstructor
    public static class ValidationError{
        private final String field;
        private final String message;
    }

    public void addValidationError(String field, String message) {
        if(Objects.isNull(validErrors)){
            validErrors = new ArrayList<>();
        }
        validErrors.add(new ValidationError(field, message));
    }

}
