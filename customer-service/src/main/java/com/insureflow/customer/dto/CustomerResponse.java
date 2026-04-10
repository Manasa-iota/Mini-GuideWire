package com.insureflow.customer.dto;

import java.time.LocalDate;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        LocalDate dateOfBirth,
        String governmentId,
        String status
) {
}
