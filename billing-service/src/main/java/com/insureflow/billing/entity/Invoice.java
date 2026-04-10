package com.insureflow.billing.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity @Table(name="invoices")
public class Invoice {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false, unique=true) private String invoiceNumber;
 private Long customerId; private Long policyId; private BigDecimal amount; private LocalDate dueDate; private LocalDate paidDate;
 @Enumerated(EnumType.STRING) private InvoiceStatus status; private String paymentMethod; private String transactionReference;
 @Column(unique=true) private String idempotencyKey;
 public Long getId(){return id;} public String getInvoiceNumber(){return invoiceNumber;} public void setInvoiceNumber(String v){invoiceNumber=v;}
 public Long getCustomerId(){return customerId;} public void setCustomerId(Long v){customerId=v;} public Long getPolicyId(){return policyId;} public void setPolicyId(Long v){policyId=v;}
 public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){amount=v;} public LocalDate getDueDate(){return dueDate;} public void setDueDate(LocalDate v){dueDate=v;}
 public LocalDate getPaidDate(){return paidDate;} public void setPaidDate(LocalDate v){paidDate=v;} public InvoiceStatus getStatus(){return status;} public void setStatus(InvoiceStatus v){status=v;}
 public String getPaymentMethod(){return paymentMethod;} public void setPaymentMethod(String v){paymentMethod=v;} public String getTransactionReference(){return transactionReference;} public void setTransactionReference(String v){transactionReference=v;}
 public String getIdempotencyKey(){return idempotencyKey;} public void setIdempotencyKey(String v){idempotencyKey=v;}
}
