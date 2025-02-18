package com.example.questioncollector.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuestionRequest {
    private String title;
    private List<ContentBlockDto> contentBlock;
}
