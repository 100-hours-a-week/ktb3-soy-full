package com.example.community.users.UserController;

import com.example.community.users.exception.UserNotFoundException;
import com.example.community.users.UsersService;
import com.example.community.users.dto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    private UsersService usersService;
    @Autowired
    public UsersController(UsersService usersService)
    {
        this.usersService = usersService;
    }

    @PostMapping("/api/users/signup")
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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @PatchMapping("/api/users/editPassword/{id}")
    public ResponseEntity<SimpleResponse> editPassword(@PathVariable Long id, @Valid @RequestBody EditPasswordRequest editPasswordRequest){
        SimpleResponse simpleResponse = usersService.editPassword(id, editPasswordRequest);
        return ResponseEntity.ok(simpleResponse);
    }

    @PatchMapping("/api/users/editProfile/{id}")
    ResponseEntity<SimpleResponse> editProfile(@Valid @RequestBody EditProfileRequest editProfileRequest, @PathVariable Long id){
        SimpleResponse simpleResponse = usersService.editProfile(id, editProfileRequest);
        return ResponseEntity.ok(simpleResponse);
    }

    @PatchMapping("/api/users/delete/{id}")
    public ResponseEntity<SimpleResponse> softDelete(@PathVariable Long id){
        SimpleResponse simpleResponse = usersService.softDelete(id);
        return ResponseEntity.ok(simpleResponse);
    }

}
