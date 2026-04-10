package com.insureflow.policy.service;

import com.insureflow.common.exception.DomainException;
import com.insureflow.policy.dto.CreatePolicyRequest;
import com.insureflow.policy.dto.PolicyResponse;
import com.insureflow.policy.entity.Policy;
import com.insureflow.policy.entity.PolicyStatus;
import com.insureflow.policy.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PolicyServiceImpl implements PolicyService {
    private final PolicyRepository repository;

    public PolicyServiceImpl(PolicyRepository repository) {
        this.repository = repository;
    }

    @Override
    public PolicyResponse create(CreatePolicyRequest request) {
        Policy policy = new Policy();
        policy.setPolicyNumber("POL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        policy.setCustomerId(request.customerId());
        policy.setPolicyType(request.policyType());
        policy.setCoverageAmount(request.coverageAmount());
        policy.setPremiumAmount(request.premiumAmount());
        policy.setStartDate(request.startDate());
        policy.setEndDate(request.endDate());
        policy.setRiskCategory(request.riskCategory());
        policy.setStatus(PolicyStatus.PENDING_PAYMENT);
        return toResponse(repository.save(policy));
    }

    @Override
    public PolicyResponse getById(Long id) {
        return toResponse(findPolicy(id));
    }

    @Override
    public List<PolicyResponse> getByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId).stream().map(this::toResponse).toList();
    }

    @Override
    public PolicyResponse activate(Long id) {
        Policy policy = findPolicy(id);
        if (policy.getStatus() != PolicyStatus.PENDING_PAYMENT) {
            throw new DomainException("POLICY_INVALID_STATE", "Policy can only be activated from PENDING_PAYMENT");
        }
        policy.setStatus(PolicyStatus.ACTIVE);
        return toResponse(repository.save(policy));
    }

    @Override
    public PolicyResponse cancel(Long id) {
        Policy policy = findPolicy(id);
        if (policy.getStatus() == PolicyStatus.CANCELLED) {
            throw new DomainException("POLICY_ALREADY_CANCELLED", "Policy already cancelled");
        }
        policy.setStatus(PolicyStatus.CANCELLED);
        return toResponse(repository.save(policy));
    }

    private Policy findPolicy(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainException("POLICY_NOT_FOUND", "Policy not found"));
    }

    private PolicyResponse toResponse(Policy policy) {
        return new PolicyResponse(policy.getId(), policy.getPolicyNumber(), policy.getCustomerId(), policy.getPolicyType(),
                policy.getCoverageAmount(), policy.getPremiumAmount(), policy.getStartDate(), policy.getEndDate(),
                policy.getStatus(), policy.getRiskCategory());
    }
}
