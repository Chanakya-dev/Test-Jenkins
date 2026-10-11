pipeline{
	agent any
	stages{
		stage('Checkout'){
			steps{
				checkout scm
			}
		}
		stage('Gradle Build'){
			steps{
				bat 'gradlew.bat clean build'
			}
		}
		stage('Build Docker Image'){
			steps{
				bat 'docker build -t sample:latest .'
			}
		}
		stage('Stop OLD Container'){
			steps{
				script{
					bat 'docker rm-f sample-container || exit /b 0'
				}
			}
		}
		stage('Deploy Container'){
			steps{
				bat 'docker run -d --name sample-container -p 8080:8080 sample:latest'
			}
		}
	}
}