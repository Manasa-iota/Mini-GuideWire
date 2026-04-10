package com.insureflow.notification.dto;

public record NotificationRequest(Long customerId, String recipient, String channel, String templateType, String payload) {}
