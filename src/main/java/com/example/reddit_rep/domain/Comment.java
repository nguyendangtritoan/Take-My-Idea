package com.example.reddit_rep.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
public class Comment implements Comparable<Comment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Feature feature;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    @OrderBy("createdDate, id")
    private Set<SubComment> children = new TreeSet<>();

    public Comment() {
    }

    public void addChildren(SubComment subComment) {
        children.add(subComment);
        subComment.setParent(this);
    }

    public void removeChildren(SubComment subComment) {
        children.remove(subComment);
        subComment.setParent(null);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Set<SubComment> getChildren() {
        return children;
    }

    public void setChildren(Set<SubComment> children) {
        this.children = children;
    }

    @Override
    public int compareTo(Comment that) {
        int comparedValue = this.createdDate.compareTo(that.createdDate);
        if (comparedValue == 0) {
            comparedValue = this.id.compareTo(that.id);
        }
        return comparedValue;
    }
}
