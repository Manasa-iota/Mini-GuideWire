# InsureFlow Cloud Phased Build Plan

## Phase 0: Foundation (Day 0)
1. Create mono-repo structure with Maven parent and service modules.
2. Add common libraries:
   - common-security (JWT utils, auth filter contracts)
   - common-observability (correlation-id filter/interceptor)
   - common-events (versioned event envelope)
   - common-exception (error model and handler contracts)
3. Add baseline tooling:
   - Checkstyle/Spotless (optional)
   - JUnit 5, Mockito, Testcontainers
   - shared Docker base image strategy

## Phase 1: Core Identity + Customer + Policy
1. Auth Service
   - register/login/me
   - BCrypt password hashing
   - JWT issuance/validation
2. Customer Service
   - profile CRUD + ownership checks
3. Policy Service
   - create policy as `PENDING_PAYMENT`
   - fetch/renew/cancel/update coverage
   - publish `PolicyCreatedEvent`
4. Tests
   - auth security and role tests
   - policy lifecycle baseline tests

## Phase 2: Billing + Activation
1. Billing Service
   - invoice generation on policy creation event
   - idempotent payment endpoint
   - publish payment success/failure events
2. Policy Service subscriber
   - on payment success activate policy
3. Tests
   - payment idempotency
   - invoice generation integration

## Phase 3: Claims + Workflow
1. Claims Service
   - claim filing with active policy validation
   - review/approve/reject/settle transitions
   - adjuster assignment and notes
2. Rules
   - threshold-driven manual review
   - invalid transitions blocked
3. Tests
   - active policy constraint
   - transition matrix tests

## Phase 4: Notification + Audit
1. Notification Service
   - consume domain events
   - channel factory (email/sms strategy)
2. Audit Service
   - consume key events + capture actor/context
   - searchable audit APIs
3. Tests
   - consumer contract tests
   - audit persistence tests

## Phase 5: Gateway + Platform Ops
1. API Gateway
   - route to services
   - JWT relay/validation
   - request logging + correlation propagation
2. Observability
   - actuator, health, readiness/liveness
   - metrics scaffolding
3. Config + resilience
   - profile-based configuration
   - retries/timeouts (where meaningful)

## Phase 6: Delivery Artifacts
1. Dockerfile per service
2. Docker Compose for local stack
3. Kubernetes manifests:
   - deployments/services/configmaps/secrets placeholders/ingress notes
4. API documentation and example calls
5. End-to-end README

## Suggested Milestone Order for Interviews
1. Demo auth and protected endpoint.
2. Create policy and show invoice auto-generated.
3. Pay invoice and show policy activation.
4. File claim and run approval flow.
5. Show event-driven notification and audit timeline.
