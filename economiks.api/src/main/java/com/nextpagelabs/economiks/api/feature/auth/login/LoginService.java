package com.nextpagelabs.economiks.api.feature.auth.login;

import com.nextpagelabs.economiks.api.core.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.expiration.ms}")
    private long expiration;

    public LoginResponse execute(LoginRequest request) {

        var authToken = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );

        Authentication authentication = authenticationManager.authenticate(authToken);
        String token = tokenService.generateToken(authentication);

        return new LoginResponse(token, expiration);
    }
}
