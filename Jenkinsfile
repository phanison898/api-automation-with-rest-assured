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
    
    post {
        always {
            // Send email notification regardless of build status
            emailext (
                subject: "Build ${currentBuild.result}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Build ${currentBuild.result}: ${env.JOB_NAME} #${env.BUILD_NUMBER}\r\n\r\nCheck the console output at ${env.BUILD_URL}",
                to: "recipient@example.com", // Specify the recipient email address
                from: "jenkins@example.com", // Specify the sender email address
                replyTo: "noreply@example.com", // Specify the reply-to email address
                mimeType: 'text/html',
                attachmentsPattern: '**/target/surefire-reports/*.html' // Path to your HTML test report
            )
        }
    }
}
