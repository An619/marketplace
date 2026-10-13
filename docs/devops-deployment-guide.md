# DevOps deployment guide

This repository contains the application and starter CI/CD/GitOps configuration. The presence of configuration files does not mean that a pipeline or production deployment has already succeeded; validate each phase in a disposable lab first.

## Delivery flow

1. A pull request runs Maven formatting and test/build checks through GitHub Actions.
2. Jenkins can run formatting, tests, SonarQube analysis and a configured quality gate.
3. The image-publishing GitHub Actions workflow builds 16 service images from the shared Dockerfile and publishes them to GHCR on pushes to main.
4. Helm templates render Deployments and ClusterIP Services for the gateway and enabled business services.
5. Argo CD reconciles the Helm chart from this repository into the marketplace namespace.

## Jenkins prerequisites

Configure Jenkins tools with the exact names jdk-21 and maven-3.9. Configure a SonarQube server named SonarQube and install the Pipeline, Docker Pipeline, JUnit and JaCoCo plugins required by the Jenkinsfile. Create a username/password credential with ID ghcr-push-token for GHCR publishing. Keep PUBLISH_IMAGES disabled until the build, registry permissions and image names have been tested.

The Jenkins pipeline's image-publish stage builds all service images and can consume significant agent disk, memory and time. Enable it deliberately.

## GitHub Container Registry

The publishing workflow uses the repository's GITHUB_TOKEN and publishes images under ghcr.io/an619. After its first successful run, inspect the Packages page, confirm the image names and visibility, and configure Kubernetes imagePullSecrets if packages are private. The Helm values use the matching marketplace-<service> image naming convention.

## Helm validation

Install Helm 3 and run from the repository root:

```bash
helm lint ./helm
helm template marketplace ./helm --namespace marketplace -f ./helm/values-dev.yaml
```

Do not sync Argo CD until the rendered image names exist and all required application configuration is supplied.

## Argo CD

Install Argo CD in the target cluster first. Review argocd/marketplace-application.yaml, then apply it to the cluster where Argo CD is installed. Its automated sync policy can prune resources and self-heal drift; understand those effects before using it outside a disposable environment.

## Secrets and external dependencies

The chart can import an existing Secret named by global.existingSecret. The development values file uses marketplace-runtime-secrets as an example. Create the Secret through a secure mechanism or external secrets operator. Never commit actual credentials.

The Helm chart does not provision PostgreSQL, Kafka, Redis, Elasticsearch, Keycloak, Ollama, ingress/TLS, persistent storage, or cloud infrastructure. Configure those dependencies separately and use service-specific database credentials and least privilege.

## EKS and cost control

Do not create an EKS cluster until local build/tests, container publishing, Helm rendering, and a basic Kubernetes deployment work. Before provisioning, estimate charges for EKS, EC2 nodes, load balancers, EBS, NAT gateways, logs and data transfer. After practice, delete resources and verify that billable resources are gone.

## Production-readiness checklist

- [ ] Maven build and unit/integration tests pass
- [ ] SonarQube quality gate is configured and enforced
- [ ] Container scans are triaged and blocking thresholds are defined
- [ ] All images are published with immutable tags and provenance
- [ ] Helm templates render successfully and are reviewed
- [ ] Secrets are managed outside Git
- [ ] Probes match each service's actual health endpoints
- [ ] External dependencies, persistence, backups and recovery are tested
- [ ] Ingress/TLS, RBAC, network policies and resource limits are reviewed
- [ ] Argo CD sync, drift correction and rollback are demonstrated
- [ ] Monitoring, alerting and incident runbooks are exercised
