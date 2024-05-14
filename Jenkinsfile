pipeline {

    agent any
    
    stages {

        stage('Start REST Server') {
            steps {
                dir('src/main/resources/json') {
                    bat 'node --version'
                }
            }
        }

        stage('Test') {
            steps {
                bat 'mvn clean test'
            }
        }
        
        stage('Generate HTML Report') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'x-output/reports',
                    reportFiles: 'index.html'
                ])
            }
        }
    }
}
