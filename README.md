# InsureFlow Cloud (Phase 1 Scaffold)

This repository now contains a runnable **Phase 1** foundation for the InsureFlow Cloud backend:

- `common`: shared correlation-id filter and standardized exception model.
- `auth-service`: register/login with BCrypt-hashed credentials.
- `customer-service`: customer profile CRUD APIs.
- `policy-service`: policy creation and lifecycle basics (`PENDING_PAYMENT`, activation, cancellation).

## Run tests

```bash
mvn -q test
```

## Run a service (example: auth)

```bash
mvn -pl auth-service spring-boot:run
```

## Example APIs

- Auth: `POST /api/auth/register`, `POST /api/auth/login`
- Customer: `POST /api/customers`, `GET /api/customers/{id}`, `PUT /api/customers/{id}`
- Policy: `POST /api/policies`, `GET /api/policies/{id}`, `PUT /api/policies/{id}/activate`, `PUT /api/policies/{id}/cancel`

## Notes

This is the first implementation step after prompt cleanup. Remaining services (claims, billing, notification, audit, gateway, infra manifests) are next phases.
