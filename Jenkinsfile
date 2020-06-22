def getHost() {
  def remote = [:]
  remote.name = 'server-dev'
  remote.host = "${BOATHOUSE_DEV_HOST}"
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
    options {
        buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '5', numToKeepStr: '10')
    }
    environment {
      CREDS_GITHUB_REGISTRY = credentials('creds-github-registry')
      CREDS_DEV_SERVER = credentials('creds-dev-server')
      def server=''
    }

    // 阶段配置
    stages {
         // clean workspace
         /*
         stage('delete files from workspace') {
          steps {
            sh 'ls -l'
            sh 'sudo rm -rf ./*'
          }
        }*/

        // 构建准备
        stage('before-build'){
          steps {
            sh "printenv"
          }
        }

        // 构建
        stage('build') {
          parallel {
            // 用户界面
            stage('build-client') {
              steps {
                sh "docker build -f src/client/web/Dockerfile -t ${BOATHOUSE_CONTAINER_REGISTRY}/client:${env.BRANCH_NAME}-${env.BUILD_ID} -t ${BOATHOUSE_CONTAINER_REGISTRY}/client:latest src/client/web"
                sh "docker login docker.pkg.github.com -u ${CREDS_GITHUB_REGISTRY_USR} -p ${CREDS_GITHUB_REGISTRY_PSW}"
                sh "docker push ${BOATHOUSE_CONTAINER_REGISTRY}/client:latest"
                sh "docker push ${BOATHOUSE_CONTAINER_REGISTRY}/client:${env.BRANCH_NAME}-${env.BUILD_ID}"
              }
            }

            // 管理界面
            stage('build-management') {
              steps {
                sh "docker build -f src/management/web/Dockerfile -t ${BOATHOUSE_CONTAINER_REGISTRY}/management:${env.BRANCH_NAME}-${env.BUILD_ID} -t ${BOATHOUSE_CONTAINER_REGISTRY}/management:latest management/web"
                sh "docker login docker.pkg.github.com -u ${CREDS_GITHUB_REGISTRY_USR} -p ${CREDS_GITHUB_REGISTRY_PSW}"
                sh "docker push ${BOATHOUSE_CONTAINER_REGISTRY}/management:latest"
                sh "docker push ${BOATHOUSE_CONTAINER_REGISTRY}/management:${env.BRANCH_NAME}-${env.BUILD_ID}"
              }
            }

          }
        }

  /*
        stage('deploy-dev') { 
            steps {
              sh "sed -i 's/#{BOATHOUSE_ORG_NAME}#/${BOATHOUSE_ORG_NAME}/g' src/docker-compose-template.yaml"
              script {
                server = getHost()
                echo "copy docker-compose file to remote server...."       
                sshPut remote: server, from: 'src/docker-compose-template.yaml', into: '.'
                sshCommand remote: server, command: "mkdir -p src/product-service/api/scripts"
                sshPut remote: server, from: 'src/product-service/api/scripts/init.sql', into: './src/product-service/api/scripts/init.sql'

                // 下面的 docker-compose-template.yaml 已经复制到根目录下，不用再调整
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

    
        stage('Jmeter') {
          steps {
            script{
                echo "waitting for the sevice up...."
                sleep 80
                sh "ls -al ./test/jmeter"
                sh "cd test/jmeter && find . -name '*.log' -delete"
                sh "rm -R ./test/jmeter/output || exit 0"
                sh "mkdir ./test/jmeter/output"
                sh "docker run --interactive --rm --volume `pwd`/test/jmeter:/jmeter egaillardon/jmeter --nongui --testfile boat-house.jmx --logfile output/result.jtl -Jdomain=${BOATHOUSE_DEV_HOST} -e -o ./output"
                sh "ls -al ./test/jmeter"
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: './test/jmeter/output', reportFiles: 'index.html', reportName: 'Jmeter Report', reportTitles: ''])
            }
          }
        }
        */

        stage('build-uitest'){
            steps {
                sh "docker build -f test/selenium/dotnet-uitest/Dockerfile -t ${BOATHOUSE_CONTAINER_REGISTRY}/uitest:${env.BRANCH_NAME}-${env.BUILD_ID} -t ${BOATHOUSE_CONTAINER_REGISTRY}/uitest:latest test/selenium/dotnet-uitest"
            }
        }

        // 运行UI测试
        stage('run-uitest'){
            steps {
                script {
                    // 本地执行测试
                    sh "mkdir -p ./test/selenium/dotnet-uitest/uitest/report"
                    sh "docker-compose -f ./test/selenium/dotnet-uitest/docker-compose-hub.yml -p uitest-hub down"
                    sh "docker-compose -f ./test/selenium/dotnet-uitest/docker-compose-hub.yml -p uitest-hub pull"
                    sh "docker-compose -f ./test/selenium/dotnet-uitest/docker-compose-hub.yml -p uitest-hub up -d"
                    sh "docker run -v \$(pwd)/test/selenium/dotnet-uitest/uitest/report:/app/TestResults ${BOATHOUSE_CONTAINER_REGISTRY}/uitest:latest"
                    mstest testResultsFile:"test/selenium/**/*.trx", keepLongStdio: true
                }
            }
        }

        // 测试环境部署
        stage('deploy-test') {
            steps {
                timeout(5) {
                    input message: '是否部署到测试环境?', ok: '是', submitter: 'admin'
                }
                sh "find devops/kompose/test -name *-deployment.yaml | xargs sed -i 's/#{BOATHOUSE_ORG_NAME}#/${BOATHOUSE_ORG_NAME}/g'"
                sh "find devops/kompose/test -name *-deployment.yaml | xargs sed -i 's/#{env.BRANCH_NAME}#-#{env.BUILD_ID}#/${env.BRANCH_NAME}-${env.BUILD_ID}/g'"
                kubernetesDeploy configs: 'devops/kompose/test/client-deployment.yaml,devops/kompose/test/management-deployment.yaml', deleteResource: true, kubeConfig: [path: ''], kubeconfigId: 'creds-test-k8s', secretName: 'regcred', secretNamespace: 'boathouse-dev', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
                kubernetesDeploy configs: 'devops/kompose/test/*', deleteResource: false, kubeConfig: [path: ''], kubeconfigId: 'creds-test-k8s', secretName: 'regcred', secretNamespace: 'boathouse-dev', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']

            }
        }

        // 生产环境部署
        stage('deploy-production') { 
            steps {
                timeout(5) {
                    input message: '是否部署到生产环境?', ok: '是', submitter: 'admin'
                }
                sh "find devops/kompose/prod -name *-deployment.yaml | xargs sed -i 's/#{BOATHOUSE_ORG_NAME}#/${BOATHOUSE_ORG_NAME}/g'"
                sh "find devops/kompose/prod -name *-deployment.yaml | xargs sed -i 's/#{env.BRANCH_NAME}#-#{env.BUILD_ID}#/${env.BRANCH_NAME}-${env.BUILD_ID}/g'"
                kubernetesDeploy configs: 'devops/kompose/prod/client-deployment.yaml,devops/kompose/prod/management-deployment.yaml', deleteResource: true, kubeConfig: [path: ''], kubeconfigId: 'creds-test-k8s', secretName: 'regcred', secretNamespace: 'boathouse-prod', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
                kubernetesDeploy configs: 'devops/kompose/prod/*', deleteResource: false, kubeConfig: [path: ''], kubeconfigId: 'creds-test-k8s', secretName: 'regcred', secretNamespace: 'boathouse-prod', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
            }
        }

    }

   /*   post {
      always {
      
        sh "sudo rm -rf src/product-service/api/target"
        sh "sudo rm -rf src/account-service/api/target"
        
      }
    }*/
  }