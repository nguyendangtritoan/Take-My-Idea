package com.example.reddit_rep.repo;

import com.example.reddit_rep.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
