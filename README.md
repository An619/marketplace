# Enterprise Marketplace Platform

![CI](https://github.com/An619/marketplace/actions/workflows/ci.yml/badge.svg?branch=main) ![Deployment validation](https://github.com/An619/marketplace/actions/workflows/validate-infrastructure.yml/badge.svg?branch=main)

An enterprise-style B2B marketplace reference platform built with Java 21, Spring Boot 3.5.x, and a Maven multi-module architecture. The repository is intended for application engineering and DevOps portfolio practice. Production readiness must be established through testing, security review, load testing, and environment-specific validation.

## Overview

The repository contains a shared library, API gateway, identity module, domain services, local infrastructure definitions, and deployment automation. It is organized as a monorepo to make cross-service builds and coordinated releases easier to practice.

Core domain modules include product, seller, buyer, category, inventory, and pricing APIs. Workflow, notification, audit, subscription, report, admin, search, and AI modules are also included. The presence of a module or deployment file does not by itself mean that the service is production-ready.

## Technology stack

| Layer | Technology |
|---|---|
| Runtime | Java 21 |
| Framework | Spring Boot 3.5.x, Spring Cloud 2025.0.x |
| Security | Spring Security, Keycloak (OIDC/JWT) |
| Gateway | Spring Cloud Gateway |
| Database | PostgreSQL |
| Cache | Redis |
| Messaging | Apache Kafka (KRaft) |
| Search | Elasticsearch |
| AI | Ollama |
| Observability | Prometheus, Grafana, ELK, OpenTelemetry, Jaeger |
| Migrations | Flyway |
| Build | Maven |
| Quality | Checkstyle, Spotless, SonarQube, JaCoCo |
| Testing | JUnit 5, Mockito, Testcontainers |

## Modules

| Module | Default port | Responsibility |
|---|---:|---|
| `common-library` | — | Shared cross-cutting concerns |
| `gateway-service` | 8080 | API gateway and routing |
| `identity-service` | 8081 | Identity and authentication-related APIs |
| `product-service` | 8082 | Product catalog |
| `seller-service` | 8083 | Seller management |
| `buyer-service` | 8084 | Buyer management |
| `category-service` | 8085 | Category taxonomy |
| `inventory-service` | 8086 | Inventory and reservation APIs |
| `pricing-service` | 8087 | Pricing APIs |
| `workflow-service` | 8088 | Business workflow module |
| `notification-service` | 8089 | Notification module |
| `search-service` | 8090 | Product search integration |
| `ai-service` | 8091 | AI integration |
| `audit-service` | 8092 | Audit trail module |
| `subscription-service` | 8093 | Subscription and plan module |
| `report-service` | 8094 | Reporting and analytics module |
| `admin-service` | 8095 | Platform administration module |

These are intended/default ports; check each module's configuration before running services together.

## Prerequisites

- JDK 21
- Maven 3.9+
- Docker Engine or Docker Desktop (for local dependencies)
- Git
- Node.js and npm (only for the test frontend)

## Quick start

Run commands from the repository root unless the step says otherwise.

### 1. Configure and start local infrastructure

```bash
cp docker/.env.example docker/.env
# Edit docker/.env and replace example credentials for your local environment.
docker compose --env-file docker/.env -f docker/docker-compose.yml config
docker compose --env-file docker/.env -f docker/docker-compose.yml up -d
docker compose --env-file docker/.env -f docker/docker-compose.yml ps
```

The Compose stack is for local development. Do not expose it to the public internet or reuse its example credentials in shared or production environments.

### 2. Build and test the Java modules

```bash
mvn -B spotless:check
mvn -B clean verify -Pcoverage
```

### 3. Run a service

For example, to start the gateway from the repository root:

```bash
mvn -pl gateway-service spring-boot:run
```

Configure its required dependencies and environment variables before starting other services. Check the service-specific documentation and application configuration.

### 4. Run the test frontend

```bash
cd frontend
npm install
npm run dev
```

Open http://localhost:5173. See [End-to-End Testing Guide](docs/end-to-end-testing-guide.md).

### 5. Verify health endpoints

Examples (only available when the relevant service and dependencies are configured):

```text
GET http://localhost:8080/actuator/health
GET http://localhost:8081/api/v1/infrastructure/health
GET http://localhost:8090/api/v1/infrastructure/health/elasticsearch
GET http://localhost:8091/api/v1/infrastructure/health/ollama
```

Import the Postman collection at `docs/postman/Enterprise-Marketplace-Infrastructure.postman_collection.json` where available.

## Project structure and documentation

- [Architecture](docs/architecture.md)
- [Product Service](docs/product-service.md)
- [Domain Services](docs/domain-services.md)
- [Coding Standards](docs/coding-standards.md)
- [Folder Structure](docs/folder-structure.md)
- [Logging Standard](docs/logging-standard.md)
- [Branching Strategy](docs/branching-strategy.md)
- [Docker and local infrastructure](docker/README.md)
- [End-to-end testing guide](docs/end-to-end-testing-guide.md)

## Configuration profiles

| Profile | Purpose |
|---|---|
| `local` | Local development |
| `dev` | Development environment |
| `qa` | QA environment |
| `prod` | Production-oriented configuration; requires environment-specific review |

## DevOps practice and deployment

The repository includes starter automation and infrastructure configuration for hands-on practice. These workflows and files must be validated in the target environment; their presence does not imply a successful production deployment.

- [Jenkins pipeline](Jenkinsfile) — Maven formatting and tests, SonarQube analysis and quality gate, optional GHCR publishing.
- [GitHub Actions CI](.github/workflows/ci.yml) — Maven formatting, build, tests, and coverage artifact.
- [Container publishing workflow](.github/workflows/publish-images.yml) — validates source before attempting to publish the 16 deployable service images to GitHub Container Registry.
- [Repository security scan](.github/workflows/security-scan.yml) — Trivy filesystem, dependency, secret, and configuration scanning. Review findings; the current scan is report-only.
- [Infrastructure validation](.github/workflows/validate-infrastructure.yml) — Helm lint/render and Terraform validation without provisioning AWS resources.
- [Helm chart](helm/README.md) — Kubernetes Deployments and Services for the gateway and application modules.
- [Argo CD Application](argocd/marketplace-application.yaml) — GitOps synchronization of the Helm chart.
- [Kubernetes guide](kubernetes/README.md) — rendering and development deployment instructions.
- [Terraform EKS guide](terraform/README.md) — starter AWS VPC/EKS infrastructure and cleanup guidance.
- [DevOps deployment guide](docs/devops-deployment-guide.md) — CI/CD setup, registry permissions, prerequisites, and readiness checklist.
- [Jenkins and Argo CD setup](docs/jenkins-argocd-setup.md) — Jenkins tools, credentials, GHCR publishing, and GitOps deployment steps.

## Operational boundaries

- PostgreSQL, Kafka, Redis, Elasticsearch, Keycloak, and Ollama dependencies must be configured for the services that use them. The Helm chart does not provision all of these dependencies.
- Keep credentials out of Git. Inject secrets through an approved secret manager or Kubernetes Secrets created outside source control.
- Confirm GHCR package visibility and image-pull permissions before deploying to a cluster.
- Review resource sizing, ingress/TLS, access control, monitoring, backup/recovery, and rollback before any production use.
- Terraform validation does not create infrastructure. EKS, EC2 nodes, NAT gateways, load balancers, storage, and data transfer can incur charges. Do not run `terraform apply` until you have reviewed the plan and cost.

## License

MIT — see [LICENSE](LICENSE).
