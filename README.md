# InsureFlow Cloud

InsureFlow Cloud is a cloud-native, microservices-based insurance backend inspired by Guidewire Cloud Platform patterns.

## Services

- `api-gateway` (8080): single entry point and route layer.
- `auth-service` (8081): registration and login.
- `customer-service` (8082): customer profile management.
- `policy-service` (8083): policy lifecycle management.
- `claims-service` (8084): claim filing and state transitions.
- `billing-service` (8085): invoice generation and idempotent payments.
- `notification-service` (8086): notification persistence and retrieval.
- `audit-service` (8087): audit trail capture and search.
- `common`: shared exception and correlation-id components.

## Architecture Notes

- Layered service design (`controller -> service -> repository`).
- DTO-based API boundaries.
- Shared `GlobalExceptionHandler` and `ErrorResponse` contract.
- Correlation header propagation with `X-Correlation-Id`.
- Billing payment endpoint uses `idempotencyKey` to prevent duplicate charges.

## Quick Start

### Build

```bash
mvn clean package
```

### Test

```bash
mvn test
```

### Run individually

```bash
mvn -pl auth-service spring-boot:run
mvn -pl customer-service spring-boot:run
mvn -pl policy-service spring-boot:run
mvn -pl claims-service spring-boot:run
mvn -pl billing-service spring-boot:run
mvn -pl notification-service spring-boot:run
mvn -pl audit-service spring-boot:run
mvn -pl api-gateway spring-boot:run
```

### Run via Docker Compose

```bash
docker compose up --build
```

## Core API Surface

### Auth
- `POST /api/auth/register`
- `POST /api/auth/login`

### Customers
- `POST /api/customers`
- `GET /api/customers/{id}`
- `PUT /api/customers/{id}`

### Policies
- `POST /api/policies`
- `GET /api/policies/{id}`
- `GET /api/policies/customer/{customerId}`
- `PUT /api/policies/{id}/activate`
- `PUT /api/policies/{id}/cancel`

### Claims
- `POST /api/claims`
- `GET /api/claims/{id}`
- `PUT /api/claims/{id}/review?adjuster=...`
- `PUT /api/claims/{id}/approve?approvedAmount=...`
- `PUT /api/claims/{id}/reject`
- `PUT /api/claims/{id}/settle`

### Billing
- `POST /api/invoices/generate`
- `POST /api/invoices/{invoiceId}/pay`
- `GET /api/invoices/customer/{customerId}`
- `GET /api/invoices/{invoiceId}`

### Notifications
- `POST /api/notifications`
- `GET /api/notifications/{id}`
- `GET /api/notifications/customer/{customerId}`

### Audit
- `POST /api/audits`
- `GET /api/audits/entity/{entityType}/{entityId}`
- `GET /api/audits/search?entityType=...&entityId=...`

## Kubernetes

Starter manifests are in `k8s/`:
- `configmap.yaml`
- `services.yaml`
- `deployments.yaml`

These use image placeholders intended for CI/CD replacement.

## Current Scope

This implementation provides a full end-to-end scaffold across all requested services with core domain APIs, baseline business-rule enforcement, and deployment artifacts (Docker/K8s) in one repository.
