package com.example.questioncollector.service;

import com.example.questioncollector.entity.Question;
import com.example.questioncollector.entity.User;
import com.example.questioncollector.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
  @Autowired
  private QuestionRepository questionRepository;

  public Question createQuestion(User user, String title, String content) {
    Question question = new Question();
    question.setAuthor(user);
    question.setTitle(title);
    question.setContent(content);
    return questionRepository.save(question);
  }
}