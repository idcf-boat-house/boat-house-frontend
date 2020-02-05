pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                sh 'echo hello world! Build!'
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