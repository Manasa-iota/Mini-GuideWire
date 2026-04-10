package com.insureflow.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.insureflow")
public class NotificationServiceApplication {
    public static void main(String[] args) { SpringApplication.run(NotificationServiceApplication.class, args); }
}
