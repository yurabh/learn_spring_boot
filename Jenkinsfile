pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage("build") {

            steps {
                echo "Building the application ..."
                sh 'mvn clean install'
            }
        }
        stage("test") {

             when {
                 expression {
                     BRANCH_NAME == 'master'
                 }
             }

             steps {
                echo "Run all Test..."
                 sh "mvn test"
             }
        }

        stage("deploy") {
             steps {
                echo "Deploy the application ..."
                sh "mvn package"
             }
        }
    }

    post {
        success {
             echo "Application Built"
        }
    }
}