pipeline {
  agent any
  stages {
    stage('error') {
      steps {
        sh 'docker build -f ./client/web -t boat-house_client:v1 .'
      }
    }

  }
}