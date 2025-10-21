package com.example.community.users.UserController;

import com.example.community.users.UsersService;
import com.example.community.users.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/api/users")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        System.out.println("signUp");
        SignUpResponse signUpResponse = usersService.signup(signUpRequest);
        System.out.println(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponse);
    }

    @PostMapping("api/users/auth")
    ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) {
        SignInResponse signInResponse = usersService.signIn(signInRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(signInResponse);
    }

    @PatchMapping("/api/users/{id}/password")
    public ResponseEntity<SimpleResponse> editPassword(@PathVariable Long id, @Valid @RequestBody EditPasswordRequest editPasswordRequest) {
        SimpleResponse simpleResponse = usersService.editPassword(id, editPasswordRequest);
        return ResponseEntity.ok(simpleResponse);
    }

    @PatchMapping("/api/users/{id}/profile")
    ResponseEntity<SimpleResponse> editProfile(@Valid @RequestBody EditProfileRequest editProfileRequest, @PathVariable Long id) {
        SimpleResponse simpleResponse = usersService.editProfile(id, editProfileRequest);
        return ResponseEntity.ok(simpleResponse);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<SimpleResponse> softDelete(@PathVariable Long id) {
        SimpleResponse simpleResponse = usersService.softDelete(id);
        return ResponseEntity.ok(simpleResponse);
    }
}