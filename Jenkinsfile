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
                    alwaysLinkToLastBuild: false,
                    keepAll: false,
                    reportDir: 'x-output/reports',
                    reportFiles: 'index.html', 
    				reportName: 'Extent Report',
    				reportTitles: 'API Automation Report',
    				wrapperName: 'HTML Report'
                ])
            }
        }
    }
}
