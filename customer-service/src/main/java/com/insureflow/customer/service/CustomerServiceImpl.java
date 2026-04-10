package com.insureflow.customer.service;

import com.insureflow.common.exception.DomainException;
import com.insureflow.customer.dto.CustomerRequest;
import com.insureflow.customer.dto.CustomerResponse;
import com.insureflow.customer.entity.Customer;
import com.insureflow.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerResponse create(CustomerRequest request) {
        Customer customer = mapToEntity(new Customer(), request);
        return toResponse(repository.save(customer));
    }

    @Override
    public CustomerResponse findById(Long id) {
        return repository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new DomainException("CUSTOMER_NOT_FOUND", "Customer not found"));
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new DomainException("CUSTOMER_NOT_FOUND", "Customer not found"));
        return toResponse(repository.save(mapToEntity(customer, request)));
    }

    private Customer mapToEntity(Customer customer, CustomerRequest request) {
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setAddress(request.address());
        customer.setDateOfBirth(request.dateOfBirth());
        customer.setGovernmentId(request.governmentId());
        customer.setStatus(request.status());
        return customer;
    }

    private CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(),
                customer.getPhone(), customer.getAddress(), customer.getDateOfBirth(), customer.getGovernmentId(), customer.getStatus());
    }
}
