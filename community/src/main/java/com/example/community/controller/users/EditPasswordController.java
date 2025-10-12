package com.example.community.controller;

import com.example.community.dto.EditPasswordRequest;
import com.example.community.dto.SimpleResponse;
import com.example.community.service.EditPasswordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EditPasswordController {
    private EditPasswordService editPasswordService;
    @Autowired
    public EditPasswordController(EditPasswordService editPasswordService) {
        this.editPasswordService = editPasswordService;
    }

    @PatchMapping("/api/users/editPassword/{id}")
    public ResponseEntity<SimpleResponse> editPassword(@PathVariable Long id, @Valid @RequestBody EditPasswordRequest editPasswordRequest){
        SimpleResponse simpleResponse = editPasswordService.editPassword(id, editPasswordRequest);
        return ResponseEntity.ok(simpleResponse);
    }

}
