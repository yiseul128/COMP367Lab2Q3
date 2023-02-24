pipeline {
    agent any

    tools {
        maven "maven"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/yiseul128/COMP367Lab2Q3.git'
                
                bat "mvn clean compile"
            }

            post {
                success {
                    echo "success"
                }
            }
        }
    }
}
