pipeline {
    agent any
    environment {
        PROJECT_ID   = 'test-project0012'
        REGION       = 'asia-south1'
        REPOSITORY   = 'test-jenkins'
        SERVICE_NAME = 'sample'
        IMAGE_TAG    = "${env.BUILD_NUMBER}"
        GAR_HOST     = "${REGION}-docker.pkg.dev"
        IMAGE_URL    = "${GAR_HOST}/${PROJECT_ID}/${REPOSITORY}/${SERVICE_NAME}:${IMAGE_TAG}"
    }
    stages {
        stage('Authenticate to GCP') {
            steps {
                withCredentials([file(credentialsId: 'gcp-sa-key', variable: 'GCP_KEY')]) {
                    sh 'gcloud auth activate-service-account --key-file="${GCP_KEY}"'
                    sh "gcloud auth configure-docker ${GAR_HOST} --quiet"
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    app = docker.build("${IMAGE_URL}")
                }
            }
        }
        stage('Push to Artifact Registry') {
            steps {
                script {
                    docker.withRegistry("https://${GAR_HOST}", "gcloud") {
                        app.push()
                    }
                }
            }
        }
        stage('Deploy to Cloud Run') {
            steps {
                sh """
                    gcloud run deploy ${SERVICE_NAME} \
                        --image=${IMAGE_URL} \
                        --region=${REGION} \
                        --platform=managed \
                        --allow-unauthenticated
                """
            }
        }
    }
}