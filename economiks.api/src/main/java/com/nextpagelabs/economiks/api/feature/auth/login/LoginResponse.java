package com.nextpagelabs.economiks.api.feature.auth.login;

public record LoginResponse(
        String token,
        long expiresIn
) { }
