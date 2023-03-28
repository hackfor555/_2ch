package com.example._2ch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example._2ch.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}