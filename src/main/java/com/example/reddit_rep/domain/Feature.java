package com.example.reddit_rep.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feature", orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feature", orphanRemoval = true)
    private Set<Vote> votes = new HashSet<>();

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Feature() {
    }

    public Feature addVote(Vote vote) {
        votes.add(vote);
        vote.setFeature(this);
        return this;
    }

    public Feature removeVote(Vote vote) {
        votes.add(vote);
        vote.setFeature(null);
        return this;
    }

    public Feature addComment(Comment comment) {
        comments.add(comment);
        comment.setFeature(this);
        return this;
    }

    public Feature removeComment(Comment comment) {
        comments.add(comment);
        comment.setFeature(null);
        return this;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}
