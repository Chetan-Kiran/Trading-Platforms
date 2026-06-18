package com.tradingplatform.project.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.tradingplatform.project.auth.dto.LoginRequest;
import com.tradingplatform.project.entity.User;
import com.tradingplatform.project.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            JwtUtil jwtUtil,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request
    ){

        User user =
                userRepository
                        .findByUsername(
                                request.getUsername()
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Invalid username"
                                )
                        );

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPasswordHash()
                );

        if(!matches){
            throw new RuntimeException(
                    "Invalid password"
            );
        }

        return jwtUtil.generateToken(
                user.getUsername()
        );
    }
}