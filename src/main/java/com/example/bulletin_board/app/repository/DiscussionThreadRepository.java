package com.example.bulletin_board.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import com.example.bulletin_board.app.entity.DiscussionThread;

public interface DiscussionThreadRepository extends CrudRepository<DiscussionThread,Long> {
}
