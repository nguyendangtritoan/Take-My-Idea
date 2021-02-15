package com.example.reddit_rep.web;

import com.example.reddit_rep.domain.Feature;
import com.example.reddit_rep.domain.User;
import com.example.reddit_rep.service.FeatureService;
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

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
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
            modelMap.put("votes", feature.getVotes());
            //modelMap.put("comments", feature.getComments());
        } //TODO: handle situation can't find feature by its id

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
