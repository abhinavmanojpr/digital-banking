package com.digitalbanking.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.JwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // Get Authorization header
        String authHeader = request.getHeader("Authorization");

        // If there is no Bearer token, continue to the next filter
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Remove "Bearer " and get only the JWT
        String jwt = authHeader.substring(7);

        String email;

        try {
            email = jwtService.extractEmail(jwt);
        } catch (JwtException | IllegalArgumentException ex) {
            filterChain.doFilter(request, response);
        return;
        }
        
        // Authenticate only if user is not already authenticated
        if (email != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {
                    System.out.println("JWT Email: " + email);

                    System.out.println("Authentication set in SecurityContext");
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);

                    System.out.println("Authenticated: " + userDetails.getUsername());

            // Validate token
            if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // Tell Spring Security that this user is authenticated
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}