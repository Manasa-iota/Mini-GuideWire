package com.insureflow.notification.controller;

import com.insureflow.notification.dto.NotificationRequest;
import com.insureflow.notification.dto.NotificationResponse;
import com.insureflow.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService service;
    public NotificationController(NotificationService service){this.service=service;}
    @PostMapping public ResponseEntity<NotificationResponse> create(@RequestBody NotificationRequest request){return ResponseEntity.ok(service.create(request));}
    @GetMapping("/{id}") public ResponseEntity<NotificationResponse> get(@PathVariable Long id){return ResponseEntity.ok(service.get(id));}
    @GetMapping("/customer/{customerId}") public ResponseEntity<List<NotificationResponse>> byCustomer(@PathVariable Long customerId){return ResponseEntity.ok(service.byCustomer(customerId));}
}
