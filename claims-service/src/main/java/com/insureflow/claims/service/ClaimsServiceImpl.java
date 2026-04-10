package com.insureflow.claims.service;

import com.insureflow.claims.client.PolicyClient;
import com.insureflow.claims.dto.ClaimRequest;
import com.insureflow.claims.dto.ClaimResponse;
import com.insureflow.claims.entity.Claim;
import com.insureflow.claims.entity.ClaimStatus;
import com.insureflow.claims.repository.ClaimRepository;
import com.insureflow.common.exception.DomainException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ClaimsServiceImpl implements ClaimsService {
    private final ClaimRepository repository;
    private final PolicyClient policyClient;

    public ClaimsServiceImpl(ClaimRepository repository, PolicyClient policyClient) {
        this.repository = repository;
        this.policyClient = policyClient;
    }

    public ClaimResponse file(ClaimRequest request) {
        policyClient.validateActivePolicy(request.policyId());
        Claim claim = new Claim();
        claim.setClaimNumber("CLM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        claim.setPolicyId(request.policyId()); claim.setCustomerId(request.customerId()); claim.setClaimType(request.claimType());
        claim.setIncidentDate(request.incidentDate()); claim.setClaimAmount(request.claimAmount()); claim.setDescription(request.description());
        claim.setStatus(ClaimStatus.FILED);
        return toResponse(repository.save(claim));
    }

    public ClaimResponse get(Long id){ return toResponse(find(id)); }
    public ClaimResponse review(Long id, String adjuster){ Claim c=find(id); c.setStatus(ClaimStatus.UNDER_REVIEW); c.setAssignedAdjuster(adjuster); return toResponse(repository.save(c)); }
    public ClaimResponse approve(Long id, BigDecimal approvedAmount){ Claim c=find(id); c.setStatus(ClaimStatus.APPROVED); c.setApprovedAmount(approvedAmount); return toResponse(repository.save(c)); }
    public ClaimResponse reject(Long id){ Claim c=find(id); c.setStatus(ClaimStatus.REJECTED); return toResponse(repository.save(c)); }
    public ClaimResponse settle(Long id){ Claim c=find(id); if(c.getStatus()==ClaimStatus.REJECTED) throw new DomainException("CLAIM_INVALID_TRANSITION", "Rejected claim cannot be settled"); c.setStatus(ClaimStatus.SETTLED); return toResponse(repository.save(c)); }

    private Claim find(Long id){ return repository.findById(id).orElseThrow(() -> new DomainException("CLAIM_NOT_FOUND", "Claim not found")); }
    private ClaimResponse toResponse(Claim c){ return new ClaimResponse(c.getId(), c.getClaimNumber(), c.getPolicyId(), c.getCustomerId(), c.getClaimType(), c.getIncidentDate(), c.getClaimAmount(), c.getApprovedAmount(), c.getDescription(), c.getStatus(), c.getAssignedAdjuster()); }
}
