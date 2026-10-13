# Enterprise Marketplace Platform

Enterprise-style B2B marketplace backend platform (IndiaMART-like) built with Java 21, Spring Boot 3.5.x, and a cloud-native microservices architecture. This repository is intended for hands-on application engineering and DevOps portfolio practice; production readiness must be established through testing, security review, and environment-specific validation.

## Overview

This repository contains the **Enterprise Marketplace Platform** — a Maven multi-module monorepo with shared libraries, API gateway, domain microservice scaffolds, and a complete **Infrastructure Foundation** (Docker Compose, Keycloak, Kafka, Redis, Elasticsearch, observability stack).

Core B2B domain services include CRUD APIs for products, sellers, buyers, categories, inventory, and pricing. Workflow, notification, audit, subscription, report, admin, search, AI, and identity modules are present; their operational readiness should be judged by the current CI/test results and successful environment-specific deployment, not by source presence alone.

## Technology Stack

| Layer | Technology |
|-------|------------|
| Runtime | Java 21 |
| Framework | Spring Boot 3.5.x, Spring Cloud 2025.0.x |
| Security | Spring Security, Keycloak (OIDC/JWT) |
| Gateway | Spring Cloud Gateway |
| Database | Neon PostgreSQL (per service) |
| Cache | Redis |
| Messaging | Apache Kafka (KRaft) |
| Search | Elasticsearch |
| AI | Ollama |
| Observability | Prometheus, Grafana, ELK, OpenTelemetry, Jaeger |
| Migration | Flyway |
| Build | Maven |
| Quality | Checkstyle, Spotless, SonarQube, JaCoCo |
| Testing | JUnit 5, Mockito, Testcontainers |

## Modules

| Module | Port | Description |
|--------|------|-------------|
| `common-library` | — | Shared cross-cutting concerns |
| `gateway-service` | 8080 | API Gateway (JWT, routing, tracing) |
| `identity-service` | 8081 | Identity, Redis, Kafka, Neon DB |
| `product-service` | 8082 | Product catalog (CRUD) |
| `seller-service` | 8083 | Seller management (CRUD) |
| `buyer-service` | 8084 | Buyer management (CRUD) |
| `category-service` | 8085 | Category taxonomy (CRUD) |
| `inventory-service` | 8086 | Inventory management (CRUD + reserve/release) |
| `pricing-service` | 8087 | Pricing engine (CRUD) |
| `workflow-service` | 8088 | Business workflow lifecycle (production-ready) |
| `notification-service` | 8089 | Multi-channel notifications (production-ready) |
| `search-service` | 8090 | Elasticsearch product search (production-ready) |
| `ai-service` | 8091 | Ollama AI chat, descriptions, search interpret (production-ready) |
| `audit-service` | 8092 | Central audit trail (production-ready) |
| `subscription-service` | 8093 | Subscriptions and billing plans (production-ready) |
| `report-service` | 8094 | Report generation and analytics (production-ready) |
| `admin-service` | 8095 | Platform administration (production-ready) |

## Prerequisites

- JDK 21
- Maven 3.9+
- Docker Desktop (for local infrastructure)
- Git

## Quick Start

### 1. Start Infrastructure

```bash
cd docker
cp .env.example .env
docker compose --env-file .env up -d
```

### 2. Build Platform

```bash
mvn clean install
```

### 3. Run a Service

```powershell
cd gateway-service
mvn spring-boot:run
```

Repeat for `identity-service`, `search-service`, and `ai-service` as needed.

### 5. Run the Test Frontend

```powershell
cd frontend
npm install
npm run dev
```

Open **http://localhost:5173** for the end-to-end test console. See [End-to-End Testing Guide](docs/end-to-end-testing-guide.md).

### 4. Verify Health

```
GET http://localhost:8080/actuator/health
GET http://localhost:8081/api/v1/infrastructure/health
GET http://localhost:8090/api/v1/infrastructure/health/elasticsearch
GET http://localhost:8091/api/v1/infrastructure/health/ollama
```

Import the Postman collection from `docs/postman/Enterprise-Marketplace-Infrastructure.postman_collection.json`.

## Project Structure

See [docs/folder-structure.md](docs/folder-structure.md) for the complete layout.

## Documentation

- [Architecture](docs/architecture.md)
- [Product Service](docs/product-service.md)
- [Domain Services](docs/domain-services.md)
- [Coding Standards](docs/coding-standards.md)
- [Folder Structure](docs/folder-structure.md)
- [Logging Standard](docs/logging-standard.md)
- [Branching Strategy](docs/branching-strategy.md)
- [Docker README](docker/README.md)

## Configuration Profiles

| Profile | Purpose |
|---------|---------|
| `local` | Local development (default) |
| `dev` | Development environment |
| `qa` | QA environment |
| `prod` | Production environment |

## License

MIT — see [LICENSE](LICENSE).


## DevOps practice and deployment

This repository includes starter automation and infrastructure configuration for hands-on DevOps practice. These files still require validation in your environment; their presence does not imply a successful production deployment.

- [Jenkins pipeline](Jenkinsfile) — Maven formatting/tests, SonarQube analysis and quality gate, optional GHCR publishing.
- [GitHub Actions CI](.github/workflows/ci.yml) — Maven checks and test reports.
- [Container publishing workflow](.github/workflows/publish-images.yml) — builds the 16 deployable services and publishes images to GitHub Container Registry.
- [Repository security scan](.github/workflows/security-scan.yml) — Trivy filesystem/dependency/configuration scan.
- [Infrastructure validation](.github/workflows/validate-infrastructure.yml) — Helm lint/render and Terraform validation.
- [Helm chart](helm/README.md) — Kubernetes Deployments and Services for the gateway and application services.
- [Argo CD Application](argocd/marketplace-application.yaml) — GitOps synchronization of the Helm chart.
- [Kubernetes guide](kubernetes/README.md) — rendering and development deployment instructions.
- [Terraform EKS guide](terraform/README.md) — starter AWS VPC/EKS infrastructure and cleanup guidance.
- [DevOps deployment guide](docs/devops-deployment-guide.md) — CI/CD setup, registry permissions, prerequisites and production-readiness checklist.\n- [Jenkins and Argo CD setup](docs/jenkins-argocd-setup.md) — Jenkins tools, credentials, GHCR publishing and GitOps deployment steps.

### Important

The application requires external infrastructure such as PostgreSQL, Kafka, Redis, Elasticsearch, Keycloak and Ollama. Helm does not provision these dependencies. Configure secrets outside Git, verify GHCR image visibility and health probes, and run validation before syncing Argo CD or creating AWS resources.
