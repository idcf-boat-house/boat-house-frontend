pipeline {
    agent none
    stages {
        stage('Build') { 
            agent 
            { 
                label 'vm-slave' 
            } 
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']],
                            userRemoteConfigs: [[url: 'https://github.com/icdps/BoatHouse.git']]])
                sh 'docker-compose -f docker-compose.yml -f docker-compose-standalone.yml up -d'
            }
        }
        stage('Dev') { 
            agent 
            { 
                label 'vm-slave' 
            }
            steps {
                sh 'echo hello world! Dev!'
            }
        }
        stage('Test') { 
            agent 
            { 
                label 'vm-slave' 
            }
            steps {
                sh 'echo hello world! Test!'
            }
        }
        stage('Deploy') { 
            agent 
            { 
                label 'vm-slave' 
            }
            steps {
                sh 'echo hello world! Deploy!'
            }
        }
    }
}