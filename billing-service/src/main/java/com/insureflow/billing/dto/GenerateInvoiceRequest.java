package com.insureflow.billing.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record GenerateInvoiceRequest(@NotNull Long customerId, @NotNull Long policyId, @NotNull BigDecimal amount, @NotNull LocalDate dueDate) {}
