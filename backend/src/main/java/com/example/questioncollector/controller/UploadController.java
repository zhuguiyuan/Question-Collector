package com.example.questioncollector.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        // return fileStorageService.save(file);
        return "TODO";
    }

}
