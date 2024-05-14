pipeline {

    agent any
    
    stages {

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
