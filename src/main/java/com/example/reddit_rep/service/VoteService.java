package com.example.reddit_rep.service;

import com.example.reddit_rep.domain.Vote;
import com.example.reddit_rep.repo.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Vote checkVote(Long featureId, Long userId) {
        Optional<Vote> voteOpt = voteRepository.findByUserIdAndFeatureId(featureId, userId);

        return voteOpt.orElse(null);
    }

    public void deleteVote(Long featureId, Long userId) {
        voteRepository.deleteVote(featureId, userId);
    }
}
