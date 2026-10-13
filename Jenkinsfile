pipeline {
    agent any

    parameters {
        booleanParam(
            name: 'PUBLISH_IMAGES',
            defaultValue: false,
            description: 'Build and push all service images to GHCR. Enable only on main after CI succeeds.'
        )
    }

    tools {
        jdk 'jdk-21'
        maven 'maven-3.9'
    }

    environment {
        JAVA_HOME = "${tool 'jdk-21'}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Format Check') {
            steps {
                sh 'mvn -B spotless:check'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn -B clean verify -Pcoverage'
            }
        }

        stage('SonarQube Analysis') {
            when {
                anyOf {
                    branch 'main'
                    branch 'develop'
                }
            }
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn -B sonar:sonar'
                }
            }
        }

        stage('SonarQube Quality Gate') {
            when {
                anyOf {
                    branch 'main'
                    branch 'develop'
                }
            }
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build and Publish Container Images') {
            when {
                allOf {
                    branch 'main'
                    expression { params.PUBLISH_IMAGES }
                }
            }
            steps {
                script {
                    def imageTag = sh(script: 'git rev-parse --short=12 HEAD', returnStdout: true).trim()
                    def services = [
                        'gateway-service',
                        'identity-service',
                        'product-service',
                        'seller-service',
                        'buyer-service',
                        'category-service',
                        'inventory-service',
                        'pricing-service',
                        'workflow-service',
                        'notification-service',
                        'search-service',
                        'ai-service',
                        'audit-service',
                        'subscription-service',
                        'report-service',
                        'admin-service'
                    ]

                    docker.withRegistry('https://ghcr.io', 'ghcr-push-token') {
                        for (service in services) {
                            def imageName = "ghcr.io/an619/marketplace-${service}"
                            sh "docker build -f docker/Dockerfile.service --build-arg SERVICE_MODULE=${service} -t ${imageName}:${imageTag} ."
                            sh "docker push ${imageName}:${imageTag}"
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml,**/target/failsafe-reports/*.xml'
            jacoco execPattern: '**/target/jacoco.exec'
        }
    }
}
