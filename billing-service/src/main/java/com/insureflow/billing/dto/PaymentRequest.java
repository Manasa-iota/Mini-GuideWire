package com.insureflow.billing.dto;

import jakarta.validation.constraints.NotBlank;

public record PaymentRequest(@NotBlank String paymentMethod, @NotBlank String transactionReference, @NotBlank String idempotencyKey) {}
