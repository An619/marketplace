# Helm chart

This chart renders Kubernetes Deployments and ClusterIP Services for the API gateway and the 15 deployable application services. It is an application chart; it does not install databases, message brokers, identity provider, search infrastructure, observability stack, or AWS resources.

## Validate locally

From the repository root:

    helm lint ./helm
    helm template marketplace ./helm --namespace marketplace --values ./helm/values-dev.yaml

## Image naming

The image publishing workflow creates image names in this form:

    ghcr.io/an619/marketplace-product-service:latest

The values file maps each service to its image. The chart uses the latest tag for the development default. For repeatable deployments, override global.imageTag with an immutable tag such as a CI commit SHA. GHCR packages may be private; create imagePullSecrets and configure global.imagePullSecrets if your cluster cannot pull them anonymously.

## Runtime configuration and secrets

If global.existingSecret is non-empty, the chart imports that Secret into each application container. Create the Secret outside Git and populate only the environment variables actually required by the services. Do not commit real database passwords, OAuth client secrets, API keys, or production configuration.

The chart sets SPRING_PROFILES_ACTIVE, SERVER_PORT, and Spring Boot health probe settings. Verify that each service exposes the configured Actuator health endpoints and that the port matches the application configuration.

## Ingress

Ingress is disabled by default. Set ingress.enabled to true and configure ingress.className, hosts, TLS, and controller-specific annotations for the controller installed in your cluster. For AWS EKS, choose and configure the AWS Load Balancer Controller or another supported ingress controller before enabling this feature.

## Argo CD

The Argo CD Application manifest is at argocd/marketplace-application.yaml. Review the repository URL, target branch, values file, image tags, namespace, secrets, and automated prune/self-heal behavior before applying it. Do not sync until the image registry and external dependencies are ready.
