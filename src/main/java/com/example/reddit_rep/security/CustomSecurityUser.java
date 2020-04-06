package com.example.reddit_rep.security;

import com.example.reddit_rep.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public class CustomSecurityUser extends User implements UserDetails {

    public CustomSecurityUser(User user) {
        this.setAuthoritySet(user.getAuthoritySet());
        this.setId(user.getId());
        this.setName(user.getName());
        this.setPassword(user.GetPassword());
        this.setUsername(user.GetUsername());
    }

    public CustomSecurityUser() {

    }

    @Override
    public Set<Authority> getAuthorities() {
        return this.getAuthoritySet();
    }

    @Override
    public String getPassword() {
        return this.GetPassword();
    }

    @Override
    public String getUsername() {
        return this.GetUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
