pipeline {
    agent 
    { 
        label 'vm-slave' 
    }
    environment {
      DOCKER_REPO_URL = 'tools.devopshub.cn:2020/idcps'
      SERVER_DEV_CREDS = credentials('delopy-server-dev-creds')
    }
    stages {
          
        stage('before-build'){
          
          steps {
          
            sh "printenv"
            echo "creds: ${SERVER_DEV_CREDS}"
            script {
              def remote = [:]
              remote.name = 'server-dev'
              remote.host = '138.91.37.88'
              remote.user = "${SERVER_DEV_CREDS_USR}"
              remote.password = "${SERVER_DEV_CREDS_PSW}"
              remote.port = 22
              remote.allowAnyHosts = true
              
            }
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
                echo "stopping previous docker composed containers...."
                sh "docker-compose down"
                echo "restarting new docker containers...."
                sh "docker-compose -f docker-compose-template.yaml up -d"
                echo "successfully started!"
            }
        }
        stage('Test') {  
            steps {
                
                sh "echo hello world! Test!"
            }
        }
        stage('Production') { 
            steps {
                
                sh "echo hello world! Deploy!"
            }
        }
    }

    post {
      always {
        echo 'This will always run!'
      }

      success {
        echo 'This will run only if successful'
      }

      failure {
        echo 'This will run only if failed'
      }

      unstable {
        echo 'This will run only if the run was marked as unstable'
      }

      changed {
        echo 'This will run only if the state of the pipeline has changed'
        echo 'For example, if the Pipeline was previously failing but is now successful'
      }


    }
}