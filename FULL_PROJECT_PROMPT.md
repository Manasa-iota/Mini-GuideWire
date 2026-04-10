# Project Prompt: Build a Mini Guidewire Cloud Platform Inspired Insurance Backend

You are a senior backend architect and staff engineer. Design and generate a **production-style, cloud-ready backend system** inspired by **Guidewire Cloud Platform (GWCP)** and the type of engineering expected in the **Guidewire Cloud Common Services / Software Engineer Internship** role.

The goal is to build a **resume-worthy, enterprise-grade insurance platform** that demonstrates:

- strong **Java** and **Spring Boot** fundamentals
- clean **microservices architecture**
- intentional use of **design patterns**
- **cloud-readiness** (Docker/Kubernetes)
- strong **security**, **observability**, and **event-driven** design
- scalable backend engineering practices

This is not a toy CRUD app. It should feel like a real backend platform built by a disciplined engineering team.

---

## 1) Project Title

**InsureFlow Cloud: A Cloud-Native Insurance Policy, Claims, Billing, and Platform Services System**

## 2) Problem Statement

Build a cloud-native insurance backend platform where customers can:

- register and manage insurance policies
- submit and track claims
- pay premiums and view invoices
- receive important notifications
- securely access data based on role

The platform must include:

- policy lifecycle management
- claims workflow
- billing and payment tracking
- notifications
- authentication + authorization
- service-to-service communication
- event-driven messaging
- audit logging
- shared platform capabilities

## 3) Architecture Requirements

Implement as microservices:

1. API Gateway
2. Auth Service
3. Customer Service
4. Policy Service
5. Claims Service
6. Billing Service
7. Notification Service
8. Audit Service
9. Service Registry / Config support (optional if justified)
10. Shared common module(s)

Support:

- synchronous REST communication
- asynchronous messaging via Kafka or RabbitMQ
- centralized JWT auth
- standardized exception handling
- observability hooks
- Dockerized deployment + Kubernetes readiness

## 4) Required Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Web, Validation, Data JPA, Security
- Spring Cloud Gateway
- PostgreSQL
- Kafka or RabbitMQ
- Docker + Docker Compose
- Kubernetes manifests
- OpenAPI/Swagger
- JUnit 5 + Mockito
- Testcontainers (preferred)
- Maven

Optional:

- Spring Cloud Config / Eureka
- Micrometer + Prometheus
- OpenTelemetry/Zipkin scaffolding
- Redis caching
- MapStruct
- Lombok

## 5) Domain Requirements

### Customer Domain

Capabilities:
- register, login, view profile, update profile
- view own policies, claims, invoices

Fields:
- customerId, firstName, lastName, email, phone, address
- dateOfBirth, governmentId, status, createdAt, updatedAt

### Policy Domain

Capabilities:
- create, fetch by id, fetch by customer, renew, cancel, update coverage
- activate after payment, auto-expire by date

Fields:
- policyId, policyNumber, customerId, policyType
- coverageAmount, premiumAmount
- startDate, endDate, status, riskCategory, createdAt, updatedAt

Policy types:
- HEALTH, AUTO, HOME, LIFE

Policy statuses:
- DRAFT, PENDING_PAYMENT, ACTIVE, EXPIRED, CANCELLED, REJECTED

Rules:
- cannot activate before successful payment
- expired renewal after cutoff is rejected (must recreate)
- cancelled policy cannot accept new claims
- coverage changes must create audit entries

### Claims Domain

Capabilities:
- file claim, validate policy status, add notes, upload metadata
- assign adjuster, approve/reject, settle, fetch history

Fields:
- claimId, claimNumber, policyId, customerId, claimType
- incidentDate, claimAmount, approvedAmount
- description, status, assignedAdjuster, createdAt, updatedAt

Claim statuses:
- FILED, UNDER_REVIEW, INVESTIGATION, APPROVED, REJECTED, SETTLED, CLOSED

Rules:
- only ACTIVE policies may file claims
- threshold breaches require manual review
- REJECTED claims cannot settle
- APPROVED claims publish settlement/billing event
- every status transition must be auditable

### Billing Domain

Capabilities:
- generate invoice, pay invoice, fetch customer invoices
- apply overdue penalties, maintain payment history
- trigger policy activation on payment success

Fields:
- invoiceId, invoiceNumber, customerId, policyId
- amount, dueDate, paidDate, status
- paymentMethod, transactionReference, createdAt, updatedAt

Invoice statuses:
- GENERATED, PENDING, PAID, FAILED, OVERDUE, CANCELLED

Rules:
- invoice generated at policy creation
- successful payment triggers policy PENDING_PAYMENT -> ACTIVE
- overdue triggers notifications
- payment processing must be idempotent

### Notification Domain

Capabilities:
- email/SMS style notifications
- consume domain events
- notify for policy creation, payment success/overdue, claim filed, claim decision, policy expiry reminders

Fields:
- notificationId, recipient, channel, templateType, payload, status, createdAt, sentAt

### Audit Domain

Track:
- actor, actionType, entityType, entityId
- oldValue/newValue, timestamp, correlationId, sourceService

Must include:
- policy lifecycle changes
- claim state transitions
- payment outcomes
- role-sensitive admin/agent actions

## 6) Security and Roles

Use JWT-based stateless security with BCrypt password hashing.

Roles:
- CUSTOMER
- AGENT
- ADMIN

Access controls:
- CUSTOMER: own profile, own policies/claims/invoices, make payments, file claim on own policy
- AGENT: assigned claims and review transitions (no admin reports)
- ADMIN: customer management, claims decisions, invoice monitoring, audit access

Also require:
- method-level authorization where useful
- secure error responses
- no sensitive logs

## 7) Platform Capabilities

Implement shared platform patterns:

1. Correlation ID propagation across services
2. Standard error model (`status`, `errorCode`, `message`, `timestamp`, `path`, `correlationId`)
3. Observability via Actuator + health/readiness/liveness + metrics-ready structure
4. API Gateway as single entry point with route + JWT validation/token relay
5. Environment-based externalized config (dev/test/prod)
6. Resilience basics: retries/timeouts/circuit-breaker optional, payment idempotency required

## 8) Required Design Patterns

Use and explain where applied:

- Controller-Service-Repository
- DTO + Mapper
- Factory (notification channels or policy handlers)
- Strategy (premium, claim validation, payment method)
- Builder (event/response objects)
- Observer/Event-driven (Kafka/RabbitMQ)
- Adapter (external payment provider)
- Template Method (claim workflow if appropriate)
- Specification (optional filtering/search)
- Global Exception Handler
- Base Auditable Entity

Avoid:
- fat controllers
- business logic in controllers
- exposing entities directly in APIs
- duplicated validation logic
- hardcoded constants everywhere

## 9) Functional Flows (Must Implement)

1. **Registration/Login**: register -> secure credential storage -> login -> JWT issued -> protected access.
2. **Policy Creation**: admin/agent creates policy -> `PENDING_PAYMENT` -> billing invoice -> notification -> audit.
3. **Payment Activation**: customer pays invoice idempotently -> success event -> policy `ACTIVE` -> notification -> audit.
4. **Claim Filing**: file only for `ACTIVE` policy -> initial `FILED` state -> notify operations -> audit.
5. **Claim Review/Settlement**: agent review transitions -> admin approve/reject -> settlement flow -> notifications + audit.
6. **Policy Expiry Reminder**: scheduled expiry scan -> reminder notification -> optional audit.

## 10) Non-Functional Requirements

- clean package structure
- SOLID principles
- strong validation
- transaction boundaries
- pagination/filtering for list endpoints
- standardized responses
- high testability and extension readiness

## 11) API Requirements

Implement complete REST endpoints with request/response DTOs and validation, including at least:

- Auth: `POST /api/auth/register`, `POST /api/auth/login`, `GET /api/auth/me`
- Customer: `GET/PUT /api/customers/{id}`, plus related policies/claims views
- Policy: create/get/get-by-customer/renew/cancel/coverage update
- Claims: file/get/review/approve/reject/settle
- Billing: generate/pay/get-by-customer/get-by-id
- Audit: entity history + search
- Notification: get-by-id/get-by-customer

## 12) Database Requirements

- each service owns schema/tables
- normalized design
- JPA entities + migrations + indexes
- include canonical tables for users/customers/policies/claims/invoices/notifications/audit_logs

## 13) Event-Driven Messaging Requirements

Include events such as:
- PolicyCreatedEvent
- PaymentCompletedEvent / PaymentFailedEvent
- ClaimFiledEvent / ClaimApprovedEvent / ClaimRejectedEvent
- PolicyExpiringEvent

Event contract requirements:
- structured, versionable payloads
- metadata includes timestamp, correlationId, sourceService
- notification consumes relevant events
- audit consumes all important events

## 14) Testing Requirements

Include:
- service unit tests
- controller tests
- repository tests
- role/security tests
- integration tests for critical flows
- producer/consumer tests where reasonable

Critical tests:
- policy creation
- payment idempotency
- claim only on active policy
- claim transition rules
- unauthorized access denied
- JWT-protected endpoints
- invoice generation after policy creation

## 15) DevOps and Deployment Requirements

Generate:
- Dockerfile per service
- docker-compose for local stack
- Kubernetes manifests (deployments, services, config maps, secret placeholders, ingress/gateway notes)
- environment profiles for local/test/prod

README must include:
- local run
- docker compose run
- testing instructions
- API usage examples
- default demo users/roles

## 16) Project Structure Expectations

Each service should include:
- controller
- service
- repository
- dto
- entity
- mapper
- config
- security
- exception
- event
- util

Also include:
- shared common module (if useful)
- API docs setup
- sample Postman collection or curl examples

## 17) Code Quality Expectations

- complete, runnable code
- no pseudo-code
- constructor injection
- clear naming and maintainability
- comments only where helpful
- Spring Boot best practices

## 18) Resume Alignment Goal

The implementation should support resume bullets like:

- Built a cloud-native insurance platform inspired by Guidewire Cloud Platform using Java, Spring Boot, PostgreSQL, Docker, API Gateway, and event-driven microservices.
- Implemented secure policy, claims, and billing workflows with JWT-based role access, audit logging, and asynchronous notifications.
- Designed scalable backend services with clean architecture, design patterns, observability hooks, idempotent payment processing, and Kubernetes-ready deployment artifacts.

## 19) Delivery Phases

### Phase 1
- architecture and module breakdown
- ER diagram description
- text service interaction diagram
- package structure

### Phase 2
- Auth Service
- Customer Service
- Policy Service

### Phase 3
- Claims Service
- Billing Service
- Notification Service
- Audit Service

### Phase 4
- API Gateway
- Dockerfiles + docker-compose
- Kubernetes manifests
- README
- test suite

Do not skip the design explanation before code generation.

## 20) Final Constraints

- Java + Spring Boot only (not Node.js)
- keep realistic and interview-explainable
- enforce business rules explicitly in code
- every service has a clear responsibility
- prioritize correctness, clarity, and maintainability
