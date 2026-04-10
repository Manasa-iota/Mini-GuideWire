package com.insureflow.customer.service;

import com.insureflow.customer.dto.CustomerRequest;
import com.insureflow.customer.dto.CustomerResponse;

public interface CustomerService {
    CustomerResponse create(CustomerRequest request);
    CustomerResponse findById(Long id);
    CustomerResponse update(Long id, CustomerRequest request);
}
