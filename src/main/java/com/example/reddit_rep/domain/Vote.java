package com.example.reddit_rep.domain;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean upVote;

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Feature feature;

    public Vote() {
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isUpVote() {
        return upVote;
    }

    public void setUpVote(boolean upVote) {
        this.upVote = upVote;
    }
}
