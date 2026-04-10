package com.insureflow.claims.client;

import com.insureflow.common.exception.DomainException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class PolicyClient {
    private final RestClient client;

    public PolicyClient(@Value("${clients.policy-service-url:http://localhost:8083}") String url) {
        this.client = RestClient.builder().baseUrl(url).build();
    }

    public void validateActivePolicy(Long policyId) {
        try {
            Map<String, Object> response = client.get().uri("/api/policies/{id}", policyId)
                    .retrieve().body(new ParameterizedTypeReference<>() {});
            if (response == null || !"ACTIVE".equals(String.valueOf(response.get("status")))) {
                throw new DomainException("CLAIM_POLICY_INACTIVE", "Claim can only be filed on active policy");
            }
        } catch (Exception ex) {
            throw new DomainException("CLAIM_POLICY_CHECK_FAILED", "Policy validation failed");
        }
    }
}
