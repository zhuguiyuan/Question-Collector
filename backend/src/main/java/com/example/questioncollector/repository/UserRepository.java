package com.example.questioncollector.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.questioncollector.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
