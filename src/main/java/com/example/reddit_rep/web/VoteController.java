package com.example.reddit_rep.web;

import com.example.reddit_rep.domain.User;
import com.example.reddit_rep.domain.Vote;
import com.example.reddit_rep.service.VoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/vote/save")
    public String Vote(@AuthenticationPrincipal User user, Vote vote) {

        voteService.saveVote(vote);
        return "";
    }

}
