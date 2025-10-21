package com.example.community.users;

import com.example.community.common.exception.CommonResponse;
import com.example.community.users.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "회원가입")
    @PostMapping("")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "가입 성공")
    })
    public ResponseEntity<CommonResponse<SignUpResponse>> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = usersService.signup(signUpRequest);
        return UserApiResponse.created(HttpStatus.CREATED, "회원가입 성공", signUpResponse);
    }

    @Operation(summary = "로그인")
    @PostMapping("/auth")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "로그인 성공")
    })
    public ResponseEntity<CommonResponse<SignInResponse>> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        SignInResponse signInResponse = usersService.signIn(signInRequest);
        return UserApiResponse.created(HttpStatus.CREATED,
                "로그인 성공",
                signInResponse);
    }

    @Operation(summary = "비밀번호 변경")
    @PatchMapping("/{id}/password")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "비밀번호 변경 성공")
    })
    public ResponseEntity<CommonResponse<SimpleResponse>> editPassword(@PathVariable Long id, @Valid @RequestBody EditPasswordRequest editPasswordRequest) {
        SimpleResponse simpleResponse = usersService.editPassword(id, editPasswordRequest);
        return UserApiResponse.ok(HttpStatus.OK,
                "비밀번호 변경 성공",
                simpleResponse);
    }

    @Operation(summary = "프로필 변경")
    @PatchMapping("/{id}/profile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "프로필 변경 성공")
    })
    public ResponseEntity<CommonResponse<SimpleResponse>> editProfile(@Valid @RequestBody EditProfileRequest editProfileRequest, @PathVariable Long id) {
        SimpleResponse simpleResponse = usersService.editProfile(id, editProfileRequest);
        return UserApiResponse.ok(HttpStatus.OK,
                "프로필 변경 성공",
                simpleResponse);
    }

    @Operation(summary = "회원 삭제")
    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공")
    })
    public ResponseEntity<CommonResponse<SimpleResponse>> softDelete(@PathVariable Long id) {
        SimpleResponse simpleResponse = usersService.softDelete(id);
        return UserApiResponse.ok(HttpStatus.OK,
                "회원 삭제 성공",
                simpleResponse);
    }
}