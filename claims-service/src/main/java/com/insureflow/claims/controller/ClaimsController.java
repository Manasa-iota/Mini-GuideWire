package com.insureflow.claims.controller;

import com.insureflow.claims.dto.ClaimRequest;
import com.insureflow.claims.dto.ClaimResponse;
import com.insureflow.claims.service.ClaimsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/claims")
public class ClaimsController {
    private final ClaimsService service;
    public ClaimsController(ClaimsService service){this.service=service;}

    @PostMapping public ResponseEntity<ClaimResponse> file(@Valid @RequestBody ClaimRequest request){ return ResponseEntity.ok(service.file(request)); }
    @GetMapping("/{id}") public ResponseEntity<ClaimResponse> get(@PathVariable Long id){ return ResponseEntity.ok(service.get(id)); }
    @PutMapping("/{id}/review") public ResponseEntity<ClaimResponse> review(@PathVariable Long id, @RequestParam String adjuster){ return ResponseEntity.ok(service.review(id, adjuster)); }
    @PutMapping("/{id}/approve") public ResponseEntity<ClaimResponse> approve(@PathVariable Long id, @RequestParam BigDecimal approvedAmount){ return ResponseEntity.ok(service.approve(id, approvedAmount)); }
    @PutMapping("/{id}/reject") public ResponseEntity<ClaimResponse> reject(@PathVariable Long id){ return ResponseEntity.ok(service.reject(id)); }
    @PutMapping("/{id}/settle") public ResponseEntity<ClaimResponse> settle(@PathVariable Long id){ return ResponseEntity.ok(service.settle(id)); }
}
