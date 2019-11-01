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
         withCredentials([usernameColonPassword(credentialsId: 'DockerCred', variable: 'dockerhub')]) {
             sh "docker login -u vedantkdesai -p ${DockerCred}"
         }
         sh 'docker build -t vedantkdesai/vedant-docker:1.0'
      }

      stage('Push Docker Image') {
         withCredentials([usernameColonPassword(credentialsId: 'DockerCred', variable: 'dockerhub')]) {
            sh "docker login -u vedantkdesai -p ${DockerCred}"
         }
         sh 'docker push vedantkdesai/vedant-docker:2.0'
      }
   }
}
