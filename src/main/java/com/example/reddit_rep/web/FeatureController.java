package com.example.reddit_rep.web;

import com.example.reddit_rep.domain.Feature;
import com.example.reddit_rep.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/products/{productId}/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping("")
    public String createFeature(@PathVariable Long productId) {
        Feature feature = featureService.creatFeature(productId);
        return "redirect:/products/" + productId + "/features/" + feature.getId();
    }

    @GetMapping("{featureId}")
    public String getFeature(@PathVariable Long productId, @PathVariable Long featureId, ModelMap modelMap) {
        Optional<Feature> featureOpt = featureService.findById(featureId);
        if (featureOpt.isPresent()) {
            modelMap.put("feature", featureOpt.get());
        } //TODO: handle situation can't find feature by its id

        return "feature";
    }

    @PostMapping("{featureId}")
    public String updateFeature(Feature feature, @PathVariable Long productId, @PathVariable Long featureId) {

        feature = featureService.save(feature);

        String encodedProductName = null;
        try {
            encodedProductName = URLEncoder.encode(feature.getProduct().getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "redirect:/p/" + encodedProductName;
    }
}
