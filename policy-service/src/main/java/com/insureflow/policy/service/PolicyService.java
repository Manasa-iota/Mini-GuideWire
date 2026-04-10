package com.insureflow.policy.service;

import com.insureflow.policy.dto.CreatePolicyRequest;
import com.insureflow.policy.dto.PolicyResponse;

import java.util.List;

public interface PolicyService {
    PolicyResponse create(CreatePolicyRequest request);
    PolicyResponse getById(Long id);
    List<PolicyResponse> getByCustomerId(Long customerId);
    PolicyResponse activate(Long id);
    PolicyResponse cancel(Long id);
}
