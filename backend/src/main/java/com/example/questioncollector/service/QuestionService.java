package com.example.questioncollector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.questioncollector.entity.ContentBlock;
import com.example.questioncollector.entity.Question;
import com.example.questioncollector.entity.User;
import com.example.questioncollector.repository.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Question createQuestion(User user, String title, List<ContentBlock> content) {
        Question question = new Question();
        question.setCreator(user);
        question.setTitle(title);
        question.setContent(content);
        return questionRepository.save(question);
    }
}