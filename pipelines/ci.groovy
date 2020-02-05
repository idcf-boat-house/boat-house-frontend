pipeline {
    agent 
    { 
        label 'vm-slave' 
    } 
    stages {
        stage('Build') { 
            steps {
                checkout scm
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