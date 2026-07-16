def buildJar() {
    echo 'building the application...'
    sh 'mvn clean package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t felixkarg/demo-app:$IMAGE_NAME ."
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh "docker push  felixkarg/demo-app:$IMAGE_NAME"
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
