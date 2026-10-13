# Jenkins, SonarQube and Argo CD setup

This guide connects the repository's Jenkins pipeline and Helm chart to a Kubernetes environment. It does not create cloud infrastructure or store credentials in Git.

## 1. Jenkins prerequisites

Install/configure these Jenkins plugins:

- Pipeline
- Git and GitHub integration
- JUnit
- JaCoCo
- SonarQube Scanner
- Docker Pipeline (only if Jenkins will publish images)

Configure **Manage Jenkins → Tools** with the exact names referenced in `Jenkinsfile`:

| Tool | Name in Jenkins | Version |
|---|---|---|
| JDK | `jdk-21` | JDK 21 |
| Maven | `maven-3.9` | Maven 3.9.x |

Configure **Manage Jenkins → System → SonarQube servers** with the server name `SonarQube` and a server URL. Store its token as a Jenkins-managed credential; never put the token in the repository.

## 2. Credentials and pipeline

If using the optional image-publishing stage, add a Jenkins username/password credential with ID `ghcr-push-token`. Use your GitHub username as the username and a fine-grained token with package write permission as the password. Do not use a personal token with broader permissions than necessary.

Create a Multibranch Pipeline job pointing to this repository and enable GitHub branch discovery. Configure a GitHub webhook to notify Jenkins of pushes and pull requests if inbound webhook connectivity is available. Otherwise use a conservative periodic scan interval.

The pipeline runs formatting and Maven tests first. Image publishing is opt-in via `PUBLISH_IMAGES=true`, and is restricted to `main`. Run a successful CI build before enabling it. GitHub Actions also publishes images to GHCR; avoid running both publishers at the same time unless that duplication is intentional.

## 3. Verify container images

After the GitHub Actions publish workflow finishes successfully, verify the expected packages in the repository's GitHub Packages page. The image names are:

`ghcr.io/an619/marketplace-gateway-service` and `ghcr.io/an619/marketplace-<service-name>` for the other services.

Use an immutable commit-based image tag for release deployments. The `latest` tag is intended for development convenience and should not be treated as a production release identifier.

If the packages are private, create a Kubernetes image-pull secret in the target namespace using a least-privilege GitHub token with package read permission, then configure `global.imagePullSecrets` in the Helm values. Never commit the token or generated Secret YAML.

## 4. Deploy with Argo CD

Install Argo CD into the target cluster using its official installation instructions, then connect Argo CD to this repository. From a machine with `kubectl` configured for the intended cluster:

```bash
kubectl create namespace argocd
# Install Argo CD using the official manifest/instructions for your chosen version.
kubectl apply -f argocd/marketplace-application.yaml
kubectl -n argocd get applications
kubectl -n marketplace get deployments,pods,services
```

The application manifest targets `helm/values-dev.yaml` and enables automated sync, pruning, and self-healing. Review the target cluster and values before applying it. Do not use the development values for a production environment.

## 5. External dependencies and secrets

The Helm chart deploys application services only. Before syncing, configure the database, Kafka, Redis, search, identity provider, and any AI/runtime services required by the application. Supply settings through a Kubernetes Secret or a secret manager integration; do not put passwords, API keys, signing keys, or production connection strings in Git.

The chart's health probes assume the services expose Spring Boot Actuator health endpoints on their configured `SERVER_PORT`. Confirm each service's actual port and dependency readiness in the target environment.

## 6. Release checklist

- [ ] CI formatting, unit tests, integration tests, and coverage complete successfully.
- [ ] Repository security scan and Helm/Terraform validation reviewed.
- [ ] All required image packages exist and the target cluster can pull them.
- [ ] Database migrations and external dependency configuration reviewed.
- [ ] Secrets provisioned outside Git.
- [ ] Resource requests/limits, ingress/TLS, monitoring, backup and rollback plan reviewed.
- [ ] Deploy first to development/staging and verify smoke tests before production.

Terraform validation in CI uses `terraform init -backend=false` and `terraform validate`; it does not apply infrastructure. Review cost and the full Terraform plan before manually creating AWS resources.
