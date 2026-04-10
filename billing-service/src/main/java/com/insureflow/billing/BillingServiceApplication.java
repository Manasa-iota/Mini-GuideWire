package com.insureflow.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.insureflow")
public class BillingServiceApplication {
    public static void main(String[] args) { SpringApplication.run(BillingServiceApplication.class, args); }
}
