pipeline {
    agent 
    { 
        label 'vm-slave' 
    }
    environment {
      DOCKER_REPO_URL = 'tools.devopshub.cn:2020/idcps'
    }
    stages {
        stage('before-build'){
          steps {
            sh "printenv"
          }
        }
        stage('build') {
          parallel {
            stage('build-client') {
              steps {
                sh "docker build -f client/web/Dockerfile -t ${DOCKER_REPO_URL}/client:${env.BRANCH_NAME}-${env.BUILD_ID} -t ${DOCKER_REPO_URL}/client:latest client/web"
                sh "docker login tools.devopshub.cn:2020 -u admin -p admin"
                sh "docker push ${DOCKER_REPO_URL}/client:latest"
                sh "docker push ${DOCKER_REPO_URL}/client:${env.BRANCH_NAME}-${env.BUILD_ID}"
              }
            }

            stage('build-management') {
              steps {
                sh "docker build -f management/web/Dockerfile -t ${DOCKER_REPO_URL}/management:${env.BRANCH_NAME}-${env.BUILD_ID} -t ${DOCKER_REPO_URL}/management:latest management/web"
                sh "docker login tools.devopshub.cn:2020 -u admin -p admin"
                sh "docker push ${DOCKER_REPO_URL}/management:latest"
                sh "docker push ${DOCKER_REPO_URL}/management:${env.BRANCH_NAME}-${env.BUILD_ID}"
              }
            }

            stage('build-statistics-service') {
              steps {
                sh "docker build -f statistics-service/api/Dockerfile -t ${DOCKER_REPO_URL}/statistics_service_api:${env.BRANCH_NAME}-${env.BUILD_ID} -t ${DOCKER_REPO_URL}/statistics_service_api:latest statistics-service/api"
                sh "docker build -f statistics-service/worker/Dockerfile -t ${DOCKER_REPO_URL}/statistics_service_worker:${env.BRANCH_NAME}-${env.BUILD_ID} -t ${DOCKER_REPO_URL}/statistics_service_worker:latest statistics-service/worker"

                sh "docker login tools.devopshub.cn:2020 -u admin -p admin"
                echo "push service api..."
                sh "docker push ${DOCKER_REPO_URL}/statistics_service_api:latest"
                sh "docker push ${DOCKER_REPO_URL}/statistics_service_api:${env.BRANCH_NAME}-${env.BUILD_ID}"

                echo "push service worker..."
                sh "docker push ${DOCKER_REPO_URL}/statistics_service_worker:latest"
                sh "docker push ${DOCKER_REPO_URL}/statistics_service_worker:${env.BRANCH_NAME}-${env.BUILD_ID}"
              }
            }

            stage('build-product-service') {
              steps {
                sh "docker build -f product-service/api/Dockerfile -t ${DOCKER_REPO_URL}/product_service_api:${env.BRANCH_NAME}-${env.BUILD_ID} -t ${DOCKER_REPO_URL}/product_service_api:latest product-service/api"
                sh "docker login tools.devopshub.cn:2020 -u admin -p admin"
                sh "docker push ${DOCKER_REPO_URL}/product_service_api:latest"
                sh "docker push ${DOCKER_REPO_URL}/product_service_api:${env.BRANCH_NAME}-${env.BUILD_ID}"
              }
            }
          }
        }
        stage('Dev') { 
            steps {
                sh "echo hello world! Dev!"
            }
        }
        stage('Test') {  
            steps {
                input("Please confirm to deploy test env?")
                sh "echo hello world! Test!"
            }
        }
        stage('Production') { 
            steps {
                input("Please confirm to deploy production env?")
                sh "echo hello world! Deploy!"
            }
        }
    }
}