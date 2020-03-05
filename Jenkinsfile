def getHost() {
  def remote = [:]
  remote.name = 'server-dev'
  remote.host = "${boathouse-container-registry}"
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
      DOCKER_REPO_URL = ${boathouse-dev-host-ip-address}
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
                sh "docker-compose -f product-service/api/docker-compose.build.yaml up"
                junit 'product-service/api/target/surefire-reports/**/TEST-*.xml'
                cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: 'product-service/api/target/site/cobertura/coverage.xml', conditionalCoverageTargets: '70, 0, 0', failUnhealthy: false, failUnstable: false, lineCoverageTargets: '80, 0, 0', maxNumberOfBuilds: 0, methodCoverageTargets: '80, 0, 0', onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false
                sh "docker build -f product-service/api/Dockerfile.image -t ${DOCKER_REPO_URL}/product_service_api:${env.BRANCH_NAME}-${env.BUILD_ID} -t ${DOCKER_REPO_URL}/product_service_api:latest product-service/api"

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
                sshCommand remote: server, command: "mkdir -p product-service/api/scripts"
                sshPut remote: server, from: 'product-service/api/scripts/init.sql', into: './product-service/api/scripts/init.sql'


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
                submitter "admin"
            }
            steps {
                kubernetesDeploy configs: 'kompose/test/client-deployment.yaml,kompose/test/management-deployment.yaml,kompose/test/product-service-api-deployment.yaml,kompose/test/statistics-service-api-deployment.yaml,kompose/test/statistics-service-worker-deployment.yaml', deleteResource: true, kubeConfig: [path: ''], kubeconfigId: 'creds-test-k8s', secretName: 'regcred', secretNamespace: 'boathouse-dev', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
                kubernetesDeploy configs: 'kompose/test/*', deleteResource: false, kubeConfig: [path: ''], kubeconfigId: 'creds-test-k8s', secretName: 'regcred', secretNamespace: 'boathouse-dev', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']

            }
        }

        stage('deploy-production') { 
            input {
                message "是否部署到生产环境?"
                ok "是"
                submitter "admin"
            }
            steps {
                kubernetesDeploy configs: 'kompose/prod/client-deployment.yaml,kompose/prod/management-deployment.yaml,kompose/prod/product-service-api-deployment.yaml,kompose/prod/statistics-service-api-deployment.yaml,kompose/prod/statistics-service-worker-deployment.yaml', deleteResource: true, kubeConfig: [path: ''], kubeconfigId: 'creds-test-k8s', secretName: 'regcred', secretNamespace: 'boathouse-prod', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
                kubernetesDeploy configs: 'kompose/prod/*', deleteResource: false, kubeConfig: [path: ''], kubeconfigId: 'creds-test-k8s', secretName: 'regcred', secretNamespace: 'boathouse-prod', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
            }
        }
    }

    post {
      always {
        sh "sudo rm -rf product-service/api/target"
      }
    }
    


  }
