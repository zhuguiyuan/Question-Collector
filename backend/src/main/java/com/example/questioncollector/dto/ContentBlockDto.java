package com.example.questioncollector.dto;

import com.example.questioncollector.entity.ContentBlock;

import lombok.Data;

@Data
public class ContentBlockDto {
    private ContentBlock.ContentType type;
    private String data;
}
