package com.example.reddit_rep.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SubComment implements Comparable<SubComment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date createdDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Comment parent;

    public SubComment() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(SubComment that) {
        int compareValue = this.createdDate.compareTo(that.createdDate);
        if (compareValue == 0) {
            compareValue = this.id.compareTo(that.id);
        }
        return compareValue;
    }
}
