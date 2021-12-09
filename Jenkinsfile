pipeline {
  agent any

  tools {
    maven 'mvn-3.8.4'
  }

  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }
    
    stage('Make Container') {
      steps {
      sh "docker build -t squad-devcompilers/bluebank ."
      
      }
    }
    
    stage('Check Specification') {
      steps {
        sh "docker-compose up"
      }
    }
  