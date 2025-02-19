package com.example.questioncollector.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuestionRequest {
  @NotBlank(message = "标题不能为空")
  private String title;

  @NotBlank(message = "内容块不能为空")
  private String content;
}
