package com.insureflow.notification.service;

import com.insureflow.common.exception.DomainException;
import com.insureflow.notification.dto.NotificationRequest;
import com.insureflow.notification.dto.NotificationResponse;
import com.insureflow.notification.entity.Notification;
import com.insureflow.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository repository;
    public NotificationServiceImpl(NotificationRepository repository){this.repository=repository;}

    public NotificationResponse create(NotificationRequest request){
        Notification n=new Notification();
        n.setCustomerId(request.customerId()); n.setRecipient(request.recipient()); n.setChannel(request.channel());
        n.setTemplateType(request.templateType()); n.setPayload(request.payload()); n.setStatus("SENT"); n.setSentAt(Instant.now());
        return map(repository.save(n));
    }
    public NotificationResponse get(Long id){return map(repository.findById(id).orElseThrow(() -> new DomainException("NOTIFICATION_NOT_FOUND","Notification not found")));}
    public List<NotificationResponse> byCustomer(Long customerId){return repository.findByCustomerId(customerId).stream().map(this::map).toList();}
    private NotificationResponse map(Notification n){return new NotificationResponse(n.getId(), n.getCustomerId(), n.getRecipient(), n.getChannel(), n.getTemplateType(), n.getPayload(), n.getStatus(), n.getCreatedAt(), n.getSentAt());}
}
