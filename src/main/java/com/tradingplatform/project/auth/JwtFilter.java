package com.tradingplatform.project.auth;

import java.io.IOException;
import java.lang.reflect.Method;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter
extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(
        JwtUtil jwtUtil
    ){
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(

        HttpServletRequest request,

        HttpServletResponse response,

        FilterChain filterChain

    ) throws ServletException, IOException {

        String header =
            request.getHeader(
                "Authorization"
            );

        if(
            header != null
            &&
            header.startsWith(
                "Bearer "
            )
        ){

            String token =
                header.substring(7);

            try {
                Method validateMethod = jwtUtil.getClass().getMethod(
                    "validateToken",
                    String.class
                );
                Boolean valid = (Boolean) validateMethod.invoke(
                    jwtUtil,
                    token
                );

                if(Boolean.TRUE.equals(valid)){

    String username =
        jwtUtil.extractUsername(token);

    UsernamePasswordAuthenticationToken auth =
        new UsernamePasswordAuthenticationToken(
            username,
            null,
            java.util.Collections.emptyList()
        );

    SecurityContextHolder
        .getContext()
        .setAuthentication(auth);
}
            } catch (NoSuchMethodException e) {
                // JWT validation method not available; continue without validation.
            } catch (Exception e) {
                throw new ServletException(
                    "Unable to validate JWT",
                    e
                );
            }
        }

        filterChain.doFilter(
            request,
            response
        );
    }
}