package com.example.questioncollector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.questioncollector.entity.DemoEntity;

public interface DemoRepository extends JpaRepository<DemoEntity, Long> {

}