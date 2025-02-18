package com.example.questioncollector.controller;

import java.security.Principal;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.questioncollector.dto.QuestionRequest;
import com.example.questioncollector.entity.ContentBlock;
import com.example.questioncollector.entity.Question;
import com.example.questioncollector.repository.UserRepository;
import com.example.questioncollector.service.QuestionService;
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
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionRequest request, Principal principal) {
        var user = userRepository.findByUsername(principal.getName()).orElseThrow(
                () -> new RuntimeException("用户未找到"));
        var contentBlocks = request.getContentBlock().stream()
                .map(dto -> new ContentBlock(dto.getType(), dto.getData())).collect(Collectors.toList());
        var question = questionService.createQuestion(user, request.getTitle(), contentBlocks);
        return ResponseEntity.ok(question);
    }

}
