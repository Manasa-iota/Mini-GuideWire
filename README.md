# InsureFlow Cloud

InsureFlow Cloud is a cloud-native insurance backend platform inspired by enterprise systems such as Guidewire Cloud Platform. It demonstrates production-style backend engineering using Java, Spring Boot, microservices, secure APIs, and event-driven architecture.

## Overview

The platform models core insurance operations:

- Policy management
- Claims processing
- Billing and payments
- Notifications
- Audit logging

It is designed to reflect real-world enterprise backend systems with scalability, security, and maintainability.

---

## Architecture

The system follows a microservices architecture:

- API Gateway
- Auth Service
- Customer Service
- Policy Service
- Claims Service
- Billing Service
- Notification Service
- Audit Service

### Communication

- REST APIs for synchronous communication
- Kafka or RabbitMQ for asynchronous event-driven communication

---

## Features

### Authentication & Authorization
- User registration and login
- JWT-based authentication
- Role-based access control (CUSTOMER, AGENT, ADMIN)

### Policy Management
- Create and manage insurance policies
- Policy lifecycle (PENDING_PAYMENT → ACTIVE → EXPIRED)
- Policy renewal and cancellation

### Claims Management
- File claims on active policies
- Claim validation and processing
- Claim approval and settlement workflows

### Billing
- Invoice generation for policies
- Payment tracking
- Idempotent payment handling
- Policy activation after payment success

### Notifications
- Policy creation alerts
- Payment confirmations
- Claim updates
- Expiry reminders

### Audit Logging
- Tracks all critical system actions
- Stores entity changes, actor, timestamps, and correlation IDs

---

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Spring Cloud Gateway
- PostgreSQL
- Kafka / RabbitMQ
- Docker
- Kubernetes (deployment-ready)
- Maven
- JUnit, Mockito
- Swagger (OpenAPI)

---

## Design Principles

- Clean architecture (Controller-Service-Repository)
- Microservices separation
- DTO-based APIs
- Stateless authentication
- Event-driven design
- Externalized configuration

---

## Design Patterns Used

- Strategy Pattern (business logic variations)
- Factory Pattern (notification creation)
- Builder Pattern (event construction)
- Adapter Pattern (external service abstraction)
- Observer Pattern (event-driven messaging)
- Global Exception Handling

---

## Core Workflows

### Policy Creation
1. Policy created with `PENDING_PAYMENT`
2. Invoice generated
3. Notification sent
4. Audit log created

### Payment Flow
1. User pays invoice
2. Payment validated (idempotent)
3. Policy becomes `ACTIVE`
4. Event triggers notifications and audit

### Claim Flow
1. Claim filed on active policy
2. Claim reviewed by agent
3. Approved or rejected by admin
4. Settlement processed if approved

---

## API Endpoints

### Auth
- POST /api/auth/register
- POST /api/auth/login

### Policy
- POST /api/policies
- GET /api/policies/{id}
- PUT /api/policies/{id}/renew
- PUT /api/policies/{id}/cancel

### Claims
- POST /api/claims
- GET /api/claims/{id}
- PUT /api/claims/{id}/approve

### Billing
- POST /api/invoices/generate
- POST /api/invoices/{id}/pay

---

## Project Structure
```

insureflow-cloud/
├── api-gateway/
├── auth-service/
├── customer-service/
├── policy-service/
├── claims-service/
├── billing-service/
├── notification-service/
├── audit-service/
├── docker-compose.yml
└── k8s/
```
---

## Running Locally

### Prerequisites

- Java 17
- Maven
- Docker
- Docker Compose

### Steps

git clone <repo-url>
cd insureflow-cloud
mvn clean install
docker-compose up --build

---

## Environment Variables
```
DB_URL=jdbc:postgresql://localhost:5432/insureflow
DB_USERNAME=postgres
DB_PASSWORD=postgres
JWT_SECRET=your-secret-key
KAFKA_BOOTSTRAP_SERVERS=localhost:9092
```
---

## Testing

Run all tests:

mvn test

---

## Future Improvements

- Service discovery (Eureka)
- Config server
- Distributed tracing
- Centralized logging
- Payment gateway integration
- Kubernetes autoscaling

---
