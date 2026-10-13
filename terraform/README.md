# Terraform: AWS EKS practice environment

This configuration is a starter for a disposable development EKS environment. It creates a VPC with public/private subnets, a single NAT gateway, an EKS control plane, and a managed EC2 node group. It does not deploy the application or configure Argo CD.

## Before you start

- AWS CLI configured with an account and permissions appropriate for EKS, EC2, IAM, VPC and CloudFormation-backed module resources.
- Terraform 1.9 or later.
- A Kubernetes version supported by EKS in the selected region.
- A restricted public IPv4 CIDR for the EKS API. Never use 0.0.0.0/0.
- An understanding of AWS charges.

EKS clusters, EC2 worker nodes, NAT gateways, load balancers, EBS, logging and data transfer can incur charges. A single NAT gateway lowers cost but is not highly available. This configuration is not a free-tier guarantee.

## Plan before apply

From this directory:

```bash
cp terraform.tfvars.example terraform.tfvars
# Edit terraform.tfvars and replace the documentation IP with your own public IPv4 /32.
terraform init
terraform fmt -check -recursive
terraform validate
terraform plan
```

Review the entire plan and estimated cost. Do not run terraform apply until you understand every resource it will create. The example CIDR is deliberately non-routable documentation space and must be replaced.

## Create and connect

After reviewing the plan, create the resources:

```bash
terraform apply
aws eks update-kubeconfig --region ap-south-1 --name marketplace-dev
kubectl get nodes
```

Use the exact cluster name and region from your outputs/variables if you change them. Restrict the EKS API CIDR whenever your public IP changes.

## Destroy after practice

```bash
terraform destroy
```

Confirm the destroy plan before approving it. Then check the AWS console for remaining NAT gateways, load balancers, EBS volumes, snapshots, elastic IPs, CloudWatch log groups and other billable resources. Terraform may not manage resources created manually or by Kubernetes controllers.

## Next steps

1. Install Helm and Argo CD in the cluster.
2. Publish application images through the repository workflow and confirm registry access.
3. Configure required database, Kafka, Redis, Elasticsearch, Keycloak and Ollama dependencies.
4. Review the Argo CD Application and Helm values before syncing.
5. Add remote Terraform state with locking before using this configuration collaboratively.

Do not treat a successful Terraform apply as proof that the application is production-ready.
