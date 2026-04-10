package com.insureflow.notification.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name="notifications")
public class Notification {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long customerId; private String recipient; private String channel; private String templateType;
 @Column(length=3000) private String payload; private String status; private Instant createdAt; private Instant sentAt;
 @PrePersist void onCreate(){createdAt=Instant.now();}
 public Long getId(){return id;} public Long getCustomerId(){return customerId;} public void setCustomerId(Long v){customerId=v;}
 public String getRecipient(){return recipient;} public void setRecipient(String v){recipient=v;} public String getChannel(){return channel;} public void setChannel(String v){channel=v;}
 public String getTemplateType(){return templateType;} public void setTemplateType(String v){templateType=v;} public String getPayload(){return payload;} public void setPayload(String v){payload=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;} public Instant getCreatedAt(){return createdAt;} public Instant getSentAt(){return sentAt;} public void setSentAt(Instant v){sentAt=v;}
}
