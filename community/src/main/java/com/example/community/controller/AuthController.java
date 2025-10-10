package com.example.community.controller;

import com.example.community.dto.SignInRequest;
import com.example.community.dto.SignInResponse;
import com.example.community.exception.UserNotFoundException;
import com.example.community.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {
    public AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("api/users/auth")
    ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) {
        SignInResponse signInResponse = authService.signIn(signInRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(signInResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


}
