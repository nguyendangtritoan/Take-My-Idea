package com.example.reddit_rep.service;

import com.example.reddit_rep.domain.User;
import com.example.reddit_rep.repo.UserRepository;
import com.example.reddit_rep.security.Authority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        String encodePass = passwordEncoder.encode((user.getPassword()));
        user.setPassword(encodePass);
        System.out.println(user.getPassword());
        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        authority.setUser(user);
        user.getAuthoritySet().add(authority);

        return userRepo.save(user);
    }


}
