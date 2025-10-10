package com.example.community.controller;

import com.example.community.dto.SimpleResponse;
import com.example.community.service.SoftDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SoftDeleteController {
    private final SoftDeleteService softDeleteService;
    @Autowired
    public SoftDeleteController(SoftDeleteService softDeleteService) {
        this.softDeleteService = softDeleteService;
    }

    @PatchMapping("/api/users/delete/{id}")
    public ResponseEntity<SimpleResponse> softDelete(@PathVariable Long id){
        SimpleResponse simpleResponse = softDeleteService.softDelete(id);
        return ResponseEntity.ok(simpleResponse);
    }
}
