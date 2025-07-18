package com.munusync.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTTokenProvider jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    )throws ServletException, IOException {
        //extract the value of the HTTP header Authorization from the request.
        //If the client didn’t send an Authorization header, then authHeader will be null
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        //check if the request has a valid Authorization header
        // the header always start with Bearer (length 7 with the space)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            //Pass the request to the next filter in the chain (or controller).
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtService.extractEmail(jwt);

        if (userEmail != null && jwtService.validateToken(jwt)) {
            // Get Authentication object from JWT
            var authentication = jwtService.getAuthentication(jwt);

            // Set authentication in security context
            org.springframework.security.core.context.SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
