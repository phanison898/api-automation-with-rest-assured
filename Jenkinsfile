pipeline {

    agent any
    
    environment {
        EMAIL_RECIPIENTS = 'phanison898@gmail.com'
    }
    
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
            script {
                def testResultAction = currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction.class)
                def testDetails = ""
                
                if (testResultAction != null) {
                    testResultAction.totalTests.each { test ->
                        def status = test.status.toString()
                        def color = status == 'FAILED' ? 'red' : (status == 'SKIPPED' ? 'orange' : 'green')
                        
                        testDetails += """
                            <tr>
                                <td>${test.className}.${test.displayName}</td>
                                <td style="color:${color};">${status}</td>
                                <td>${test.errorDetails ?: 'N/A'}</td>
                            </tr>
                        """
                    }
                } else {
                    testDetails = "<tr><td colspan='3'>No tests found</td></tr>"
                }

                def emailBody = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Build Notification</title>
                    <style>
                        table {
                            width: 100%;
                            border-collapse: collapse;
                        }
                        th, td {
                            border: 1px solid black;
                            padding: 8px;
                            text-align: left;
                        }
                        th {
                            background-color: #f2f2f2;
                        }
                    </style>
                </head>
                <body>
                    <h2>Build ${currentBuild.result}</h2>
                    <p>Hi Team,</p>
                    <p>The latest build has completed with the following status: <strong>${currentBuild.result}</strong>. Please find the details below:</p>
                    <table>
                        <tr>
                            <th>Project</th>
                            <td>${env.JOB_NAME}</td>
                        </tr>
                        <tr>
                            <th>Build Number</th>
                            <td>${env.BUILD_NUMBER}</td>
                        </tr>
                        <tr>
                            <th>Status</th>
                            <td>${currentBuild.result}</td>
                        </tr>
                        <tr>
                            <th>Triggered by</th>
                            <td>${env.BUILD_CAUSE}</td>
                        </tr>
                        <tr>
                            <th>Duration</th>
                            <td>${currentBuild.durationString}</td>
                        </tr>
                        <tr>
                            <th>Error Log</th>
                            <td><a href="${env.BUILD_URL}console">View Log</a></td>
                        </tr>
                    </table>
                    
                    <h3>Test Cases</h3>
                    <table>
                        <tr>
                            <th>Test Case</th>
                            <th>Status</th>
                            <th>Details</th>
                        </tr>
                        ${testDetails}
                    </table>
                    
                    <p>Best Regards, <br> Jenkins CI Server</p>
                </body>
                </html>
                """

                emailext(
                    subject: "Build ${env.JOB_NAME} #${env.BUILD_NUMBER} Failure Notification",
                    body: emailBody,
                    mimeType: 'text/html',
                    to: env.EMAIL_RECIPIENTS
                )
            }
        }
    }
}