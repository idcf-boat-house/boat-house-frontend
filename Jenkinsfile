pipeline {
  agent any
  stages {
    stage('error') {
      agent any
      steps {
        sh 'docker build -f ./client/web -t boat-house_client:v1 .'
      }
    }

  }
}