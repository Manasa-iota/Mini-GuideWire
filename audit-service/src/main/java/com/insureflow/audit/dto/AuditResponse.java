package com.insureflow.audit.dto;

import java.time.Instant;

public record AuditResponse(Long id, String actor, String action, String entityType, String entityId, String oldValue, String newValue, String correlationId, String sourceService, Instant createdAt) {}
