def getHost() {
  def remote = [:]
  remote.name = 'server-dev'
  remote.host = '138.91.37.88'
  remote.user = "${env.CREDS_DEV_SERVER_USR}"
  remote.password = "${env.CREDS_DEV_SERVER_PSW}"
  remote.port = 22
  remote.allowAnyHosts = true
  return remote
}

pipeline {
    agent 
    { 
        label 'vm-slave' 
    }
    environment {
      DOCKER_REPO_URL = 'docker.pkg.github.com/icdps/boat-house'
      CREDS_GITHUB_REGISTRY = credentials('creds-github-registry')
      CREDS_DEV_SERVER = credentials('creds-dev-server')
      def server=''
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
                sh "docker login docker.pkg.github.com -u ${CREDS_GITHUB_REGISTRY_USR} -p ${CREDS_GITHUB_REGISTRY_PSW}"
                sh "docker push ${DOCKER_REPO_URL}/client:latest"
                sh "docker push ${DOCKER_REPO_URL}/client:${env.BRANCH_NAME}-${env.BUILD_ID}"
              }
            }

            stage('build-management') {
              steps {
                sh "docker build -f management/web/Dockerfile -t ${DOCKER_REPO_URL}/management:${env.BRANCH_NAME}-${env.BUILD_ID} -t ${DOCKER_REPO_URL}/management:latest management/web"
                sh "docker login docker.pkg.github.com -u ${CREDS_GITHUB_REGISTRY_USR} -p ${CREDS_GITHUB_REGISTRY_PSW}"
                sh "docker push ${DOCKER_REPO_URL}/management:latest"
                sh "docker push ${DOCKER_REPO_URL}/management:${env.BRANCH_NAME}-${env.BUILD_ID}"
              }
            }

            stage('build-statistics-service') {
              steps {
                sh "docker build -f statistics-service/api/Dockerfile -t ${DOCKER_REPO_URL}/statistics_service_api:${env.BRANCH_NAME}-${env.BUILD_ID} -t ${DOCKER_REPO_URL}/statistics_service_api:latest statistics-service/api"
                sh "docker build -f statistics-service/worker/Dockerfile -t ${DOCKER_REPO_URL}/statistics_service_worker:${env.BRANCH_NAME}-${env.BUILD_ID} -t ${DOCKER_REPO_URL}/statistics_service_worker:latest statistics-service/worker"

                sh "docker login docker.pkg.github.com -u ${CREDS_GITHUB_REGISTRY_USR} -p ${CREDS_GITHUB_REGISTRY_PSW}"
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
                sh "docker login docker.pkg.github.com -u ${CREDS_GITHUB_REGISTRY_USR} -p ${CREDS_GITHUB_REGISTRY_PSW}"
                sh "docker push ${DOCKER_REPO_URL}/product_service_api:latest"
                sh "docker push ${DOCKER_REPO_URL}/product_service_api:${env.BRANCH_NAME}-${env.BUILD_ID}"
              }
            }
          }
        }

        stage('deploy-dev') { 
            steps {
              script {
                server = getHost()
                echo "copy docker-compose file to remote server...."       
                sshPut remote: server, from: 'docker-compose-template.yaml', into: '.'

                echo "stopping previous docker containers...."       
                sshCommand remote: server, command: "docker login docker.pkg.github.com -u ${CREDS_GITHUB_REGISTRY_USR} -p ${CREDS_GITHUB_REGISTRY_PSW}"
                sshCommand remote: server, command: "docker-compose -f docker-compose-template.yaml -p boathouse down"
                
                echo "pulling newest docker images..."
                sshCommand remote: server, command: "docker-compose -f docker-compose-template.yaml -p boathouse pull"
                
                echo "restarting new docker containers...."
                sshCommand remote: server, command: "docker-compose -f docker-compose-template.yaml -p boathouse up -d"
                echo "successfully started!"
              }
            }
        }

        stage('deploy-test') {  
          input {
                message "是否部署到测试环境?"
                ok "是"
                
            }
            steps {
                kubernetesDeploy configs: 'kompose/test/*deployment.yaml', deleteResource: true, kubeConfig: [path: ''], kubeconfigId: 'creds-test-k8s', secretName: 'regcred', secretNamespace: 'boathouse-dev', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
                kubernetesDeploy configs: 'kompose/test/*', deleteResource: false, kubeConfig: [path: ''], kubeconfigId: 'creds-test-k8s', secretName: 'regcred', secretNamespace: 'boathouse-dev', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']

            }
        }

        stage('deploy-production') { 
          input {
                message "是否部署到生产环境?"
                ok "是"
            }
            steps {
                kubernetesDeploy configs: 'kompose/prod/*deployment.yaml', deleteResource: true, kubeConfig: [path: ''], kubeconfigId: 'creds-test-k8s', secretName: 'regcred', secretNamespace: 'boathouse-prod', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
                kubernetesDeploy configs: 'kompose/prod/*', deleteResource: false, kubeConfig: [path: ''], kubeconfigId: 'creds-test-k8s', secretName: 'regcred', secretNamespace: 'boathouse-prod', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
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
