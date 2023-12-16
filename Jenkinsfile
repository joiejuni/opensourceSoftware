pipeline {
    agent any
    environment {
            PROJECT_ID = 'open-new-407603'
            CLUSTER_NAME = 'kube'
            LOCATION = 'asia-northeast3-a'
            //GKE_CREDENTIALS = credentials('your-credential-id')
            CREDENTIALS_ID = '44b4916c-2f25-4b4c-a1ca-7d0d75fe9107'
            registry = 'https://hub.docker.com/r/julia2039/springboot'
            imageName = 'https://hub.docker.com/r/julia2039/springboot/julia2039/springboot:1.0'
            dockerImage = ''
    }

    stages {
        stage("Checkout code") {  // 멀티 브랜치파이프라인 작성할 때 세팅해놓은 코드 가져옴. git clone 효과
            steps {
                checkout scm
               }
        }

//         stage('Build Spring Boot Project') {
//             steps {
//                 script {
//                     // Spring Boot 프로젝트 빌드
//                     sh 'chmod +x gradlew'
//                     sh './gradlew clean build --warning-mode all'
//                 }
//             }
//         }

        stage('Build Docker Image') {
            steps {
                script {
                    // Docker 이미지 빌드. 만들 이미지명 명시 (username/repository name)
                    //docker.build("julia2039/springboot:${env.BUILD_ID}", "-f Dockerfile .")
                    myapp = docker.build("julia2039/springboot:${env.BUILD_ID}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Docker 이미지를 Docker Hub로 푸시함
                    docker.withRegistry('https://registry.hub.docker.com', 'julia2039') {
//                         docker.image("julia2039/springboot:1.0").push()
                        myapp.push("latest")
                        myapp.push("${env.BUILD_ID}")
                    }
                }
            }
        }

        stage('Clean Up Unused Docker Images') {
            steps {
                script {
                    // 태그가 겹친 이미지 삭제
                    sh 'docker rmi -f $(docker images -f "dangling=true" -q) || true'
                }
            }
        }

//         stage('Stop and Remove Existing Container') {
//             steps {
//                 script {
//                     // 기존에 동작 중인 컨테이너 중지 및 삭제함
//                     sh 'docker ps -q --filter "name=spring-boot-server" | grep -q . && docker stop spring-boot-server && docker rm spring-boot-server || true'
//                 }
//             }
//         }

        stage("Deploy to GKE") {
            when {
                branch 'main'
            }
            steps {
                sh "sed -i 's/springboot:latest/springboot:${env.BUILD_ID}/g' deployment1.yml"
                step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME,
                location: env.LOCATION, manifestPattern: 'deployment1.yml', credentialsId: env.CREDENTIALS_ID,
                verifyDeployments: true])
            }
        }

//         stage('Run Docker Container') {
//             steps {
//                 script {
//                     // Docker 컨테이너 실행
//                     sh 'docker run -p 8081:8080 -d --name=spring-boot-server julia2039/springboot:1.0'
//                 }
//             }
//         }

//         stage('Clean Up Unused Docker Images') {
//             steps {
//                 script {
//                     // 태그가 겹친 이미지 삭제
//                     sh 'docker rmi -f $(docker images -f "dangling=true" -q) || true'
//                 }
//             }
//         }

    }

    post {
        always {
            cleanWs()
        }
    }
}
