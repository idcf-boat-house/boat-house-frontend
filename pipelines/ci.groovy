pipeline {
    agent none 
    stages {
        stage('Build') { 
            agent {
                docker { image 'maven:3-alpine' }
            }
            steps {
                sh 'mvn --version'
            }
        }
        stage('Test') { 
            steps {
                sh 'echo hello world! Test!'
            }
        }
        stage('Deploy') { 
            steps {
                sh 'echo hello world! Deploy!'
            }
        }
    }
}