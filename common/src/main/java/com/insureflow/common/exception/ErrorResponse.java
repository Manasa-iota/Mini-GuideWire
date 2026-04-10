package com.insureflow.common.exception;

import java.time.Instant;

public record ErrorResponse(
        int status,
        String errorCode,
        String message,
        Instant timestamp,
        String path,
        String correlationId
) {
}
