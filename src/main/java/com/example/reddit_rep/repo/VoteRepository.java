package com.example.reddit_rep.repo;

import com.example.reddit_rep.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
