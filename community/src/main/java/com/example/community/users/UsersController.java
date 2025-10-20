package com.example.community.users.UsersController;

import com.example.community.users.UsersService;
import com.example.community.users.dto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        System.out.println("signUp");
        SignUpResponse signUpResponse = usersService.signup(signUpRequest);
        System.out.println(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponse);
    }

    @PostMapping("/auth")
    ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) {
        SignInResponse signInResponse = usersService.signIn(signInRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(signInResponse);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<SimpleResponse> editPassword(@PathVariable Long id, @Valid @RequestBody EditPasswordRequest editPasswordRequest) {
        SimpleResponse simpleResponse = usersService.editPassword(id, editPasswordRequest);
        return ResponseEntity.ok(simpleResponse);
    }

    @PatchMapping("/{id}/profile")
    ResponseEntity<SimpleResponse> editProfile(@Valid @RequestBody EditProfileRequest editProfileRequest, @PathVariable Long id) {
        SimpleResponse simpleResponse = usersService.editProfile(id, editProfileRequest);
        return ResponseEntity.ok(simpleResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleResponse> softDelete(@PathVariable Long id) {
        SimpleResponse simpleResponse = usersService.softDelete(id);
        return ResponseEntity.ok(simpleResponse);
    }
}