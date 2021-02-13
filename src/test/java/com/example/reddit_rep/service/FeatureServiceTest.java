package com.example.reddit_rep.service;

import com.example.reddit_rep.repo.FeatureRepository;
import com.example.reddit_rep.web.FeatureController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FeatureController.class)
public class FeatureServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeatureRepository featureRepository;

    @MockBean
    private FeatureService featureService;

    @Test
    public void testMethod() {


    }
}
