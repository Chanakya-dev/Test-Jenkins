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
                    url: 'https://github.com/Chanakya-dev/Test-Jenkins.git'
            }
        }

        stage('Authenticate with GCP') {
            steps {
                withCredentials([
                    file(credentialsId: 'gcp-sa-key', variable: 'GCP_KEY')
                ]) {
                    bat '''
                        gcloud auth activate-service-account --key-file="%GCP_KEY%"
                        gcloud config set project %PROJECT_ID%
                    '''
                }
            }
        }

        stage('Deploy to Cloud Run') {
            steps {
                bat '''
                    gcloud run deploy %SERVICE_NAME% ^
                        --source . ^
                        --project %PROJECT_ID% ^
                        --region %REGION% ^
                        --allow-unauthenticated ^
                        --set-build-env-vars GOOGLE_RUNTIME_VERSION=17
                '''
            }
        }
    }
}