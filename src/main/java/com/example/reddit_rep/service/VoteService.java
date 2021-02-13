package com.example.reddit_rep.service;

import com.example.reddit_rep.domain.Vote;
import com.example.reddit_rep.repo.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> findAll() {
        return voteRepository.findAll();
    }

    public void saveVote(Vote vote) {
        voteRepository.save(vote);
    }
}
