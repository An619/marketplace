output "cluster_name" {
  description = "Created EKS cluster name."
  value       = module.eks.cluster_name
}

output "cluster_endpoint" {
  description = "EKS Kubernetes API endpoint."
  value       = module.eks.cluster_endpoint
}

output "vpc_id" {
  description = "VPC ID used by EKS."
  value       = module.vpc.vpc_id
}

output "configure_kubectl" {
  description = "Run this command on a workstation with AWS CLI and kubectl installed."
  value       = "aws eks update-kubeconfig --region ${var.aws_region} --name ${module.eks.cluster_name}"
}
