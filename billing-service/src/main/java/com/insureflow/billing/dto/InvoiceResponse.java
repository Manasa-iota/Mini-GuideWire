package com.insureflow.billing.dto;

import com.insureflow.billing.entity.InvoiceStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

public record InvoiceResponse(Long id, String invoiceNumber, Long customerId, Long policyId, BigDecimal amount,
                              LocalDate dueDate, LocalDate paidDate, InvoiceStatus status, String paymentMethod,
                              String transactionReference) {}
