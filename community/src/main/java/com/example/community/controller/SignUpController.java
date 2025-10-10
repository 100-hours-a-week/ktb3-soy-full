package com.example.community.controller;

import com.example.community.dto.SignInRequest;
import com.example.community.dto.SignInResponse;
import com.example.community.dto.SignUpRequest;
import com.example.community.dto.SignUpResponse;
import com.example.community.service.SignUpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    public SignUpService signUpService;
    @Autowired
    public SignUpController(SignUpService userService) {
        this.signUpService = userService;
    }

    @PostMapping("/api/users/signup")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = signUpService.signup(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponse);
    }
}
