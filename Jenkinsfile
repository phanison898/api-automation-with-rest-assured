pipeline {

    agent any
    
    stages {

        stage('Start REST Server') {
            steps {
                dir('src/main/resources/json') {
                    bat 'json-server --watch db.json'
                }
            }
        }

        stage('Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }
}
