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
                archiveArtifacts artifacts: 'target/SolidSkies.jar', fingerprint: true 
            }
        }
    }
}
