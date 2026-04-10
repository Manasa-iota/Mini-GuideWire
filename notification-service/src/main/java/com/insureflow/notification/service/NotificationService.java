package com.insureflow.notification.service;

import com.insureflow.notification.dto.NotificationRequest;
import com.insureflow.notification.dto.NotificationResponse;

import java.util.List;

public interface NotificationService {
    NotificationResponse create(NotificationRequest request);
    NotificationResponse get(Long id);
    List<NotificationResponse> byCustomer(Long customerId);
}
