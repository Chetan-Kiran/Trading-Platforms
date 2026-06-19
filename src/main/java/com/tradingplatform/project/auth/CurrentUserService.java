package com.tradingplatform.project.auth;


import com.tradingplatform.project.repository.*;
import com.tradingplatform.project.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUserService(
        UserRepository userRepository
    ){
        this.userRepository = userRepository;
    }

    public User getCurrentUser(){

        Authentication auth =
            SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username =
            auth.getName();

        return userRepository
            .findByUsername(username)
            .orElseThrow();
    }
}
