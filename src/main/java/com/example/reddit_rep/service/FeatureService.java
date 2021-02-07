package com.example.reddit_rep.service;

import com.example.reddit_rep.domain.Feature;
import com.example.reddit_rep.domain.Product;
import com.example.reddit_rep.repo.FeatureRepository;
import com.example.reddit_rep.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FeatureRepository featureRepository;

    public Feature creatFeature(Long productId) {
        Feature feature = new Feature();

        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            feature.setProduct(product);
            product.getFeatures().add(feature);

            feature.setStatus("Pending review");
            return (Feature) featureRepository.save(feature);
        }
        return feature;
    }

    public Feature save(Feature feature) {
        return featureRepository.save(feature);
    }

    public Optional<Feature> findById(Long featureId) {
        return featureRepository.findById(featureId);
    }
}
