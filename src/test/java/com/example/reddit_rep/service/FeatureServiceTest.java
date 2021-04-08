package com.example.reddit_rep.service;

import com.example.reddit_rep.domain.Feature;
import com.example.reddit_rep.repo.FeatureRepository;
import com.example.reddit_rep.repo.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class FeatureServiceTest {

    private FeatureService featureService;

    private FeatureRepository featureRepository;
    private ProductRepository productRepository;

    @Test
    public void testSaveMethod() {
        featureRepository = mock(FeatureRepository.class);
        productRepository = mock(ProductRepository.class);
        featureService = new FeatureService(productRepository, featureRepository);
        Feature feature = new Feature();
        feature.setId(1L);
        feature.setStatus("Hello");
        Mockito.when(featureRepository.save(feature)).thenReturn(feature);
        assertEquals(featureService.save(feature).getStatus(), "Hello");
        //assertEquals(featureRepository.save(new Feature()).getId(),feature.getId());
    }

    @Test
    public void testFindMethod() {
        featureRepository = mock(FeatureRepository.class);
        productRepository = mock(ProductRepository.class);
        featureService = new FeatureService(productRepository, featureRepository);
        Feature feature = new Feature();
        feature.setId(1L);
        feature.setStatus("Hello");
        Mockito.when(featureRepository.findById(1L)).thenReturn(Optional.of(feature));
        assertEquals(featureService.findById(1L).get().getStatus(), "Hello");
        //assertEquals(featureRepository.save(new Feature()).getId(),feature.getId());
    }

}
