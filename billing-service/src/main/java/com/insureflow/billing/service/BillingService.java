package com.insureflow.billing.service;

import com.insureflow.billing.dto.GenerateInvoiceRequest;
import com.insureflow.billing.dto.InvoiceResponse;
import com.insureflow.billing.dto.PaymentRequest;

import java.util.List;

public interface BillingService {
    InvoiceResponse generate(GenerateInvoiceRequest request);
    InvoiceResponse pay(Long invoiceId, PaymentRequest request);
    List<InvoiceResponse> customerInvoices(Long customerId);
    InvoiceResponse get(Long invoiceId);
}
