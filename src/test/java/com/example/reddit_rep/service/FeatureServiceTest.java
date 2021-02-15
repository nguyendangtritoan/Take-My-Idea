package com.example.reddit_rep.service;

import com.example.reddit_rep.repo.VoteRepository;
import com.example.reddit_rep.web.VoteController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(VoteController.class)
public class FeatureServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteRepository voteRepository;

    @MockBean
    private VoteService voteService;

    @Test
    public void testMethod() {

    }
}
