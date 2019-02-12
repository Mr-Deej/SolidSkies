pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package install javadoc:javadoc'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true 
            }
        }
    }
}
