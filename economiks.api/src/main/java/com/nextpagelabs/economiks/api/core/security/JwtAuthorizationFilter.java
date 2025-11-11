package com.nextpagelabs.economiks.api.core.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // Check if the Authorization header is present and starts with "Bearer "
        // If not, continue the filter chain without setting authentication
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the token from the Authorization header
        String token = authHeader.substring("Bearer ".length());

        if (tokenService.validateToken(token)) {
            String username = tokenService.getUsernameFromToken(token); // Extract username from token
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Load user details
            var authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null, // No credentials needed for JWT
                    userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication); // Set authentication in the security context
        }

        filterChain.doFilter(request, response);
    }
}
