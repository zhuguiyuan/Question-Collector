package com.example.questioncollector.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.questioncollector.dto.QuestionRequest;
import com.example.questioncollector.entity.Question;
import com.example.questioncollector.repository.UserRepository;
import com.example.questioncollector.service.QuestionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody @Valid QuestionRequest request, Principal principal) {
        var user = userRepository.findByUsername(principal.getName()).orElseThrow(
                () -> new RuntimeException("用户未找到"));
        var contentBlocks = request.getContent();
        var question = questionService.createQuestion(user, request.getTitle(), contentBlocks);
        return ResponseEntity.ok(question);
    }

}
