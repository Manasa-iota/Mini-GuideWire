package com.insureflow.policy.dto;

import com.insureflow.policy.entity.PolicyStatus;
import com.insureflow.policy.entity.PolicyType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PolicyResponse(
        Long id,
        String policyNumber,
        Long customerId,
        PolicyType policyType,
        BigDecimal coverageAmount,
        BigDecimal premiumAmount,
        LocalDate startDate,
        LocalDate endDate,
        PolicyStatus status,
        String riskCategory
) {
}
