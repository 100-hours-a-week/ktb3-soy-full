package com.example.community.common.exception;
import jakarta.validation.constraints.NotNull;

public class ExceptionDto {
    @NotNull private final Integer code;
    @NotNull private final String message;
    public ExceptionDto(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {return code;}
    public String getMessage() {return message;}
}
