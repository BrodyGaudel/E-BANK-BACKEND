node{
    stage('Clone'){
        git 'https://github.com/BrodyGaudel/E-BANK-BACKEND.git'
    }
    stage('Build'){
	bat 'cd E-BANK-BACKEND'
        bat 'mvn clean install'
    }
    stage('Run'){
        bat 'mvn spring-boot:run'
    }
}