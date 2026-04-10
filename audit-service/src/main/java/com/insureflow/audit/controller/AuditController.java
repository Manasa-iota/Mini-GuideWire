package com.insureflow.audit.controller;

import com.insureflow.audit.dto.AuditRequest;
import com.insureflow.audit.dto.AuditResponse;
import com.insureflow.audit.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audits")
public class AuditController {
    private final AuditService service;
    public AuditController(AuditService service){this.service=service;}
    @PostMapping public ResponseEntity<AuditResponse> create(@RequestBody AuditRequest request){return ResponseEntity.ok(service.create(request));}
    @GetMapping("/entity/{entityType}/{entityId}") public ResponseEntity<List<AuditResponse>> byEntity(@PathVariable String entityType,@PathVariable String entityId){return ResponseEntity.ok(service.byEntity(entityType, entityId));}
    @GetMapping("/search") public ResponseEntity<List<AuditResponse>> search(@RequestParam String entityType,@RequestParam String entityId){return ResponseEntity.ok(service.byEntity(entityType, entityId));}
}
