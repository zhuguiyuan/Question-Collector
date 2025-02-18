package com.example.questioncollector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.questioncollector.entity.DemoEntity;
import com.example.questioncollector.repository.DemoRepository;

@RestController
public class DemoController {
    @Autowired
    private DemoRepository demoRepository;

    @GetMapping("/test-db")
    public ResponseEntity<String> testDb() {
        var entity = new DemoEntity();
        entity.setContent("H2测试数据");
        demoRepository.save(entity);
        return new ResponseEntity<>("已存储 " + demoRepository.count() + " 条记录", HttpStatus.OK);
    }

}
