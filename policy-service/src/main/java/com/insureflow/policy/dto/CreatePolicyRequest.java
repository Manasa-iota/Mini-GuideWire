package com.insureflow.policy.dto;

import com.insureflow.policy.entity.PolicyType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePolicyRequest(
        @NotNull Long customerId,
        @NotNull PolicyType policyType,
        @NotNull BigDecimal coverageAmount,
        @NotNull BigDecimal premiumAmount,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        String riskCategory
) {
}
