# Kubernetes deployment

The namespace manifest lives in kubernetes/namespace.yaml. The Helm chart in helm/ is the source of truth for application Deployments and Services.

## Prerequisites

- A working Kubernetes cluster and configured kubectl context
- Helm 3
- Container images published to the registry configured in helm/values.yaml
- Application configuration and credentials provided through a Kubernetes Secret or external secret manager
- Required PostgreSQL, Kafka, Redis, Elasticsearch, Keycloak and Ollama dependencies

## Render before applying

Run from the repository root:

```bash
helm lint ./helm
helm template marketplace ./helm -n marketplace -f ./helm/values-dev.yaml
```

Review image names, probes, ports, resource limits and environment variables before deployment.

## Deploy manually in a development lab

```bash
kubectl apply -f ./kubernetes/namespace.yaml
helm upgrade --install marketplace ./helm \
  --namespace marketplace \
  --create-namespace \
  --values ./helm/values-dev.yaml
kubectl get deployments,services,pods -n marketplace
```

Check actual deployment names with kubectl before running rollout-status commands.

## Argo CD

After installing Argo CD and publishing the required images, review and apply argocd/marketplace-application.yaml. Argo CD reconciles the Helm chart from the main branch.

## Limitations to validate

- The chart deploys application services; it does not install PostgreSQL, Kafka, Redis, Elasticsearch, Keycloak or Ollama.
- The probes expect Spring Boot Actuator health endpoints to be enabled and reachable.
- marketplace-runtime-secrets is an example Secret name, not a Secret created by this repository. Never commit real credentials.
- Do not use the production values file until image tags, capacity, network exposure, persistence, TLS, secret management, backups and recovery have been tested.
