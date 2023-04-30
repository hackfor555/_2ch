package com.example._2ch.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example._2ch.app.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}