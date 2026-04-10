package com.insureflow.audit.service;

import com.insureflow.audit.dto.AuditRequest;
import com.insureflow.audit.dto.AuditResponse;

import java.util.List;

public interface AuditService {
    AuditResponse create(AuditRequest request);
    List<AuditResponse> byEntity(String entityType, String entityId);
}
