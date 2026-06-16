package com.tradingplatform.project.auth;

import org.springframework.web.bind.annotation.*;

import com.tradingplatform.project.auth.dto.LoginRequest;
// import com.tradingplatform.project.auth.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(
        JwtUtil jwtUtil
    ){
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(
        @RequestBody
        LoginRequest request
    ){

        return jwtUtil.generateToken(
            request.getUsername()
        );
    }
}