package com.example.reddit_rep.repo;

import com.example.reddit_rep.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

}
