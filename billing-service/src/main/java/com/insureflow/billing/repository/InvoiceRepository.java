package com.insureflow.billing.repository;

import com.insureflow.billing.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByCustomerId(Long customerId);
    Optional<Invoice> findByIdempotencyKey(String idempotencyKey);
}
