package com.nextpagelabs.economiks.api.core.exception;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse (
        int status,
        String message,
        String error,
        Instant timestamp,
        String path,
        Map<String, String> errors
) {
    public static ErrorResponse of(
            int status,
            String message,
            String error,
            String path
    ) {
        return new ErrorResponse(
                status,
                message,
                error,
                Instant.now(),
                path,
                null
        );
    }

    public static ErrorResponse of(
            int status,
            String message,
            String error,
            String path,
            Map<String, String> errors
    ) {
        return new ErrorResponse(
                status,
                message,
                error,
                Instant.now(),
                path,
                errors
        );
    }
}
