package com.insureflow.claims.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ClaimRequest(@NotNull Long policyId, @NotNull Long customerId, String claimType,
                           @NotNull LocalDate incidentDate, @NotNull BigDecimal claimAmount, String description) {}
