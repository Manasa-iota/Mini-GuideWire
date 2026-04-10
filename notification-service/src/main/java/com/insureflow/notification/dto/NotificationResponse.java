package com.insureflow.notification.dto;

import java.time.Instant;

public record NotificationResponse(Long id, Long customerId, String recipient, String channel, String templateType, String payload, String status, Instant createdAt, Instant sentAt) {}
