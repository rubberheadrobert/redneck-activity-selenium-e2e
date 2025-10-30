pipeline {
    agent any

    parameters {
        string(name: 'APP_URL', defaultValue: 'http://localhost:3000', description: 'URL of the app to test')
    }

    environment {
        MAVEN_HOME = tool name: 'maven3', type: 'maven' // Jenkins-managed Maven
        JAVA_HOME = '' // empty to use system-installed Java
        PATH = "${env.MAVEN_HOME}/bin;${env.PATH}"
    }

    stages {
        stage('Checkout E2E Repo') {
            steps {
                git branch: 'master', url: 'https://github.com/rubberheadrobert/redneck-activity-selenium-e2e'
            }
        }

        stage('Install Dependencies') {
            steps {
                // Clean install using Maven
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                // Pass the APP_URL as a system property to the tests
                bat "mvn test -Dapp.url=${params.APP_URL}"
            }
        }

        stage('Archive Reports') {
            steps {
                // Archive test reports (if using surefire)
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            echo "E2E Pipeline finished. Check logs: ${env.BUILD_URL}"
        }
        failure {
            echo "E2E Tests failed."
        }
    }
}