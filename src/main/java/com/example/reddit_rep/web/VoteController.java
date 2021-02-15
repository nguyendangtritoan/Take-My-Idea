package com.example.reddit_rep.web;

import com.example.reddit_rep.domain.Feature;
import com.example.reddit_rep.domain.User;
import com.example.reddit_rep.domain.Vote;
import com.example.reddit_rep.service.FeatureService;
import com.example.reddit_rep.service.VoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/product/id/{productId}/feature/id/{featureId}/vote")
public class VoteController {

    private final VoteService voteService;
    private final FeatureService featureService;

    public VoteController(VoteService voteService, FeatureService featureService) {
        this.voteService = voteService;
        this.featureService = featureService;
    }

    @PostMapping("/")
    public String vote(@AuthenticationPrincipal User user, @PathVariable Long productId,
                       @PathVariable Long featureId, @RequestParam(name = "myVote") boolean myVote, Vote vote) {

        vote.setUpVote(myVote);
        vote.setUser(user);
        Optional<Feature> featureOpt = featureService.findById(featureId);
        featureOpt.ifPresent(vote::setFeature);
        Vote voteFromDB = voteService.checkVote(featureId, user.getId());


        if (voteFromDB != null) {
            featureOpt.ifPresent(feature -> feature.vote(!voteFromDB.isUpVote()));
            voteService.deleteVote(featureId, user.getId());

        } else {
            featureOpt.ifPresent(feature -> feature.vote(myVote));
            voteService.saveVote(vote);
        }


        return "redirect:/product/id/" + productId + "/feature/id/" + featureId;
    }

}
