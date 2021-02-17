package com.example.reddit_rep.repo;

import com.example.reddit_rep.domain.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCommentRepository extends JpaRepository<SubComment, Long> {
}
