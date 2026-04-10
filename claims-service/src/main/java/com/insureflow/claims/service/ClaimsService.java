package com.insureflow.claims.service;

import com.insureflow.claims.dto.ClaimRequest;
import com.insureflow.claims.dto.ClaimResponse;

import java.math.BigDecimal;

public interface ClaimsService {
    ClaimResponse file(ClaimRequest request);
    ClaimResponse get(Long id);
    ClaimResponse review(Long id, String adjuster);
    ClaimResponse approve(Long id, BigDecimal approvedAmount);
    ClaimResponse reject(Long id);
    ClaimResponse settle(Long id);
}
