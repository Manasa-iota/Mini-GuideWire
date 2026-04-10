package com.insureflow.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.insureflow")
public class AuditServiceApplication {
    public static void main(String[] args) { SpringApplication.run(AuditServiceApplication.class, args); }
}
