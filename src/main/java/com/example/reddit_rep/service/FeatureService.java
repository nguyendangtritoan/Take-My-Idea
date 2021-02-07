package com.example.reddit_rep.service;

import com.example.reddit_rep.domain.Feature;
import com.example.reddit_rep.domain.Product;
import com.example.reddit_rep.domain.User;
import com.example.reddit_rep.repo.FeatureRepository;
import com.example.reddit_rep.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService {

    private final ProductRepository productRepository;

    private final FeatureRepository featureRepository;

    public FeatureService(ProductRepository productRepository, FeatureRepository featureRepository) {
        this.productRepository = productRepository;
        this.featureRepository = featureRepository;
    }

    public Feature creatFeature(Long productId, User user) {
        Feature feature = new Feature();

        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            feature.setStatus("Pending review");

            feature.setProduct(product);
            //product.getFeatures().add(feature); //TODO: Does product auto update its Set of feature when feature.setProduct(p) is executed

            feature.setUser(user);

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
