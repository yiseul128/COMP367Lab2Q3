pipeline {
    agent any

    tools {
        maven "maven"
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
                    bat 'docker push dew0135/mavenproject4docker:%BUILD_ID%'
                }
            }            
        }
    }
}
