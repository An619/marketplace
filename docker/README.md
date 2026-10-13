# Docker build and local infrastructure

This directory contains a shared multi-stage Dockerfile for Spring Boot services, a gateway Dockerfile, Docker Compose infrastructure, and local configuration examples.

## Build one service image

Run from the repository root so the build context includes the root Maven POM and all Maven modules:

    docker build -f docker/Dockerfile.service --build-arg SERVICE_MODULE=product-service -t marketplace-product-service:dev .

Replace product-service with a deployable module name. The shared Dockerfile builds that module and its Maven dependencies. The image uses Java 21 and runs as a non-root user.

## Start local infrastructure

From the repository root:

    cp docker/.env.example docker/.env
    # Edit docker/.env and replace development passwords before sharing the environment.
    docker compose --env-file docker/.env -f docker/docker-compose.yml config
    docker compose --env-file docker/.env -f docker/docker-compose.yml up -d
    docker compose --env-file docker/.env -f docker/docker-compose.yml ps

The Compose stack is for local development and must not be exposed as-is to the public internet. Some example credentials are intentionally simple placeholders. Do not use them in shared or production environments.

## Stop local infrastructure

    docker compose --env-file docker/.env -f docker/docker-compose.yml down

This preserves named volumes. To remove local persisted data as well, review the impact and then use the down --volumes option.

## Image publishing

The GitHub Actions workflow publishes service images to GHCR after relevant application-source changes on main, or when manually triggered. Jenkins can publish images when PUBLISH_IMAGES is explicitly enabled and the ghcr-push-token credential is configured.

For Kubernetes and Helm, see helm/README.md and docs/devops-deployment-guide.md.
