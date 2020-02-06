pipeline {
    agent 
    { 
        label 'vm-slave' 
    } 
    stages {
        stage('Build') { 
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']],
                            userRemoteConfigs: [[url: 'https://github.com/icdps/BoatHouse.git']]])
                sh 'docker-compose -f docker-compose.yml -f docker-compose-standalone.yml up -d'
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