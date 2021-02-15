package com.example.reddit_rep.web;

import com.example.reddit_rep.domain.Feature;
import com.example.reddit_rep.domain.User;
import com.example.reddit_rep.domain.Vote;
import com.example.reddit_rep.service.FeatureService;
import com.example.reddit_rep.service.VoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;

@Controller
@RequestMapping("/product/id/{productId}/feature")
public class FeatureController {

    private final FeatureService featureService;
    private final VoteService voteService;

    public FeatureController(FeatureService featureService, VoteService voteService) {
        this.featureService = featureService;
        this.voteService = voteService;
    }

    @PostMapping("")
    public String createFeature(@AuthenticationPrincipal User user, @PathVariable Long productId) {

        //TODO: Do not create feature when user have not clicked save button
        Feature feature = featureService.creatFeature(productId, user);
        return "redirect:/product/id/" + productId + "/feature/id/" + feature.getId();
    }

    @GetMapping("/id/{featureId}")
    public String getFeature(@AuthenticationPrincipal User user, @PathVariable Long productId, @PathVariable Long featureId, ModelMap modelMap) {
        Optional<Feature> featureOpt = featureService.findById(featureId);
        if (featureOpt.isPresent()) {
            Feature feature = featureOpt.get();
            modelMap.put("feature", feature);
            //modelMap.put("comments", feature.getComments());
        } //TODO: handle situation can't find feature by its id

        Vote vote = voteService.checkVote(featureId, user.getId());
        if (vote != null) {
            modelMap.put("isVote", true);
            modelMap.put("typeVote", vote.isUpVote());
        } else {
            modelMap.put("isVote", false);
        }

        modelMap.put("user", user);

        return "feature";
    }

    @PostMapping("/id/{featureId}")
    public String updateFeature(@AuthenticationPrincipal User user, Feature feature, @PathVariable Long productId, @PathVariable Long featureId) {

        feature.setUser(user);
        feature = featureService.save(feature);

        String encodedProductName = null;
        try {
            encodedProductName = URLEncoder.encode(feature.getProduct().getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "redirect:/product/name/" + encodedProductName;
    }
}
