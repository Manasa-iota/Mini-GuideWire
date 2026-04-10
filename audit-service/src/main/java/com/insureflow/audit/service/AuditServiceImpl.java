package com.insureflow.audit.service;

import com.insureflow.audit.dto.AuditRequest;
import com.insureflow.audit.dto.AuditResponse;
import com.insureflow.audit.entity.AuditLog;
import com.insureflow.audit.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {
    private final AuditLogRepository repository;
    public AuditServiceImpl(AuditLogRepository repository){this.repository=repository;}
    public AuditResponse create(AuditRequest request){
        AuditLog log=new AuditLog();
        log.setActor(request.actor()); log.setAction(request.action()); log.setEntityType(request.entityType());
        log.setEntityId(request.entityId()); log.setOldValue(request.oldValue()); log.setNewValue(request.newValue());
        log.setCorrelationId(request.correlationId()); log.setSourceService(request.sourceService());
        return map(repository.save(log));
    }
    public List<AuditResponse> byEntity(String entityType, String entityId){return repository.findByEntityTypeAndEntityId(entityType, entityId).stream().map(this::map).toList();}
    private AuditResponse map(AuditLog log){return new AuditResponse(log.getId(),log.getActor(),log.getAction(),log.getEntityType(),log.getEntityId(),log.getOldValue(),log.getNewValue(),log.getCorrelationId(),log.getSourceService(),log.getCreatedAt());}
}
