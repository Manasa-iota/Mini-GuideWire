package com.insureflow.billing.controller;

import com.insureflow.billing.dto.GenerateInvoiceRequest;
import com.insureflow.billing.dto.InvoiceResponse;
import com.insureflow.billing.dto.PaymentRequest;
import com.insureflow.billing.service.BillingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class BillingController {
    private final BillingService service;
    public BillingController(BillingService service){this.service=service;}
    @PostMapping("/generate") public ResponseEntity<InvoiceResponse> generate(@Valid @RequestBody GenerateInvoiceRequest request){return ResponseEntity.ok(service.generate(request));}
    @PostMapping("/{invoiceId}/pay") public ResponseEntity<InvoiceResponse> pay(@PathVariable Long invoiceId,@Valid @RequestBody PaymentRequest request){return ResponseEntity.ok(service.pay(invoiceId, request));}
    @GetMapping("/customer/{customerId}") public ResponseEntity<List<InvoiceResponse>> byCustomer(@PathVariable Long customerId){return ResponseEntity.ok(service.customerInvoices(customerId));}
    @GetMapping("/{invoiceId}") public ResponseEntity<InvoiceResponse> get(@PathVariable Long invoiceId){return ResponseEntity.ok(service.get(invoiceId));}
}
