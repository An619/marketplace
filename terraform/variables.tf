variable "aws_region" {
  description = "AWS region in which to create the practice environment."
  type        = string
  default     = "ap-south-1"
}

variable "environment" {
  description = "Environment name used in resource naming and tags."
  type        = string
  default     = "dev"

  validation {
    condition     = contains(["dev", "qa", "prod"], var.environment)
    error_message = "environment must be dev, qa, or prod."
  }
}

variable "cluster_name" {
  description = "Base name for the EKS cluster."
  type        = string
  default     = "marketplace"
}

variable "kubernetes_version" {
  description = "EKS Kubernetes version supported by the selected AWS region."
  type        = string
  default     = "1.33"
}

variable "vpc_cidr" {
  description = "IPv4 CIDR block for the EKS VPC."
  type        = string
  default     = "10.40.0.0/16"
}

variable "cluster_endpoint_public_access_cidrs" {
  description = "Public IPv4 CIDRs allowed to reach the EKS API. Set this to your current public IP /32; 0.0.0.0/0 is rejected."
  type        = list(string)

  validation {
    condition = length(var.cluster_endpoint_public_access_cidrs) > 0 && alltrue([
      for cidr in var.cluster_endpoint_public_access_cidrs :
      can(cidrhost(cidr, 0)) && cidr != "0.0.0.0/0" && cidr != "::/0"
    ])
    error_message = "Provide at least one valid, restricted CIDR and do not use 0.0.0.0/0 or ::/0."
  }
}

variable "node_instance_types" {
  description = "EC2 instance types for the managed node group. Check regional availability and cost."
  type        = list(string)
  default     = ["t3.medium"]
}

variable "node_min_size" {
  description = "Minimum node count."
  type        = number
  default     = 1
}

variable "node_max_size" {
  description = "Maximum node count."
  type        = number
  default     = 2
}

variable "node_desired_size" {
  description = "Initial desired node count."
  type        = number
  default     = 1

  validation {
    condition     = var.node_desired_size >= var.node_min_size && var.node_desired_size <= var.node_max_size
    error_message = "node_desired_size must be between node_min_size and node_max_size."
  }
}

variable "tags" {
  description = "Additional tags applied to created resources."
  type        = map(string)
  default     = {}
}
