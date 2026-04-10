package com.insureflow.billing.service;

import com.insureflow.billing.dto.GenerateInvoiceRequest;
import com.insureflow.billing.dto.InvoiceResponse;
import com.insureflow.billing.dto.PaymentRequest;
import com.insureflow.billing.entity.Invoice;
import com.insureflow.billing.entity.InvoiceStatus;
import com.insureflow.billing.repository.InvoiceRepository;
import com.insureflow.common.exception.DomainException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BillingServiceImpl implements BillingService {
    private final InvoiceRepository repository;
    public BillingServiceImpl(InvoiceRepository repository){this.repository=repository;}

    public InvoiceResponse generate(GenerateInvoiceRequest request){
        Invoice i=new Invoice();
        i.setInvoiceNumber("INV-"+UUID.randomUUID().toString().substring(0,8).toUpperCase());
        i.setCustomerId(request.customerId()); i.setPolicyId(request.policyId()); i.setAmount(request.amount());
        i.setDueDate(request.dueDate()); i.setStatus(InvoiceStatus.PENDING);
        return map(repository.save(i));
    }

    public InvoiceResponse pay(Long invoiceId, PaymentRequest request){
        var existing = repository.findByIdempotencyKey(request.idempotencyKey());
        if (existing.isPresent()) return map(existing.get());
        Invoice i = repository.findById(invoiceId).orElseThrow(() -> new DomainException("INVOICE_NOT_FOUND", "Invoice not found"));
        i.setStatus(InvoiceStatus.PAID); i.setPaidDate(LocalDate.now()); i.setPaymentMethod(request.paymentMethod());
        i.setTransactionReference(request.transactionReference()); i.setIdempotencyKey(request.idempotencyKey());
        return map(repository.save(i));
    }

    public List<InvoiceResponse> customerInvoices(Long customerId){return repository.findByCustomerId(customerId).stream().map(this::map).toList();}
    public InvoiceResponse get(Long invoiceId){return map(repository.findById(invoiceId).orElseThrow(() -> new DomainException("INVOICE_NOT_FOUND", "Invoice not found")));}

    private InvoiceResponse map(Invoice i){return new InvoiceResponse(i.getId(), i.getInvoiceNumber(), i.getCustomerId(), i.getPolicyId(), i.getAmount(), i.getDueDate(), i.getPaidDate(), i.getStatus(), i.getPaymentMethod(), i.getTransactionReference());}
}
