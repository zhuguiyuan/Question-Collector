package com.example.questioncollector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.questioncollector.service.FileStorageService;

@Controller
@RequestMapping("/api/upload")
public class FileUploadController {
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(fileStorageService.storeFile(file));
    }

}
