package com.example.questioncollector.repository;

import com.example.questioncollector.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
