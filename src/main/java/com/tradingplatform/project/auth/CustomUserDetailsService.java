package com.tradingplatform.project.auth;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tradingplatform.project.repository.*;
import com.tradingplatform.project.entity.*;

@Service
public class CustomUserDetailsService
implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(
        UserRepository userRepository
    ){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
        String username
    ) throws UsernameNotFoundException {

        User user =
            userRepository
                .findByUsername(username)
                .orElseThrow(
                    () ->
                    new UsernameNotFoundException(
                        username
                    )
                );

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPasswordHash())
            .authorities("USER")
            .build();
    }
}
