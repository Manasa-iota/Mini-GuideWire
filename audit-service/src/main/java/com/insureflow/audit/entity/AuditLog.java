package com.insureflow.audit.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name="audit_logs")
public class AuditLog {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String actor; private String action; private String entityType; private String entityId;
 @Column(length=4000) private String oldValue; @Column(length=4000) private String newValue;
 private String correlationId; private String sourceService; private Instant createdAt;
 @PrePersist void onCreate(){createdAt=Instant.now();}
 public Long getId(){return id;} public String getActor(){return actor;} public void setActor(String v){actor=v;}
 public String getAction(){return action;} public void setAction(String v){action=v;} public String getEntityType(){return entityType;} public void setEntityType(String v){entityType=v;}
 public String getEntityId(){return entityId;} public void setEntityId(String v){entityId=v;} public String getOldValue(){return oldValue;} public void setOldValue(String v){oldValue=v;}
 public String getNewValue(){return newValue;} public void setNewValue(String v){newValue=v;} public String getCorrelationId(){return correlationId;} public void setCorrelationId(String v){correlationId=v;}
 public String getSourceService(){return sourceService;} public void setSourceService(String v){sourceService=v;} public Instant getCreatedAt(){return createdAt;}
}
