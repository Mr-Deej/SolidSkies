pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package install -DskipTests'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'build/localCache/*.jar', fingerprint: true 
            }
        }
    }
}
