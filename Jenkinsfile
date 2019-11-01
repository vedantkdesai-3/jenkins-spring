pipeline {
   agent any

   tools {
      maven "M3"
   }

   stages {
      stage('Build') {
         steps {
            sh "mvn -Dmaven.test.failure.ignore=true clean package"
         }

         post {
            success {
               junit '**/target/surefire-reports/TEST-*.xml'
               archiveArtifacts 'target/*.jar'
            }
         }
      }

      stage('Build Docker Image') {
         steps {
            withCredentials([string(credentialsId: 'DockerPwd', variable: 'DockerPassword')]) {
               sh "docker login -u vedantkdesai -p ${DockerPassword}"
            }
            sh "docker build -t vedantkdesai/vedant-docker:${BUILD_NUMBER} ."
         }
      }

      stage('Push Docker Image') {
         steps {
            withCredentials([string(credentialsId: 'DockerPwd', variable: 'DockerPassword')]) {
               sh "docker login -u vedantkdesai -p ${DockerPassword}"
            }
            sh "docker push vedantkdesai/vedant-docker:${BUILD_NUMBER}"
         }
      }

      stage('Run Container') {
         steps {
            def dockerRunCMD = "docker run -p 8085:8085 --name vedant-docker vedantkdesai/vedant-docker:${BUILD_NUMBER}"
            sshagent(['vedant-aws']) {
               def dockerRunCMD = "docker run -p 8085:8085 --name vedant-docker vedantkdesai/vedant-docker:${BUILD_NUMBER}"

            }

         }
      }
   }
}
