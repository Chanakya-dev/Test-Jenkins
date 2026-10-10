
pipeline {
    agent any

    environment {
        PROJECT_ID = 'test-project0012'
        REGION = 'asia-south1'
        SERVICE_NAME = 'sample'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/your-user/your-repo.git'
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
        }

stage('Authenticate with GCP') {
    steps {
        withCredentials([
            file(
                credentialsId: 'gcp-sa-key',
                variable: 'GCP_KEY'
            )
        ]) {
            sh '''
                gcloud auth activate-service-account \
                    --key-file="$GCP_KEY"

                gcloud config set project test-project0012

                gcloud auth list

                gcloud config get-value project
            '''
        }
    }
}
        stage('Deploy to Cloud Run') {
            steps {
                sh '''
                    gcloud run deploy $SERVICE_NAME \
                      --source . \
                      --project $PROJECT_ID \
                      --region $REGION \
                      --allow-unauthenticated
                '''
            }
        }
    }
}
