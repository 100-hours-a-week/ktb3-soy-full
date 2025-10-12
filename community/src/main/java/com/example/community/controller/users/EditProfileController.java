package com.example.community.controller;

import com.example.community.dto.EditProfileRequest;
import com.example.community.dto.SimpleResponse;
import com.example.community.service.EditProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditProfileController {
    private EditProfileService editProfileService;
    @Autowired
    public EditProfileController(EditProfileService editProfileService) {
        this.editProfileService = editProfileService;
    }

    @PatchMapping("/api/users/editProfile/{id}")
    ResponseEntity<SimpleResponse> editProfile(@Valid @RequestBody EditProfileRequest editProfileRequest, @PathVariable Long id){
        SimpleResponse simpleResponse = editProfileService.editProfile(id, editProfileRequest);
        return ResponseEntity.ok(simpleResponse);
    }
}
