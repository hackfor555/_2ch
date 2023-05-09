package com.example.bulletin_board.app.repository;

import com.example.bulletin_board.app.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}
