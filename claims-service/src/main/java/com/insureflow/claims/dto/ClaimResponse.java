package com.insureflow.claims.dto;

import com.insureflow.claims.entity.ClaimStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ClaimResponse(Long id, String claimNumber, Long policyId, Long customerId, String claimType,
                            LocalDate incidentDate, BigDecimal claimAmount, BigDecimal approvedAmount,
                            String description, ClaimStatus status, String assignedAdjuster) {}
