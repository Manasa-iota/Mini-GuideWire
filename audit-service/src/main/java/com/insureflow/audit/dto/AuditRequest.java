package com.insureflow.audit.dto;

public record AuditRequest(String actor, String action, String entityType, String entityId, String oldValue, String newValue, String correlationId, String sourceService) {}
