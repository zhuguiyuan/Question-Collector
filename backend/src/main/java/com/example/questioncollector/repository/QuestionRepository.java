package com.example.questioncollector.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.questioncollector.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
