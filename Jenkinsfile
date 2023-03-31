pipeline {
    agent any
    tools {
        maven "maven"
    }
    
    environment {
        AZURE_ACI_NAME = "comp367-group-project3" // replace with your Azure container instance name
        DOCKERHUB_USERNAME = 'dew0135'
        DOCKERHUB_REGISTRY = 'docker.io'
        DOCKERHUB_REPOSITORY = 'comp367lab2q3'
        IMAGE_TAG = 'latest'
        CONTAINER_NAME = 'comp367-group-project3'
        RESOURCE_GROUP = 'newresourcegroup'
        REGION = 'eastus'
        CPU = 1
        MEMORY = 1.5
    }
    
    stages {
        stage('Check out') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/yiseul128/COMP367Lab2Q3.git'
            }
        }
        stage('Build maven project') {
            steps {
                bat "mvn clean install"
            }            
        }
        stage('Docker build') {
            steps {
                bat "docker build -t dew0135/comp367lab2q3:%BUILD_ID% ."
            }            
        }
        stage('Docker login') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'b9385f93-e054-4684-850f-3a9bfa2f2fba', passwordVariable: 'docker_password', usernameVariable: 'docker_username')]) {
                        echo 'Username: %docker_username%'
                        bat 'docker login --username=%docker_username% --password=%docker_password%'
                    }
                }
            }            
        }
        stage('Docker push') {
            steps {
                script {
                    bat 'docker push dew0135/comp367lab2q3:%BUILD_ID%'
                }
            }            
        }
        
        stage('Deploy to Azure Container Instances') {
            steps {
                script {
                	withCredentials([string(credentialsId: 'f980e30e-f1e7-4c20-8e56-98dfaf18b915', variable: 'azure_pat')]) {
	                    bat "az login --service-principal -u ${azure_pat} -p '' --tenant ${env.TENANT_ID}"
	                    bat "az acr login --name ${DOCKERHUB_REGISTRY} --expose-token"
	                    bat "az container create --resource-group ${AZURE_RG} --name ${CONTAINER_NAME} --image ${DOCKERHUB_REGISTRY}/${DOCKERHUB_USERNAME}/${DOCKERHUB_REPOSITORY}:${IMAGE_TAG} --cpu ${CPU} --memory ${MEMORY} --ports 8083 --location ${REGION}"
                	}
                }
            }
        }
    }
}
